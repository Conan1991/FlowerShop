package com.doronin.dao;

import com.doronin.model.FlowersEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class FlowerDaoImpl implements FlowerDao {

    private static final Logger LOGGER = LogManager.getLogger(FlowerDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(FlowersEntity flower) {
        sessionFactory.getCurrentSession().save(flower);
    }

    @Override
    public List<FlowersEntity> list() {
        Session currentSession = sessionFactory.getCurrentSession();
        TypedQuery<FlowersEntity> query = sessionFactory.getCurrentSession().createQuery(("select Flower from FlowersEntity Flower"));
        return query.getResultList();
    }
}
