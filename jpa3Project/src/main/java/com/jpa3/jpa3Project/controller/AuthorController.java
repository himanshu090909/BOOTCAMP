package com.jpa3.jpa3Project.controller;

import com.jpa3.jpa3Project.dao.AuthorDao;
import com.jpa3.jpa3Project.entities.Address;
import com.jpa3.jpa3Project.entities.Author;
import com.jpa3.jpa3Project.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorDao authorDao;

    @GetMapping("/savingdetails")
    public String savingAuthorDetailsAlongWithAddressBookAndSubjects() {
        Author author = authorDao.createAuthorData();
        authorRepository.save(author);
        return "added records of the author successfully";
    }

}
