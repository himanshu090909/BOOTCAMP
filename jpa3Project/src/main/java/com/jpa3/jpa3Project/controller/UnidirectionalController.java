package com.jpa3.jpa3Project.controller;

import com.jpa3.jpa3Project.entities.AuthorUnidirectional;
import com.jpa3.jpa3Project.entities.BookUnidirectional;
import com.jpa3.jpa3Project.repositories.AuthorUnidirectionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class UnidirectionalController {
    @Autowired
    AuthorUnidirectionalRepository authorUnidirectionalRepository;

    @GetMapping("/unidirectional")
    public void createSampleData() {
        AuthorUnidirectional authorUnidirectional = new AuthorUnidirectional();
        authorUnidirectional.setName("himanshu");
        BookUnidirectional bookUnidirectional = new BookUnidirectional();
        bookUnidirectional.setBookName("harrypotter");
        BookUnidirectional bookUnidirectional1 = new BookUnidirectional();
        bookUnidirectional1.setBookName("qwerty");
        HashSet<BookUnidirectional> bookUnidirectionals = new HashSet<>();
        bookUnidirectionals.add(bookUnidirectional);
        bookUnidirectionals.add(bookUnidirectional1);
        authorUnidirectional.setBookUnidirectionals(bookUnidirectionals);
        authorUnidirectionalRepository.save(authorUnidirectional);

    }

}
