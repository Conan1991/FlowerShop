package com.doronin.service;

import com.doronin.dao.OrderedItemsDao;
import com.doronin.model.OrderedItemsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderedItemsServiceImpl implements OrderedItemsService {

    @Autowired
    OrderedItemsDao orderedItemsDao;

    @Transactional
    public void save(OrderedItemsEntity ordersEntity) {
            orderedItemsDao.save(ordersEntity);
    }

    @Transactional
    public List<OrderedItemsEntity> list() {
        return orderedItemsDao.list();
    }

    @Transactional
    public List<OrderedItemsEntity> getOrderById(String id) {
        return orderedItemsDao.getOrderById(id);
    }

    @Transactional
    public void clearEntitiesById(String id) {
            orderedItemsDao.clearEntitiesById(id);
    }
}
