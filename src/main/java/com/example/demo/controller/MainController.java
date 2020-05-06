package com.example.demo.controller;

import com.example.demo.model.Cats;
import com.example.demo.model.Users;
import com.example.demo.repos.CatRepository;
import com.example.demo.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private CatService catService;

    @GetMapping("/mainPage")
    public String mainPage(Model model, Users users) {
        model.addAttribute("listcat", catService.listCats());
        return "mainPage";
    }

    @GetMapping("/addCats")
    public String beginAddCats(Model model) {
        model.addAttribute("listcat", catService.listCats());
        return "addCats";
    }

    @PostMapping("/addCats")
    public String endAddCats(@RequestParam(name = "nameCat") String nameCat, @RequestParam(name = "idDad") Long idDad, @RequestParam(name = "idMam") Long idMam, @RequestParam(name = "gender") String gender, Model model, @AuthenticationPrincipal Users users) {
        if (nameCat != null & idDad != null & idMam != null & gender != null) {
            Cats cats = new Cats(nameCat, idDad, idMam, gender, users);
            catService.save(cats);
        } else {
            return "addCats";
        }
        return "redirect:/mainPage";
    }

    @PostMapping("/chekCat")
    public String chekCat(@RequestParam(name = "check") Long id, Model model) {

        model.addAttribute("listcat", catService.check(id));
        return "chekCat";
    }

    @PostMapping("/chekCat/remove")
    public String deleteCat(@RequestParam Long id, @RequestParam String name_cat, @RequestParam Long id_dad, @RequestParam Long id_mam, @RequestParam String gender, Model model) {
        catService.deleteRecord(id);
        model.addAttribute("id_cat", id);
        model.addAttribute("name_cat", name_cat);
        model.addAttribute("id_dad", id_dad);
        model.addAttribute("id_mam", id_mam);
        model.addAttribute("gender", gender);
        return "deleteCat";
    }

    @PostMapping("/edit/record/")
    public String editcat(@RequestParam Long idCat, @RequestParam String nameCat, @RequestParam Long idDad, @RequestParam Long idMam, Model model) {
        catService.editRecord(idCat, nameCat, idDad, idMam);
        return "redirect:/mainPage";
    }
}
