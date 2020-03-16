package com.doronin.controller;

import com.doronin.data.OrderStatusObject;
import com.doronin.model.OrdersEntity;
import com.doronin.service.CartService;
import com.doronin.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Calendar;
import java.util.List;

@Controller
public class OrderController {
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    private final CartService cartService;
    private final OrderService orderService;




    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @ModelAttribute("orders")
    public List<OrdersEntity> getOrders() {
        return orderService.list();
    }


    @PostMapping("/applyStatus")
    public String applyStatus(@RequestBody OrderStatusObject obj) {
        LOGGER.info("get apply status");
        String id = obj.getId();
        String status = obj.getStatus();

        LOGGER.info(id);
        LOGGER.info(status);

        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setId(Integer.valueOf(id));
        ordersEntity.setStatus(status);
        Calendar calendar = Calendar.getInstance();
        LOGGER.info(calendar.getTime());
        //if(status.equals("CLOSED"))
        ordersEntity.setClosedate(calendar.getTime());

        orderService.update(ordersEntity);
        return "admin";
    }

}
