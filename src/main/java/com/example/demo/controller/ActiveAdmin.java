package com.example.demo.controller;

import com.example.demo.enums.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ad")
public class ActiveAdmin {
    @Autowired
    private UserService userService;

    @PostMapping
    public String editprof1(User users,@RequestBody MultiValueMap<String, String> form) {
        userService.editUsers(users, form);
        return "redirect:/mainPage";
    }

    @GetMapping("/{ad}")
    public String editProf(@PathVariable(name = "ad") User id, Model model) {
        User userForm = new User();
        model.addAttribute("userForm", userForm);
        for (Role ignored : id.getRoles()) {

            if (ignored.toString().equals("USER")) {
                model.addAttribute("USER", ignored.toString());
            }

            if (ignored.toString().equals("ADMIN")) {
                model.addAttribute("ADMIN", ignored.toString());
            }

        }
        model.addAttribute("user", id);
        return "active/editProf";
    }
}
