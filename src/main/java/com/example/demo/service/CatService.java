package com.example.demo.service;

import com.example.demo.enums.Gender;
import com.example.demo.model.Cat;
import com.example.demo.model.User;
import com.example.demo.repos.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CatService {
    @Autowired
    private CatRepository catRepository;

    public Iterable<Cat> listCats() {
        Iterable<Cat> listcat = catRepository.findAll();
        return listcat;
    }

    public void save(Cat cats, User user) {

            Cat cat = new Cat();
            cat.setName(cats.getName());
            cat.setDad(catRepository.findById(cats.getDad().getId()).orElseThrow());
            cat.setMam(catRepository.findById(cats.getMam().getId()).orElseThrow());
            cat.setGender(cats.getGender());
            cat.setAuthor(user);
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
        Cat cat = catRepository.findById(cats.getId()).orElseThrow();
        cat.setName(cats.getName());
        cat.setDad(cats.getDad());
        cat.setMam(cats.getMam());
        catRepository.save(cat);
    }

}
