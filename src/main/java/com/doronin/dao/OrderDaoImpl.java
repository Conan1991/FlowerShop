package com.doronin.dao;

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
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(OrdersEntity ordersEntity) {
        sessionFactory.getCurrentSession().save(ordersEntity);
    }

    @Override
    public void update(OrdersEntity ordersEntity) {
        sessionFactory.getCurrentSession().saveOrUpdate(ordersEntity);
    }

    @Override
    public List<OrdersEntity> list() {
        Session currentSession = sessionFactory.getCurrentSession();
        TypedQuery<OrdersEntity> query = currentSession.createQuery(("select Order_ from OrdersEntity Order_"));
        return query.getResultList();
    }

    @Override
    public OrdersEntity getOrderByUsername(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        TypedQuery<OrdersEntity> query = sessionFactory.getCurrentSession().createQuery("select Order_ from OrdersEntity Order_ " +
                "where Order_.login = '" + username + "'", OrdersEntity.class);
        LOGGER.info("Enter into getOrder By UserName function");
        LOGGER.info(query.getResultList().toString());
        return query.getSingleResult();
    }
}
