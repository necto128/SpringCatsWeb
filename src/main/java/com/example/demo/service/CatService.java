package com.example.demo.service;

import com.example.demo.model.Cat;
import com.example.demo.model.User;
import com.example.demo.repos.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CatService {
    @Autowired
    private CatRepository catRepository;

    public Iterable<Cat> listCats() {
        Iterable<Cat> listcat = catRepository.findAll();
        return listcat;
    }

    public void save(Cat cats, User user) {
        Cat cat = new Cat(cats.getCat_name(), cats.getDad_id(), cats.getMam_id(), cats.getGender(), user);
        catRepository.save(cat);
    }

    public ArrayList<Cat> check(Long id) {
        Optional<Cat> cat = catRepository.findById(id);
        ArrayList<Cat> listcat = new ArrayList<>();
        cat.ifPresent(listcat::add);
        return listcat;
    }

    public void deleteRecord(Long id) {
        Cat cats = catRepository.findById(id).orElseThrow();
        catRepository.delete(cats);
    }

    public void editRecord(Cat cats) {
        Cat cat = catRepository.findById(cats.getCat_id()).orElseThrow();
        cat.setCat_name(cats.getCat_name());
        cat.setDad_id(cats.getDad_id());
        cat.setMam_id(cats.getMam_id());
        catRepository.save(cat);
    }

}
