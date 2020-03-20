package com.doronin.dao;

import com.doronin.model.OrderedItemsEntity;

import java.util.List;

public interface OrderedItemsDao {
    void save(OrderedItemsEntity ordersEntity);

    List<OrderedItemsEntity> list();

    List<OrderedItemsEntity> getOrderById(String id);

    void remove(OrderedItemsEntity entity);
}
