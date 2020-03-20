package com.doronin.controller;

import com.doronin.model.AdministratorEntity;
import com.doronin.service.AdminService;
import com.doronin.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Objects;

@Controller
@SessionAttributes(value = {"username", "password"})
public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    private final UserService userService;
    private final AdminService adminService;

    @Autowired
    public LoginController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        if (LoginController.isAdminLoggedIn(model, adminService))
            return "admin";
        if (LoginController.isUserLoggedIn(model, userService))
            return "home";
        return "login";
    }

    public static String checkLogin(Model model, AdminService adminService, UserService userService) {
        //first, check login
        if (Objects.isNull(model.getAttribute("username")) || Objects.isNull(model.getAttribute("password"))) {
            LOGGER.info("username or password are null");
            return "redirect:/login";
        }

        AdministratorEntity admin = adminService.getAdmin();
        String username = (String) model.getAttribute("username");
        String password = (String) model.getAttribute("password");

        //check if username and password belongs to user or admin
        if (username.equals(admin.getLogin()) && password.equals(admin.getPassword())) return "admin";
        if (userService.isCorrectUser(username, password)) return "home";

        LOGGER.info("get model attr " + model.getAttribute("username"));
        LOGGER.info("get model attr " + model.getAttribute("balance"));
        LOGGER.info("username or password are not correct");
        return "redirect:/login";
    }

    public static boolean isUserLoggedIn(Model model, UserService userService) {
        //first, check login
        if (Objects.isNull(model.getAttribute("username")) || Objects.isNull(model.getAttribute("password"))) {
            LOGGER.info("User username or password are null");
            return false;
        }

        String username = (String) model.getAttribute("username");
        String password = (String) model.getAttribute("password");
        //check if username and password belongs to user
        return userService.isCorrectUser(username, password);
    }

    public static boolean isAdminLoggedIn(Model model, AdminService adminService) {
        //first, check login
        if (Objects.isNull(model.getAttribute("username")) || Objects.isNull(model.getAttribute("password"))) {
            LOGGER.info("Admin username or password are null");
            return false;
        }
        AdministratorEntity admin = adminService.getAdmin();
        String username = (String) model.getAttribute("username");
        String password = (String) model.getAttribute("password");
        //check if username and password belongs to admin
        if (username.equals(admin.getLogin()) && password.equals(admin.getPassword())) return true;
        LOGGER.info("username or password are not correct");
        return false;
    }
}
