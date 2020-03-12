package com.doronin.dao;

import com.doronin.model.FlowersEntity;

import java.util.List;

public interface FlowerDao {
    void save(FlowersEntity flower);
    List<FlowersEntity> list();
}
