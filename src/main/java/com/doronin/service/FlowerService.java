package com.doronin.service;


import com.doronin.model.FlowersEntity;

import java.util.List;

public interface FlowerService {
    void save(FlowersEntity flowersEntity);
    void update(FlowersEntity flower);
    List<FlowersEntity> list();
    FlowersEntity getFlowerByName(String flowername);
}
