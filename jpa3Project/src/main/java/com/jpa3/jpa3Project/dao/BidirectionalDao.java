package com.jpa3.jpa3Project.dao;

import com.jpa3.jpa3Project.entities.AuthorBidirectional;
import com.jpa3.jpa3Project.entities.BookBidirectional;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class BidirectionalDao
{
    public AuthorBidirectional createData()
    {
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
        return authorBidirectional;
    }

}
