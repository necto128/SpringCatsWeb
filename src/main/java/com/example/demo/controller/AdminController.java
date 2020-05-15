package com.example.demo.controller;

import com.example.demo.enums.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String editprof1(User users, @RequestBody MultiValueMap<String, String> form) {
        userService.editUsers(users, form);
        return "redirect:/mainPage";
    }

    @GetMapping
    public String beginAddCats(Model model) {
        model.addAttribute("list", userService.userlist());
        return "adminweb/listUsers";
    }

    @GetMapping("{users}")
    public String editProf(@PathVariable User users, Model model) {
        User userForm = new User();
        model.addAttribute("userForm", userForm);
        model.addAttribute("USER", "");
        model.addAttribute("ADMIN", "");
        for (Role ignored : users.getRoles()) {


            if (ignored.toString().equals("USER")) {
                model.addAttribute("USER", "USER");
            }


            if (ignored.toString().equals("ADMIN")) {
                model.addAttribute("ADMIN", "ADMIN");
            }

        }
        model.addAttribute("user", users);
        return "adminweb/editProf";
    }
}
