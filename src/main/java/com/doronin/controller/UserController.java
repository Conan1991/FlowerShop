package com.doronin.controller;

import com.doronin.model.AdministratorEntity;
import com.doronin.service.AdminService;
import com.doronin.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Objects;

@Controller
@SessionAttributes(value = {"username", "password"})
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private final UserService userService;
    private final AdminService adminService;

    @Autowired
    public UserController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping("/")
    public String loginForm(Model model) {
        return "redirect:/home";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        return LoginController.checkLogin(model, adminService, userService);
    }

}
