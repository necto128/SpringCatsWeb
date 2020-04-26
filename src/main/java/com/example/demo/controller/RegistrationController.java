package com.example.demo.controller;

import com.example.demo.enums.Role;
import com.example.demo.model.Users;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/register")
    public String register(Model model) {

        return "register";
    }

    @PostMapping("/register")
    public String addProfile(Users users) {

        Users usersFromDb = userRepo.findByUsername(users.getUsername());
        if (usersFromDb != null) {
            return "register?error";
        }
        users.setActive(true);
        users.setRoles(Collections.singleton(Role.USER));
        userRepo.save(users);
        return "redirect:/login";
    }
}
