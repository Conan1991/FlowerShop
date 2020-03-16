package com.doronin.controller;

import com.doronin.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.FormParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "login";
    }

//    @RequestMapping(value = "/submit", method = RequestMethod.POST)
//    public String loginCustomer(@FormParam("username") String username, @FormParam("password") String password, Model model) {
//        //model.addAttribute("userName", userService.list());
//        model.addAttribute("username", username);
//        LOGGER.info(username);
//        LOGGER.info(password);
//        return "redirect:/home";
//    }
}
