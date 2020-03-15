package com.doronin.service;

import com.doronin.dao.UserDao;
import com.doronin.model.FlowersUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void save(FlowersUsersEntity user) {
        userDao.save(user);
    }

    @Transactional(readOnly = true)
    public List<FlowersUsersEntity> list() {
        return userDao.list();
    }

    @Transactional
    public boolean isUsernameBusy(String username) {
        return userDao.isUsernameBusy(username);
    }

    @Transactional
    public boolean isUserExists(String username, String password) {
        return userDao.isUserExists(username, password);
    }

    @Transactional
    public FlowersUsersEntity getUserByLogin(String username) {
        return userDao.getUserByLogin(username);
    }
}
