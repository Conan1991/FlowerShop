package com.doronin.controller;

import com.doronin.data.OrderStatusObject;
import com.doronin.data.OrdersResponseEntity;
import com.doronin.data.PayResponseEntity;
import com.doronin.enums.Status;
import com.doronin.model.FlowersEntity;
import com.doronin.model.FlowersUsersEntity;
import com.doronin.model.OrderedItemsEntity;
import com.doronin.model.OrdersEntity;
import com.doronin.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes(value = {"flowers", "orders", "balance"})
public class OrderController {
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    private final FlowerService flowerService;
    private final OrderedItemsService orderedItemsService;
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService, OrderedItemsService orderedItemsService, FlowerService flowerService) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderedItemsService = orderedItemsService;
        this.flowerService = flowerService;
    }

    @ModelAttribute("flowers")
    public List<FlowersEntity> getFlowers() {
        return flowerService.list();
    }

    @PostMapping(value = "/applyStatus")
    public @ResponseBody
    OrdersResponseEntity
    applyStatus(@RequestBody OrderStatusObject obj, Model model) {
        LOGGER.info("get apply status");
        OrdersResponseEntity entity = new OrdersResponseEntity();

        String id = obj.getId();
        String status = obj.getStatus();

        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();

        OrdersEntity orderById = orderService.getOrderById(id);
        orderById.setStatus(status);
        if (status.equals(Status.CLOSED.toString())) {
            entity.setDate(time.toString());
            orderById.setClosedate(time);
        }

        orderService.update(orderById);
        entity.setId(id);
        return entity;
    }

    @PostMapping("/procedurePay")
    public @ResponseBody
    PayResponseEntity
    procedurePay(@RequestBody String orderId, Model model) {

        JSONObject json = new JSONObject(orderId);
        orderId = json.getString("orderId");
        LOGGER.info("get" + orderId);

        PayResponseEntity responseEntity = new PayResponseEntity();

        OrdersEntity order = orderService.getOrderById(orderId);
        String username = order.getLogin();
        FlowersUsersEntity user = userService.getUserByLogin(username);

        BigDecimal orderTotal = order.getTotal();
        Integer orderPrice = Integer.valueOf(orderTotal.toBigInteger().intValue());
        Integer balance = user.getBalance();

        if (balance - orderPrice >= 0) {
            Integer newBalance = balance - orderPrice;
            responseEntity.setSuccess(true);
            responseEntity.setBalance(newBalance);
            user.setBalance(newBalance);
            order.setStatus(Status.PAID.toString());
            userService.update(user);
            orderService.update(order);
            List<OrderedItemsEntity> cartEntities = orderedItemsService.getOrderById(orderId);

            for (OrderedItemsEntity entity : cartEntities) {
                FlowersEntity flower = flowerService.getFlowerByName(entity.getNameFlower());
                Integer amount = flower.getAmount();
                flower.setAmount(amount - entity.getOrdered());
                flowerService.update(flower);
                //orderedItemsService.remove(entity);
            }

            model.addAttribute("flowers", flowerService.list());
            model.addAttribute("orders", orderService.getOrderByName(username));
            model.addAttribute("balance", newBalance);

            return responseEntity;
        }
        return responseEntity;
    }
}
