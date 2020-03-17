package com.doronin.controller;

import com.doronin.data.OrderStatusObject;
import com.doronin.data.OrdersResponseEntity;
import com.doronin.data.PayResponseEntity;
import com.doronin.enums.Status;
import com.doronin.model.FlowersUsersEntity;
import com.doronin.model.OrdersEntity;
import com.doronin.service.CartService;
import com.doronin.service.OrderService;
import com.doronin.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    private final CartService cartService;
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(CartService cartService, OrderService orderService, UserService userService) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @ModelAttribute("orders")
    public List<OrdersEntity> getOrders() {
        return orderService.list();
    }


    @PostMapping(value = "/applyStatus")
    public @ResponseBody
    OrdersResponseEntity
    applyStatus(@RequestBody OrderStatusObject obj, Model model) {
        LOGGER.info("get apply status");
        String id = obj.getId();
        String status = obj.getStatus();

        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();

        OrdersEntity orderById = orderService.getOrderById(id);
        orderById.setStatus(status);
        orderById.setClosedate(time);
        orderService.update(orderById);

        OrdersResponseEntity entity = new OrdersResponseEntity();
        entity.setDate(time.toString());
        entity.setId(id);
        return entity;
    }

    @PostMapping("/procedurePay")
    public @ResponseBody
    PayResponseEntity
    procedurePay(@RequestBody String orderId) {

        PayResponseEntity responseEntity = new PayResponseEntity();

        OrdersEntity order = orderService.getOrderById(orderId);
        String username = order.getLogin();
        FlowersUsersEntity user = userService.getUserByLogin(username);

        BigDecimal orderTotal = order.getTotal();
        Integer orderPrice = Integer.valueOf(orderTotal.toBigInteger().intValue());
        Integer balance = user.getBalance();

        if(balance - orderPrice >= 0)
        {
            Integer newBalance = balance - orderPrice;
            responseEntity.setSuccess(true);
            responseEntity.setBalance(newBalance);
            user.setBalance(newBalance);
            order.setStatus(Status.PAID.toString());
            userService.update(user);
            orderService.update(order);
            return responseEntity;
        }

        return responseEntity;
    }

}
