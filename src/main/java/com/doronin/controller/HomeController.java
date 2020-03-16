package com.doronin.controller;

import com.doronin.model.AdministratorEntity;
import com.doronin.model.FlowersEntity;
import com.doronin.model.FlowersUsersEntity;
import com.doronin.model.OrdersEntity;
import com.doronin.service.AdminService;
import com.doronin.service.FlowerService;
import com.doronin.service.OrderService;
import com.doronin.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.FormParam;
import java.util.List;

@Controller
public class HomeController {
    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    private final FlowerService flowerService;
    private final UserService userService;
    private final OrderService orderService;
    private final AdminService adminService;

    @Autowired
    public HomeController(FlowerService flowerService, UserService userService, OrderService orderService, AdminService adminService) {
        this.flowerService = flowerService;
        this.userService = userService;
        this.orderService = orderService;
        this.adminService = adminService;
    }

    @ModelAttribute("flower")
    public FlowersEntity formBackingObject() {
        return new FlowersEntity();
    }

    @ModelAttribute("flowers")
    public List<FlowersEntity> getFlowers() {
        return flowerService.list();
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String homeInit(@FormParam("username") String username,
                           @FormParam("password") String password,
                           Model model) {

        LOGGER.info("Get homeInitMethod");
        //LOGGER.info(model.getAttribute("username"));
        AdministratorEntity admin = adminService.getAdmin();
        if(username.equals(admin.getLogin()) && password.equals(admin.getPassword()))
        {
            List<OrdersEntity> ordersEntities = orderService.list();
            model.addAttribute("username", username);
            model.addAttribute("orders", ordersEntities);
            return "admin";
        }

        if (userService.isUserExists(username, password)) {
            FlowersUsersEntity user = userService.getUserByLogin(username);
            Integer userBalance = user.getBalance();
            Integer userDiscount = user.getDiscount();

            List<OrdersEntity> order = orderService.getOrderByName(username);

            model.addAttribute("orders", order);
            model.addAttribute("balance", userBalance);
            model.addAttribute("discount", userDiscount);
            model.addAttribute("username", username);
            return "home";
        } else {
            model.addAttribute("errMsg", "Your username or password incorrect, please, try again");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
