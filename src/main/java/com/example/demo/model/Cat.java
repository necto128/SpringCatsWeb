package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long cat_id;

    @Column(name = "name_cat")
    public String cat_name;

    @Column(name = "id_dad")
    public Long dad_id;

    @Column(name = "id_mam")
    public Long mam_id;

    @Column(name = "gender")
    public String gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;

    public String getAuthorName() {
        return author != null ? author.getUsername() : "none";
    }

    public Cat() {
    }

    public Cat(String nameCat, Long idDad, Long idMam, String gender, User users) {
        this.cat_name = nameCat;
        this.dad_id = idDad;
        this.mam_id = idMam;
        this.gender = gender;
        this.author = users;
    }
    public Cat(Long cat_id, String nameCat, Long idDad, Long idMam) {
        this.cat_id = cat_id;
        this.cat_name = nameCat;
        this.dad_id = idDad;
        this.mam_id = idMam;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public Long getDad_id() {
        return dad_id;
    }

    public void setDad_id(Long dad_id) {
        this.dad_id = dad_id;
    }

    public Long getMam_id() {
        return mam_id;
    }

    public void setMam_id(Long mam_id) {
        this.mam_id = mam_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id_cat=" + cat_id +
                ", name_cat='" + cat_name + '\'' +
                ", id_dad=" + dad_id +
                ", id_mam=" + mam_id +
                ", gender='" + gender + '\'' +
                ", author=" + author +
                '}';
    }
}
