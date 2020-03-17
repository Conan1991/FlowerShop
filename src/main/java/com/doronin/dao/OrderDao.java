package com.doronin.dao;



import com.doronin.model.OrdersEntity;

import java.util.List;

public interface OrderDao {
    void save(OrdersEntity ordersEntity);
    void update(OrdersEntity ordersEntity);
    List<OrdersEntity> list();
    List<OrdersEntity> getOrderByUsername(String username);
    OrdersEntity getOrderById(String id);
}
