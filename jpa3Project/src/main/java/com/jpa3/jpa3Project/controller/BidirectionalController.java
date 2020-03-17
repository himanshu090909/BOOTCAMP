package com.jpa3.jpa3Project.controller;

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

    @GetMapping("/bidirectional")
    public void createSampleData() {
        AuthorBidirectional authorBidirectional = new AuthorBidirectional();
        authorBidirectional.setName("himanshu");
        BookBidirectional bookBidirectional = new BookBidirectional();
        bookBidirectional.setBookName("harry potter");
        BookBidirectional bookBidirectional1 = new BookBidirectional();
        bookBidirectional1.setBookName("dfghj");
        HashSet<BookBidirectional> bookBidirectionals = new HashSet<>();
        bookBidirectionals.add(bookBidirectional);
        bookBidirectionals.add(bookBidirectional1);
        bookBidirectional.setAuthorBidirectional(authorBidirectional);
        bookBidirectional1.setAuthorBidirectional(authorBidirectional);
        authorBidirectional.setBookBidirectionals(bookBidirectionals);
        authorBidirectionalRepository.save(authorBidirectional);
    }

}
