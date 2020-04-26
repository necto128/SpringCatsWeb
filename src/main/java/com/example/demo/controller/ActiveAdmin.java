package com.example.demo.controller;

import com.example.demo.enums.Role;
import com.example.demo.model.Users;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ad")
public class ActiveAdmin {
    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public String editprof1(@RequestParam("realname") Users users, @RequestParam("username") String name, @RequestParam Map<String, String> form) {
        users.setUsername(name);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        users.getRoles().clear();
        for (String key : form.keySet()) {

            if (roles.contains(key)) {
                users.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(users);
        return "redirect:/mainPage";
    }

    @GetMapping("{ad}")
    public String editProf(@PathVariable Users ad, Model model) {
        model.addAttribute("USER", "USER");
        model.addAttribute("ADMIN", "");
        model.addAttribute("user", ad);
        return "editProf";
    }
}