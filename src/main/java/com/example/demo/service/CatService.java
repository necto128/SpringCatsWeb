package com.example.demo.service;

import com.example.demo.model.Cats;
import com.example.demo.repos.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CatService {
    @Autowired
    private CatRepository catRepository;

    public Iterable<Cats> listCats() {
        Iterable<Cats> listcat = catRepository.findAll();
        return listcat;
    }

    public void save(Cats cats) {
        catRepository.save(cats);
    }

    public ArrayList<Cats> check(Long id) {
        Optional<Cats> cat = catRepository.findById(id);
        ArrayList<Cats> listcat = new ArrayList<>();
        cat.ifPresent(listcat::add);
        return listcat;
    }

    public void deleteRecord(Long id) {
        Cats cats = catRepository.findById(id).orElseThrow();
        catRepository.delete(cats);
    }

    public void editRecord(Long idCat, String nameCat, Long idDad, Long idMam) {
        Cats cat = catRepository.findById(idCat).orElseThrow();
        cat.setName_cat(nameCat);
        cat.setId_dad(idDad);
        cat.setId_mam(idMam);
        catRepository.save(cat);
    }

}
