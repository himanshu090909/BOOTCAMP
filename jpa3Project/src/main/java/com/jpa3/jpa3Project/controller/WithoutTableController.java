package com.jpa3.jpa3Project.controller;

import com.jpa3.jpa3Project.dao.WithoutTableDao;
import com.jpa3.jpa3Project.entities.AuthorBidirectional;
import com.jpa3.jpa3Project.entities.AuthorWithoutTable;
import com.jpa3.jpa3Project.entities.BookBidirectional;
import com.jpa3.jpa3Project.entities.BookWithoutTable;
import com.jpa3.jpa3Project.repositories.AuthorWithoutTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class WithoutTableController {

    @Autowired
    AuthorWithoutTableRepository authorWithoutTableRepository;

    @Autowired
    WithoutTableDao withoutTableDao;

    @GetMapping("/withouttable")
    public void createSampleData() {
        AuthorWithoutTable authorWithoutTable = withoutTableDao.createData();
        authorWithoutTableRepository.save(authorWithoutTable);
    }

}
