package com.jpa3.jpa3Project.controller;

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

    @GetMapping("/withouttable")
    public void createSampleData() {
        AuthorWithoutTable authorWithoutTable = new AuthorWithoutTable();
        authorWithoutTable.setName("himanshu");
        BookWithoutTable bookWithoutTable = new BookWithoutTable();
        bookWithoutTable.setBookName("harry potter");
        BookWithoutTable bookWithoutTable1 = new BookWithoutTable();
        bookWithoutTable1.setBookName("aerg");
        HashSet<BookWithoutTable> bookWithoutTables = new HashSet<>();
        bookWithoutTables.add(bookWithoutTable1);
        bookWithoutTables.add(bookWithoutTable);
        bookWithoutTable.setAuthor(authorWithoutTable);
        bookWithoutTable1.setAuthor(authorWithoutTable);
        authorWithoutTable.setBookWithoutTables(bookWithoutTables);
        authorWithoutTableRepository.save(authorWithoutTable);
    }

}
