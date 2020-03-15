package com.doronin.controller;

import com.doronin.service.CartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    @Autowired
    public CartService cartService;



}
