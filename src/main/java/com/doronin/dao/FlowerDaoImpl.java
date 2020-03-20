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
    public void update(FlowersEntity flower) {
        sessionFactory.getCurrentSession().saveOrUpdate(flower);
    }

    @Override
    public List<FlowersEntity> list() {
        Session currentSession = sessionFactory.getCurrentSession();
        TypedQuery<FlowersEntity> query = sessionFactory.getCurrentSession().createQuery(("select Flower from FlowersEntity Flower"));
        return query.getResultList();
    }

    @Override
    public FlowersEntity getFlowerByName(String flowername) {
        TypedQuery<FlowersEntity> query = sessionFactory.getCurrentSession().createQuery("select Flower from FlowersEntity Flower " +
                "where Flower.name = '" + flowername + "'", FlowersEntity.class);
        LOGGER.info("Enter into getFlolwerByName function");
        LOGGER.info(query.getResultList().toString());
        return query.getSingleResult();
    }

    @Override
    public List<FlowersEntity> searchByName(String keyword) {
        TypedQuery<FlowersEntity> query = sessionFactory.getCurrentSession().createQuery("select f from FlowersEntity f where f.name like '%" + keyword + "%'");
        LOGGER.info("Enter into searchByName function");
        return query.getResultList();
    }

    @Override
    public List<FlowersEntity> searchByRange(String from, String to) {
        TypedQuery<FlowersEntity> query = sessionFactory.getCurrentSession().createQuery("select f from FlowersEntity f where f.price between " + from + " and " + to);
        LOGGER.info("Enter into searchByRange function");
        return query.getResultList();
    }

    @Override
    public List<FlowersEntity> searchGreater(String from) {
        TypedQuery<FlowersEntity> query = sessionFactory.getCurrentSession().createQuery("select f from FlowersEntity f where f.price >= " + from);
        LOGGER.info("Enter into searchGreater function");
        return query.getResultList();
    }

    @Override
    public List<FlowersEntity> searchSmaller(String to) {
        TypedQuery<FlowersEntity> query = sessionFactory.getCurrentSession().createQuery("select f from FlowersEntity f where f.price <= " + to);
        LOGGER.info("Enter into searchSmaller function");
        return query.getResultList();
    }
}
