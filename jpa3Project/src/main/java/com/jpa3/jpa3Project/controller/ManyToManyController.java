package com.jpa3.jpa3Project.controller;

import com.jpa3.jpa3Project.entities.*;
import com.jpa3.jpa3Project.repositories.AuthorManyToManyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class ManyToManyController {
    @Autowired
    AuthorManyToManyRepository authorManyToManyRepository;

    @GetMapping("/manytomany")
    public void createSampleData() {
        AuthorManyToMany authorManyToMany = new AuthorManyToMany();
        authorManyToMany.setName("himanshu");
        BookManyToMany bookManyToMany = new BookManyToMany();
        bookManyToMany.setBookName("dfghj");
        BookManyToMany bookManyToMany1 = new BookManyToMany();
        bookManyToMany1.setBookName("fghj");
        HashSet<BookManyToMany> bookManyToManyHashSet = new HashSet<>();
        bookManyToManyHashSet.add(bookManyToMany);
        bookManyToManyHashSet.add(bookManyToMany1);
        authorManyToMany.setBookManyToManySet(bookManyToManyHashSet);
        AuthorManyToMany authorManyToMany1 = new AuthorManyToMany();
        authorManyToMany1.setName("ankit");
        HashSet<AuthorManyToMany> authorManyToManyHashSet = new HashSet<>();
        authorManyToManyHashSet.add(authorManyToMany);
        authorManyToManyHashSet.add(authorManyToMany1);
        bookManyToMany.setAuthorManyToManySet(authorManyToManyHashSet);
        authorManyToManyRepository.save(authorManyToMany);
        authorManyToManyRepository.save(authorManyToMany1);
    }

}
