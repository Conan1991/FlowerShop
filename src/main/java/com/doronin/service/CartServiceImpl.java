package com.doronin.service;


import com.doronin.dao.CartDao;
import com.doronin.model.CartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Transactional
    public void save(CartEntity cartEntity) {
        cartDao.save(cartEntity);
    }

    @Transactional
    public List<CartEntity> list() {
        return cartDao.list();
    }
}
