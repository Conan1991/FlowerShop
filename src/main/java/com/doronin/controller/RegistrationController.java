package com.doronin.controller;


import com.doronin.model.FlowersUsersEntity;
import com.doronin.service.AdminService;
import com.doronin.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

@Controller
@SessionAttributes(value = {"username", "password"})
public class RegistrationController {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationController.class);

    private static final Integer BONUS = 2000;

    private final UserService userService;
    private final AdminService adminService;

    @Autowired
    public RegistrationController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @ModelAttribute("user")
    public FlowersUsersEntity formBackingObject() {
        return new FlowersUsersEntity();
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        if (LoginController.isAdminLoggedIn(model, adminService))
            return "redirect:/admin";
        if (LoginController.isUserLoggedIn(model, userService))
            return "redirect:/home";
        return "registration";
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") @Valid FlowersUsersEntity user, BindingResult result, Model model) {
        LOGGER.info("Enter into add User");

        if (result.hasErrors()) {
            return "registration";
        }
        if (userService.isUsernameBusy(user.getLogin())) {
            model.addAttribute("errMsg", "Username is Busy, sorry");
            LOGGER.info("Username " + user.getLogin() + " is Busy, sorry");
            return "registration";
        }

        Integer balance = user.getBalance();
        user.setBalance(balance + BONUS);
        userService.save(user);
        return "redirect:/login";
    }
}
