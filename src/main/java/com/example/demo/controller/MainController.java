package com.example.demo.controller;

import com.example.demo.model.Cat;
import com.example.demo.model.User;
import com.example.demo.service.CatService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {
    @Autowired
    private CatService catService;
    @Autowired
    private UserService userService;

    @GetMapping("/mainPage")
    public String mainPage(Model model) {

        Cat cat = new Cat();
        model.addAttribute("catsForm", cat);
        model.addAttribute("listcat", catService.listCats());
        return "mainPage";
    }

    @GetMapping("/addCats")
    public String beginAddCats(Model model) {
        Cat cats = new Cat();
        model.addAttribute("catsForm", cats);
        model.addAttribute("listcat", catService.listCats());
        return "addCats";
    }

    @PostMapping("/addCats")
    public String endAddCats(Cat cats, @AuthenticationPrincipal User user, @RequestBody MultiValueMap<Cat, Cat> param) {
        if (cats.getName().length() != 0) {
            catService.save(cats, user);

            return "redirect:/mainPage";
        }
        return "redirect:/addCats";
    }

    @GetMapping("/chekCat")
    public String chekCat(Cat cats, Model model) {
        Cat cat = new Cat();
        model.addAttribute("catsForm", cat);
        model.addAttribute("listcat", catService.check(cats.getId()));
        return "chekCat";
    }

    @GetMapping("/chekCat/remove")
    public String deleteCat(Cat cats, Model model) {
        model.addAttribute("listcats", catService.check(cats.getId()));
        catService.deleteRecord(cats.getId());
        return "deleteCat";
    }

    @PostMapping("/edit/record/")
    public String editcat(Cat cats, Model model) {
        catService.editRecord(cats);
        return "redirect:/mainPage";
    }
}
