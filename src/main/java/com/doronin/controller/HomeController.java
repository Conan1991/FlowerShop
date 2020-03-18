package com.doronin.controller;

import com.doronin.data.PayRequestEntity;
import com.doronin.data.PayResponseEntity;
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
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.FormParam;
import java.util.List;

@Controller
@SessionAttributes(value = {"username", "flowers", "orders", "errMsg"})
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

    @ModelAttribute("flowers")
    public List<FlowersEntity> getFlowers() {
        return flowerService.list();
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String homeInit(@FormParam("username") String username,
                           @FormParam("password") String password,
                           Model model) {

        LOGGER.info("Get homeInitMethod");

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

    @GetMapping("/home")
    public String home(Model model) {

        LOGGER.info("get model attr" + model.getAttribute("username"));
        return "home";
    }
}
