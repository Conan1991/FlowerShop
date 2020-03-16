package com.doronin.dao;

import com.doronin.model.AdministratorEntity;
import com.doronin.model.FlowersEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class AdminDaoImpl implements AdminDao {

    private static final Logger LOGGER = LogManager.getLogger(FlowerDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public AdministratorEntity getAdmin() {
        TypedQuery<AdministratorEntity> query = sessionFactory.getCurrentSession().createQuery("select Admin from AdministratorEntity Admin " +
                "where Admin.login = 'admin' and Admin.password = 'admin123'", AdministratorEntity.class);
        return query.getSingleResult();
    }
}
