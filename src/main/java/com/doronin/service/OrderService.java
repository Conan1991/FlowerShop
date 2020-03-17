package com.doronin.service;

import com.doronin.model.OrdersEntity;

import java.util.List;

public interface OrderService {
    void save(OrdersEntity ordersEntity);

    List<OrdersEntity> list();

    List<OrdersEntity> getOrderByName(String s);
    void update(OrdersEntity ordersEntity);
    OrdersEntity getOrderById(String id);
}
