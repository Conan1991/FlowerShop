package com.doronin.dao;

import com.doronin.model.FlowersUsersEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImp.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(FlowersUsersEntity user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(FlowersUsersEntity user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public List<FlowersUsersEntity> list() {
        Session currentSession = sessionFactory.getCurrentSession();
        TypedQuery<FlowersUsersEntity> query = currentSession.createQuery(("select User from FlowersUsersEntity User"));
        return query.getResultList();
    }

    @Override
    public boolean isUsernameBusy(String userName) {
        LOGGER.info("Enter check username");
        Session currentSession = sessionFactory.getCurrentSession();
        TypedQuery<FlowersUsersEntity> query = sessionFactory.getCurrentSession().createQuery("select User from FlowersUsersEntity User where User.login = '" + userName + "'", FlowersUsersEntity.class);
        if (!query.getResultList().isEmpty()) {
            LOGGER.info(query.getSingleResult().toString());
            return true;
        } else LOGGER.info("Result username is free");
        return false;
    }

    @Override
    public boolean isUserExists(String username, String password) {
        Session currentSession = sessionFactory.getCurrentSession();
        TypedQuery<FlowersUsersEntity> query = currentSession.createQuery("select User from FlowersUsersEntity User " +
                "where User.login = '" + username + "'"
                + " and User.password = '" + password + "'", FlowersUsersEntity.class);
        if (query.getResultList().isEmpty()) {
            LOGGER.info("Error, user not exists");
            return false;
        } else {
            LOGGER.info("User is existing");
            return true;
        }
    }

    @Override
    public FlowersUsersEntity getUserByLogin(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        TypedQuery<FlowersUsersEntity> query = currentSession.createQuery("select User from FlowersUsersEntity User " +
                "where User.login = '" + username + "'", FlowersUsersEntity.class);
        LOGGER.info("Enter into getUserByName function");
        LOGGER.info(query.getResultList().toString());
        return query.getSingleResult();
    }

}
