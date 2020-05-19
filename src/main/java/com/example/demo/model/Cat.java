package com.example.demo.model;

import com.example.demo.enums.Gender;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "long")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dadId")
    private Cat dad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mamId")
    private Cat mam;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;

    public String getAuthorName() {
        return author != null ? author.getUsername() : "none";
    }

    public Cat() {
    }

    public Cat(String name, Cat dad, Cat mam, Gender gender, User author) {
        this.name = name;
        this.dad = dad;
        this.mam = mam;
        this.gender = gender;
        this.author = author;
    }

    public Cat(Long id, String name, Cat dad, Cat mam) {
        this.id = id;
        this.name = name;
        this.dad = dad;
        this.mam = mam;
    }

    public Cat gettId() {
        return dad;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat getDad() {
        return dad;
    }

    public void setDad(Cat dad) {
        this.dad = dad;
    }

    public Cat getMam() {
        return mam;
    }

    public void setMam(Cat mam) {
        this.mam = mam;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dad=" + dad +
                ", mam=" + mam +
                ", gender=" + gender +
                ", author=" + author +
                '}';
    }
}
