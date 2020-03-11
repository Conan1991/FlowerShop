package com.doronin.controller;


import com.doronin.model.FlowersUsersEntity;
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

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationController.class);
    private static final Integer BONUS = 2000;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new FlowersUsersEntity());
        return "registration";
    }

    @ModelAttribute("user")
    public FlowersUsersEntity formBackingObject() {
        return new FlowersUsersEntity();
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") @Valid FlowersUsersEntity user, BindingResult result, Model model) {
        LOGGER.info("Enter into add User");

        Integer balance = user.getBalance();
        user.setBalance(balance + BONUS);


        if (result.hasErrors()) {
            //model.addAttribute("users", userService.list());
            return "registration";
        }
        if (userService.isUsernameBusy(user.getLogin())) {

            model.addAttribute("errMsg", "Username is Busy, sorry");
            LOGGER.info("Username is Busy, sorry");
            return "registration";
        }
        model.addAttribute("fio", user.getFio());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("username", user.getLogin());

        userService.save(user);
        return "redirect:/login";
    }
}
