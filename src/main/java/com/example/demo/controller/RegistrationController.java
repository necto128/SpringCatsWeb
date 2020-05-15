package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String addProfile(User users) {
        User usersFromDb = userService.ckeckUser(users);
        if (usersFromDb != null) {
            return "redirect:/register?error";
        } else {
            userService.createUser(users);
        }
        return "redirect:/login";
    }
}
