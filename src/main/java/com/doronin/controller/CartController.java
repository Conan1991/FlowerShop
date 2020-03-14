package com.doronin.controller;


import com.doronin.data.SimpleCartObject;
import com.doronin.model.CartEntity;
import com.doronin.model.FlowersEntity;
import com.doronin.service.CartService;
import com.doronin.service.FlowerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {
    private static final Logger LOGGER = LogManager.getLogger(CartController.class);

    public List<CartEntity> carts;

    @Autowired
    CartService cartService;

    @Autowired
    private FlowerService flowerService;


    @ModelAttribute("carts")
    public List<CartEntity> getAll() {
        return cartService.list();
    }

    public List<CartEntity> getCartByUsername(String username) {
        List<CartEntity> all = getAll();
        return all.stream().filter((cart) -> cart.getLogin().equals(username)).collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/home", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addToCart(@RequestBody SimpleCartObject simpleCartObject) {
        LOGGER.info("Has entered to addToCart post method");
        LOGGER.info(simpleCartObject.getFlowername());
        LOGGER.info(simpleCartObject.getAmount());
        LOGGER.info(simpleCartObject.getUsername());
        //model.addAttribute("userName", userService.list());
        //model.addAttribute("username", username);
        //cartService.save(new CartEntity());
        //return ResponseEntity.ok(simpleCartObject.getFlowername() + simpleCartObject.getAmount() + simpleCartObject.getUsername());
    }
}
