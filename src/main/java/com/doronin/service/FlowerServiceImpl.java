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
    public void update(FlowersEntity flower) {
        flowersDao.update(flower);
    }

    @Transactional
    public List<FlowersEntity> list() {
        return flowersDao.list();
    }

    @Transactional
    public FlowersEntity getFlowerByName(String flowername) {
        return flowersDao.getFlowerByName(flowername);
    }

    @Transactional
    public List<FlowersEntity> searchByName(String keyword) {
        return flowersDao.searchByName(keyword);
    }

    @Transactional
    public List<FlowersEntity> searchByRange(String from, String to) {
        return flowersDao.searchByRange(from, to);
    }

    @Transactional
    public List<FlowersEntity> searchGreater(String from) {
        return flowersDao.searchGreater(from);
    }

    @Transactional
    public List<FlowersEntity> searchSmaller(String to) {
        return flowersDao.searchSmaller(to);
    }
}
