package com.doronin.service;


import com.doronin.model.FlowersUsersEntity;

import java.util.List;

public interface UserService {
   void save(FlowersUsersEntity user);

   List<FlowersUsersEntity> list();
   boolean isUsernameBusy(String username);
   boolean isUserExists(String username, String password);
}
