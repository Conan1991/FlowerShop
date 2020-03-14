package com.doronin.controller;

import com.doronin.model.FlowersEntity;
import com.doronin.service.FlowerService;
import com.doronin.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.FormParam;
import java.util.List;

@Controller
public class HomeController {
    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    @Autowired
    private FlowerService flowerService;

    @Autowired
    private UserService userService;

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
        LOGGER.info(model.getAttribute("username"));
        //model.addAttribute("flowers", flowerService.list());
        LOGGER.info(model.toString());
        if (userService.isUserExists(username, password)) {
            model.addAttribute("username", username);
            return "home";
        } else {
            model.addAttribute("errMsg", "Your username or password incorrect, please, try again");
            return "login";
        }
    }
}
