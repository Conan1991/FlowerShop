package com.doronin.service;

import com.doronin.model.OrdersEntity;

import java.util.List;

public interface OrderService {
    void save(OrdersEntity ordersEntity);

    List<OrdersEntity> list();

    OrdersEntity getOrderByName(String s);
}
