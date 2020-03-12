package com.doronin.dao;


import com.doronin.model.FlowersUsersEntity;

import java.util.List;



public interface UserDao {
   void save(FlowersUsersEntity user);
   List<FlowersUsersEntity> list();
   boolean isUsernameBusy(String userName);
   boolean isUserExists(String username, String password);
}
