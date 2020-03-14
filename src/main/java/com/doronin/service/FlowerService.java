package com.doronin.service;


import com.doronin.model.FlowersEntity;

import java.util.List;

public interface FlowerService {
    void save(FlowersEntity flowersEntity);
    List<FlowersEntity> list();
    FlowersEntity getFlowerByName(String flowername);
}
