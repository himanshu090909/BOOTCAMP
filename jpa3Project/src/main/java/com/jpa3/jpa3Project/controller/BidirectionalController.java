package com.jpa3.jpa3Project.controller;

import com.jpa3.jpa3Project.dao.BidirectionalDao;
import com.jpa3.jpa3Project.entities.AuthorBidirectional;
import com.jpa3.jpa3Project.entities.AuthorUnidirectional;
import com.jpa3.jpa3Project.entities.BookBidirectional;
import com.jpa3.jpa3Project.entities.BookUnidirectional;
import com.jpa3.jpa3Project.repositories.AuthorBidirectionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class BidirectionalController {
    @Autowired
    AuthorBidirectionalRepository authorBidirectionalRepository;

    @Autowired
    BidirectionalDao bidirectionalDao;

    @GetMapping("/bidirectional")
    public void createSampleData() {
        AuthorBidirectional authorBidirectional = bidirectionalDao.createData();
        authorBidirectionalRepository.save(authorBidirectional);
    }

}
