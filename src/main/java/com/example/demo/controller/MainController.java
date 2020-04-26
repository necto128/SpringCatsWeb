package com.example.demo.controller;

import com.example.demo.model.Cats;
import com.example.demo.model.Users;
import com.example.demo.repos.CatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    private CatsRepository catsRepository;

    @GetMapping("/mainPage")
    public String mainPage(Model model, Users users) {
        Iterable<Cats> listcat = catsRepository.findAll();
        model.addAttribute("listcat", listcat);
        model.addAttribute("roll", "display:none");
        return "mainPage";
    }

    @GetMapping("/addCats")
    public String beginAddCats(Model model) {
        Iterable<Cats> listcat = catsRepository.findAll();
        model.addAttribute("listcat", listcat);
        return "addCats";
    }

    @PostMapping("/addCats")
    public String endAddCats(@RequestParam String nameCat, @RequestParam Long idDad, @RequestParam Long idMam, @RequestParam String gender, Model model, @AuthenticationPrincipal Users users) {
        if (nameCat != null & idDad != null & idMam != null & gender != null) {
            Cats cats = new Cats(nameCat, idDad, idMam, gender, users);
            catsRepository.save(cats);
        } else {
            return "addCats";
        }
        return "redirect:/mainPage";
    }

    @PostMapping("/chekCat")
    public String chekCat(@RequestParam(name = "check") Long check, Model model) {
        Users users;
        Optional<Cats> cat = catsRepository.findById(check);
        ArrayList<Cats> listcat = new ArrayList<>();
        cat.ifPresent(listcat::add);
        model.addAttribute("listcat", listcat);
        return "chekCat";
    }

    @PostMapping("/chekCat/remove")
    public String deleteCat(@RequestParam Long delete, @RequestParam String name_cat, @RequestParam Long id_dad, @RequestParam Long id_mam, @RequestParam String gender, Model model) {
        Cats cats = catsRepository.findById(delete).orElseThrow();
        catsRepository.delete(cats);
        model.addAttribute("id_cat", delete);
        model.addAttribute("name_cat", name_cat);
        model.addAttribute("id_dad", id_dad);
        model.addAttribute("id_mam", id_mam);
        model.addAttribute("gender", gender);
        return "deleteCat";
    }

    @PostMapping("/edit/record/")
    public String editcat(@RequestParam Long idCat, @RequestParam String nameCat, @RequestParam Long idDad, @RequestParam Long idMam, Model model) {
        Cats cat = catsRepository.findById(idCat).orElseThrow();
        cat.setName_cat(nameCat);
        cat.setId_dad(idDad);
        cat.setId_mam(idMam);
        catsRepository.save(cat);
        return "redirect:/mainPage";
    }
}
