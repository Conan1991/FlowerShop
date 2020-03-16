package com.doronin.service;

import com.doronin.dao.AdminDao;
import com.doronin.model.AdministratorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Transactional
    public AdministratorEntity getAdmin() {
        return adminDao.getAdmin();
    }
}
