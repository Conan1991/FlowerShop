package com.doronin.controller;


import com.doronin.data.SimpleCartObject;
import com.doronin.enums.Status;
import com.doronin.model.CartEntity;
import com.doronin.service.CartService;
import com.doronin.service.FlowerService;
import com.doronin.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {

    private static final Logger LOGGER = LogManager.getLogger(CartController.class);

    public List<CartEntity> carts;

    private final CartService cartService;
    private final UserService userService;
    private final FlowerService flowerService;

    @Autowired
    public CartController(CartService cartService, UserService userService, FlowerService flowerService) {
        this.cartService = cartService;
        this.userService = userService;
        this.flowerService = flowerService;
    }

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

        String flowername = simpleCartObject.getFlowername();
        String username = simpleCartObject.getUsername();
        Integer amount = Integer.valueOf(simpleCartObject.getAmount());
        LOGGER.info("Has entered to addToCart post method");
        LOGGER.info(username);
        LOGGER.info(flowername);
        LOGGER.info(amount);

        CartEntity cartEntity = new CartEntity();
        cartEntity.setLogin(username);
        cartEntity.setName(flowername);
        cartEntity.setOrdered(amount);
        Integer price = flowerService.getFlowerByName(flowername).getPrice();
        LOGGER.info(price);
        cartEntity.setSumPrice(BigInteger.valueOf(amount * price));
        cartService.save(cartEntity);
    }

    @RequestMapping(value = "/doOrder/{username}", method = RequestMethod.GET)
    public String doOrder(@PathVariable String username, Model model) {
        LOGGER.info("Get do order method");
        LOGGER.info("get value" + username);
        LOGGER.info(Status.CREATED.name());
        model.addAttribute("username", username);
        return "home";
    }

    @RequestMapping(value = "/cart/{username}", method = RequestMethod.GET)
    public String cart( Model model, @PathVariable String username) {

        LOGGER.info("Get cart method");
        LOGGER.info("get value" + username);

        List<CartEntity> cart = getCartByUsername(username);
        model.addAttribute("cart", cart);
        model.addAttribute("username", username);

        return "cart";
    }


}
