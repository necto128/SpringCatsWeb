package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "cats")
public class Cats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id_cat;

    @Column(name = "name_cat")
    public String name_cat;

    @Column(name = "id_dad")
    public Long id_dad;

    @Column(name = "id_mam")
    public Long id_mam;

    @Column(name = "gender")
    public String gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private Users author;

    public String getAuthorName() {
        return author != null ? author.getUsername() : "none";
    }

    public Cats() {
    }

    public Cats(String nameCat, Long idDad, Long idMam, String gender, Users users) {
        this.name_cat = nameCat;
        this.id_dad = idDad;
        this.id_mam = idMam;
        this.gender = gender;
        this.author = users;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public Long getId_cat() {
        return id_cat;
    }

    public void setId_cat(Long id_cat) {
        this.id_cat = id_cat;
    }

    public String getName_cat() {
        return name_cat;
    }

    public void setName_cat(String name_cat) {
        this.name_cat = name_cat;
    }

    public Long getId_dad() {
        return id_dad;
    }

    public void setId_dad(Long id_dad) {
        this.id_dad = id_dad;
    }

    public Long getId_mam() {
        return id_mam;
    }

    public void setId_mam(Long id_mam) {
        this.id_mam = id_mam;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
