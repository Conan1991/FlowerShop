package com.doronin.service;

import com.doronin.dao.UserDao;
import com.doronin.model.FlowersUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void save(FlowersUsersEntity user) {
        userDao.save(user);
    }

    @Transactional
    public void update(FlowersUsersEntity user) {
        userDao.update(user);
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
    public boolean isCorrectUser(String username, String password) {
        return userDao.isUserExists(username, password);
    }

    @Transactional
    public FlowersUsersEntity getUserByLogin(String username) {
        return userDao.getUserByLogin(username);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        FlowersUsersEntity usersEntity = getUserByLogin(s);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority("USER"));
        UserDetails userDetails =
                new User(usersEntity.getLogin(),
                        usersEntity.getPassword(), roles);
        return userDetails;
    }
}
