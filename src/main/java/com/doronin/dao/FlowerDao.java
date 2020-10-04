package com.doronin.dao;

import com.doronin.model.FlowersEntity;

import java.util.List;

public interface FlowerDao {
    void save(FlowersEntity flower);

    void update(FlowersEntity flower);

    List<FlowersEntity> list();

    FlowersEntity getFlowerByName(String flowerName);

    List<FlowersEntity> searchByName(String keyword);

    List<FlowersEntity> searchByRange(String from, String to);

    List<FlowersEntity> searchGreater(String from);

    List<FlowersEntity> searchSmaller(String to);
}
