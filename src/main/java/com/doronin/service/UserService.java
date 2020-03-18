package com.doronin.service;


import com.doronin.model.FlowersUsersEntity;

import java.util.List;

public interface UserService {
   void save(FlowersUsersEntity user);
   void update(FlowersUsersEntity user);
   List<FlowersUsersEntity> list();
   boolean isUsernameBusy(String username);
   boolean isCorrectUser(String username, String password);
   FlowersUsersEntity getUserByLogin(String username);

}
