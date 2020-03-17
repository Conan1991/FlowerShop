package com.doronin.dao;

import com.doronin.model.OrderedItemsEntity;
import com.doronin.model.OrdersEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderedItemsDaoImpl implements OrderedItemsDao {

    private static final Logger LOGGER = LogManager.getLogger(OrderedItemsDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(OrderedItemsEntity ordersEntity) {
        sessionFactory.getCurrentSession().save(ordersEntity);
    }

    @Override
    public List<OrderedItemsEntity> list() {
        Session currentSession = sessionFactory.getCurrentSession();
        TypedQuery<OrderedItemsEntity> query = currentSession.createQuery(("select Item from OrderedItemsEntity Item"));
        return query.getResultList();
    }

    @Override
    public List<OrderedItemsEntity> getOrderById(String id) {
        Session currentSession = sessionFactory.getCurrentSession();
        TypedQuery<OrderedItemsEntity> query = currentSession.createQuery("select Item from OrderedItemsEntity Item " +
                "where Item.id = '" + id + "'", OrderedItemsEntity.class);
        return query.getResultList();
    }

    @Override
    public void clearEntitiesById(String id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.createQuery("delete from OrderedItemsEntity Item " +
                "where Item.id = '" + id + "'", OrderedItemsEntity.class);
    }
}
