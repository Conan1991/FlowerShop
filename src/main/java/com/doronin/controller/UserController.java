package com.doronin.controller;

import com.doronin.model.AdministratorEntity;
import com.doronin.service.AdminService;
import com.doronin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String loginForm() {
        //model.addAttribute("users", userService.list());
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        AdministratorEntity admin = adminService.getAdmin();
        String username = (String) model.getAttribute("username");
        String password = (String) model.getAttribute("password");
        if (Objects.isNull(username) || Objects.isNull(password)) return "redirect:/login";
        if (username.equals(admin.getLogin()) && password.equals(admin.getPassword())) return "admin";
        return "redirect:/login";
    }

}
