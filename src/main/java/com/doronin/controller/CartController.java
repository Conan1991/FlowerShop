package com.doronin.controller;


import com.doronin.data.AddToCartResponceEntity;
import com.doronin.data.SimpleCartObject;
import com.doronin.enums.Status;
import com.doronin.model.*;
import com.doronin.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes(value = {"username", "password", "carts"})
public class CartController {

    private static final Logger LOGGER = LogManager.getLogger(CartController.class);

    private final OrderedItemsService orderedItemsService;
    private final CartService cartService;
    private final UserService userService;
    private final FlowerService flowerService;
    private final OrderService orderService;
    private final AdminService adminService;

    @Autowired
    public CartController(CartService cartService, UserService userService, FlowerService flowerService, OrderService orderService, OrderedItemsService orderedItemsService, AdminService adminService) {
        this.cartService = cartService;
        this.userService = userService;
        this.flowerService = flowerService;
        this.orderService = orderService;
        this.orderedItemsService = orderedItemsService;
        this.adminService = adminService;
    }

    @ModelAttribute("carts")
    public List<CartEntity> getAll() {
        return cartService.list();
    }

    @ModelAttribute("flowers")
    public List<FlowersEntity> getFlowers() {
        return flowerService.list();
    }

    public List<CartEntity> getCartByUsername(String username) {
        List<CartEntity> all = getAll();
        return all.stream().filter((rec) -> rec.getLogin().equals(username)).collect(Collectors.toList());
    }

    public boolean isFlowerOrdered(String username, String flowername) {
        List<CartEntity> cartByUsername = getCartByUsername(username);

        long count = cartByUsername.stream().filter((rec) -> rec.getName().equals(flowername)).count();
        LOGGER.info("Find " + count + " records");
        if (count > 0)
            return true;
        return false;
    }

    private CartEntity getRecord(String username, String flowername) {
        List<CartEntity> cartByUsername = getCartByUsername(username);
        return cartByUsername.stream().filter((rec) -> rec.getName().equals(flowername)).findFirst().get();
    }

    @RequestMapping(value = "/home", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    AddToCartResponceEntity addToCart(@RequestBody SimpleCartObject simpleCartObject) {

        AddToCartResponceEntity cartResponceEntity = new AddToCartResponceEntity();
        String flowername = simpleCartObject.getFlowername();
        String username = simpleCartObject.getUsername();
        Integer amount = Integer.valueOf(simpleCartObject.getAmount());
        LOGGER.info("Has entered to addToCart post method");
        LOGGER.info(username);
        LOGGER.info(flowername);
        LOGGER.info(amount);

        FlowersEntity flowerByName = flowerService.getFlowerByName(flowername);
        Integer price = flowerByName.getPrice();
        Integer totalFlowers = flowerByName.getAmount();

        if (amount > totalFlowers) {
            cartResponceEntity.setSuccess(false);
            cartResponceEntity.setResponse("Sorry, you can't add to cart because there are fewer flowers in the warehouse than you want to order");
            return cartResponceEntity;
        }

        Double discount = Double.valueOf(userService.getUserByLogin(username).getDiscount() * 0.01);

        if (isFlowerOrdered(username, flowername)) {
            CartEntity entity = getRecord(username, flowername);
            LOGGER.info(entity.toString());
            Integer newOrdered = entity.getOrdered() + amount;
            BigDecimal newSummary = BigDecimal.valueOf(newOrdered * price * (1 - discount));
            entity.setOrdered(newOrdered);
            entity.setSumPrice(newSummary);
            cartService.update(entity);
            LOGGER.info("updating");
        } else {
            CartEntity cartEntity = new CartEntity();
            cartEntity.setLogin(username);
            cartEntity.setName(flowername);
            cartEntity.setOrdered(amount);
            cartEntity.setSumPrice(BigDecimal.valueOf(amount * price * (1 - discount)));
            cartService.save(cartEntity);
        }
        cartResponceEntity.setSuccess(true);
        return cartResponceEntity;
    }

    @RequestMapping(value = "/doOrder", method = RequestMethod.GET)
    public String doOrder(Model model) {
        LOGGER.info("Get do order method");
        LOGGER.info(Status.CREATED.name());
        if(LoginController.isAdminLoggedIn(model, adminService))
            return "redirect:/admin";
        if(!LoginController.isUserLoggedIn(model, userService))
            return "redirect:/login";
        if(cartService.list().isEmpty())
            return "home";

        String username = (String) model.getAttribute("username");
        LOGGER.info("get value" + username);

        List<CartEntity> cart = getCartByUsername(username);
        BigDecimal total = BigDecimal.valueOf(0);
        for (CartEntity entity : cart)
            total = total.add(entity.getSumPrice());

        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setLogin(username);
        ordersEntity.setStatus(Status.CREATED.name());
        ordersEntity.setTotal(total);

        LOGGER.info("trying to save orders....");
        orderService.save(ordersEntity);

        List<OrdersEntity> orderByName = orderService.getOrderByName(username);
        orderByName.stream().forEach((order) -> LOGGER.info("saved order " + order.toString()));

        FlowersUsersEntity user = userService.getUserByLogin(username);
        Integer balance = user.getBalance();

        model.addAttribute("orders", orderByName);
        model.addAttribute("status", Status.CREATED.name());
        model.addAttribute("balance", balance);

        for (CartEntity cartEntity : cart) {
            OrderedItemsEntity orderedItemsEntity = new OrderedItemsEntity();
            orderedItemsEntity.setOrderId(ordersEntity.getId());
            orderedItemsEntity.setLogin(username);
            orderedItemsEntity.setOrdered(cartEntity.getOrdered());
            orderedItemsEntity.setNameFlower(cartEntity.getName());
            orderedItemsService.save(orderedItemsEntity);
        }

        cartService.clearCart(username);
        return "home";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cartPage(Model model) {
        LOGGER.info("Get cart method");
        if(LoginController.isAdminLoggedIn(model, adminService))
            return "redirect:/admin";
        if(!LoginController.isUserLoggedIn(model, userService))
            return "redirect:/login";

        String username = (String) model.getAttribute("username");
        LOGGER.info("get value " + username);
        List<CartEntity> cart = getCartByUsername(username);

        cart.stream().forEach((entity) -> LOGGER.info("get " + entity.toString()));
        BigDecimal total = BigDecimal.valueOf(0);
        for (CartEntity entity : cart)
            total = total.add(entity.getSumPrice());
        LOGGER.info(total.doubleValue());

        FlowersUsersEntity user = userService.getUserByLogin(username);
        Integer balance = user.getBalance();
        Integer discount = user.getDiscount();

        model.addAttribute("cart", cart);
        model.addAttribute("username", username);
        model.addAttribute("balance", balance);
        model.addAttribute("discount", discount);
        model.addAttribute("totalPrice", total);
        return "cart";
    }
}
