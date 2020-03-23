package com.doronin.controller;

import com.doronin.dto.PayRequestEntity;
import com.doronin.dto.PayResponseEntity;
import com.doronin.dto.SearchObject;
import com.doronin.model.AdministratorEntity;
import com.doronin.model.FlowersEntity;
import com.doronin.model.FlowersUsersEntity;
import com.doronin.model.OrdersEntity;
import com.doronin.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import java.io.IOException;
import java.util.List;

@Controller
@SessionAttributes(value = {"username", "password", "flowers", "orders", "errMsg", "balance", "discount"})
public class HomeController {
    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    private final FlowerService flowerService;
    private final UserService userService;
    private final OrderService orderService;
    private final AdminService adminService;
    private final CartService cartService;

    @Autowired
    public HomeController(FlowerService flowerService, UserService userService, OrderService orderService, AdminService adminService, CartService cartService) {
        this.flowerService = flowerService;
        this.userService = userService;
        this.orderService = orderService;
        this.adminService = adminService;
        this.cartService = cartService;
    }

//    @ModelAttribute("flowers")
//    public List<FlowersEntity> getFlowers() {
//        return flowerService.list();
//    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String homeInit(@FormParam("username") String username,
                           @FormParam("password") String password,
                           Model model) {

        LOGGER.info("Get homeInitMethod");

        LOGGER.info("get username " + username);
        LOGGER.info("get password " + password);

        AdministratorEntity admin = adminService.getAdmin();
        if (username.equals(admin.getLogin()) && password.equals(admin.getPassword())) {
            List<OrdersEntity> ordersEntities = orderService.list();
            model.addAttribute("username", username);
            model.addAttribute("orders", ordersEntities);
            model.addAttribute("password", password);
            return "redirect:/admin";
        }

        if (userService.isCorrectUser(username, password)) {
            FlowersUsersEntity user = userService.getUserByLogin(username);
            Integer userBalance = user.getBalance();
            Integer userDiscount = user.getDiscount();

            List<OrdersEntity> ordersEntities = orderService.getOrderByName(username);
            List<FlowersEntity> flowersEntities = flowerService.list();
            model.addAttribute("flowers", flowersEntities);
            model.addAttribute("orders", ordersEntities);
            model.addAttribute("balance", userBalance);
            model.addAttribute("discount", userDiscount);
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            return "home";
        } else {
            model.addAttribute("errMsg", "Your username or password incorrect, please, try again");
            return "login";
        }
    }

    @PostMapping("/putOnBalance")
    public @ResponseBody
    PayResponseEntity
    procedurePay(@RequestBody PayRequestEntity inputData) {

        String username = inputData.getUsername();
        String entered = inputData.getEntered();
        FlowersUsersEntity user = userService.getUserByLogin(username);
        Integer balance = user.getBalance();
        Integer newBalance = balance + Integer.valueOf(entered);
        user.setBalance(newBalance);
        userService.update(user);

        PayResponseEntity outputData = new PayResponseEntity();
        outputData.setBalance(newBalance);
        outputData.setSuccess(true);
        return outputData;
    }

    @GetMapping("/closeSession")
    @ResponseStatus(HttpStatus.OK)
    public void closeSession(SessionStatus status, Model model, HttpServletRequest request,
                             HttpServletResponse response) throws IOException {

        LOGGER.info("get close session method");
        LOGGER.info(model.getAttribute("username"));
        cartService.clearCart((String) model.getAttribute("username"));

        status.setComplete();
        response.sendRedirect("/login");
    }

    @RequestMapping(value = "/doSearch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody
    String doSearch(@RequestBody SearchObject obj, Model model) {

        String key = obj.getKey();
        String from = obj.getFrom();
        String to = obj.getTo();

        LOGGER.info("get doSearch method");
        LOGGER.info("key " + key + " from " + from + " to " + to);

        List<FlowersEntity> flowersEntities;
        if (key.isEmpty() && from.isEmpty()) flowersEntities = flowerService.searchSmaller(to);
        else if (key.isEmpty() && to.isEmpty()) flowersEntities = flowerService.searchGreater(from);
        else if (from.isEmpty() && to.isEmpty()) flowersEntities = flowerService.searchByName(key);
        else flowersEntities = flowerService.searchByRange(from, to);

        model.addAttribute("flowers", flowersEntities);
        return "/searchResult";
    }

    @GetMapping("/searchResult")
    public String searchResult(Model model) {
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (LoginController.isAdminLoggedIn(model, adminService))
            return "redirect:/admin";
        if (!LoginController.isUserLoggedIn(model, userService))
            return "redirect:/login";

        String username = (String) model.getAttribute("username");
        List<OrdersEntity> ordersEntities = orderService.getOrderByName(username);
        List<FlowersEntity> flowersEntities = flowerService.list();

        model.addAttribute("orders", ordersEntities);
        model.addAttribute("flowers", flowersEntities);

        return LoginController.checkLogin(model, adminService, userService);
    }

}
