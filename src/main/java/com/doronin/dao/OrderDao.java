package com.doronin.dao;



import com.doronin.model.OrdersEntity;

import java.util.List;

public interface OrderDao {
    void save(OrdersEntity ordersEntity);
    List<OrdersEntity> list();
    OrdersEntity getOrderByUsername(String username);
}
