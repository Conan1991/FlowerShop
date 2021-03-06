package com.doronin.service;

import com.doronin.dao.OrderDao;
import com.doronin.model.OrdersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Transactional
    public void save(OrdersEntity ordersEntity) {
        orderDao.save(ordersEntity);
    }

    @Transactional
    public List<OrdersEntity> list() {
        return orderDao.list();
    }

    @Transactional
    public List<OrdersEntity> getOrderByName(String s) {
        return orderDao.getOrderByUsername(s);
    }

    @Transactional
    public void update(OrdersEntity ordersEntity) {
        orderDao.update(ordersEntity);
    }

    @Transactional
    public OrdersEntity getOrderById(String id) {
        return orderDao.getOrderById(id);
    }
}
