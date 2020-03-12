package com.doronin.service;

import com.doronin.dao.FlowerDao;
import com.doronin.model.FlowersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    private FlowerDao flowersDao;

    @Transactional
    public void save(FlowersEntity flowersEntity) {
        flowersDao.save(flowersEntity);
    }

    @Transactional
    public List<FlowersEntity> list() {
        return flowersDao.list();
    }
}
