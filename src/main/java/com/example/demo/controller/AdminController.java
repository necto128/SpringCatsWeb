package com.example.demo.controller;

import com.example.demo.enums.Role;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String editprof1(@RequestParam("realname") Users users, @RequestParam("username") String username, @RequestParam Map<String, String> form) {
        users.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        users.getRoles().clear();
        for (String key : form.keySet()) {

            if (roles.contains(key)) {
                users.getRoles().add(Role.valueOf(key));
            }
        }
        userService.saveUser(users);
        return "redirect:/mainPage";
    }

    @GetMapping
    public String beginAddCats(Model model) {
        model.addAttribute("list", userService.listUser());
        return "listUsers";
    }

    @GetMapping("{user}")
    public String editProf(@PathVariable Users user, Model model) {
        model.addAttribute("USER", "");
        model.addAttribute("ADMIN", "");
        for (Role ignored : user.getRoles()) {


            if (ignored.toString() == "USER") {
                model.addAttribute("USER", "USER");
            }


            if (ignored.toString() == "ADMIN") {
                model.addAttribute("ADMIN", "ADMIN");
            }

        }
        model.addAttribute("user", user);
        return "editProf";
    }
}
