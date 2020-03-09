package com.doronin.dao;

import com.doronin.model.FlowersUsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void save(FlowersUsersEntity user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<FlowersUsersEntity> list() {
      Session currentSession = sessionFactory.getCurrentSession();

      //@SuppressWarnings("unchecked")
      TypedQuery<FlowersUsersEntity> query = sessionFactory.getCurrentSession().createQuery(("select User from FlowersUsersEntity User" ));
      return query.getResultList();
   }

}
