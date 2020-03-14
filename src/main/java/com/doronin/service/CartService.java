package com.doronin.service;

import com.doronin.model.CartEntity;

import java.util.List;

public interface CartService {
    void save(CartEntity cartEntity);
    List<CartEntity> list();
}
