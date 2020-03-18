package com.jpa3.jpa3Project.controller;

import com.jpa3.jpa3Project.dao.ManyToManyDao;
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

    @Autowired
    ManyToManyDao manyToManyDao;

    @GetMapping("/manytomany")
    public void createSampleData()
    {
        manyToManyDao.createData();
    }

}
