package com.doronin.dto;

import com.doronin.model.CartEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CartDto {

    public static Map<Integer, List<CartEntity>> getTempCart() {
        return tempCart;
    }

    private static Map<Integer, List<CartEntity>> tempCart = new HashMap<>();
}
