package com.jpa3.jpa3Project.controller;

import com.jpa3.jpa3Project.dao.UnidirectionalDao;
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

    @Autowired
    UnidirectionalDao unidirectionalDao;

    @GetMapping("/unidirectional")
    public void createSampleData() {
        AuthorUnidirectional authorUnidirectional = unidirectionalDao.createData();
        authorUnidirectionalRepository.save(authorUnidirectional);

    }

}
