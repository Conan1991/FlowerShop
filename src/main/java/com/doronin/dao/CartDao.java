package com.doronin.dao;

import com.doronin.model.CartEntity;

import java.util.List;

public interface CartDao {
    void save(CartEntity cart);
    void update(CartEntity cartEntity);
    List<CartEntity> list();
    void clearCart();
}
