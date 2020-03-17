package com.doronin.dao;

import com.doronin.model.CartEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    private static final Logger LOGGER = LogManager.getLogger(CartDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(CartEntity cart) {
        sessionFactory.getCurrentSession().save(cart);
    }

    @Override
    public void update(CartEntity cartEntity) {
        sessionFactory.getCurrentSession().saveOrUpdate(cartEntity);
    }

    @Override
    public List<CartEntity> list() {
        Session currentSession = sessionFactory.getCurrentSession();
        TypedQuery<CartEntity> query = currentSession.createQuery(("select Cart from CartEntity Cart"));
        return query.getResultList();
    }

    @Override
    public void clearCart(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.createQuery(("delete from CartEntity where CartEntity.login = '" + username + "'"));
    }
}
