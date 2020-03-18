package com.jpa3.jpa3Project.dao;

import com.jpa3.jpa3Project.entities.AuthorUnidirectional;
import com.jpa3.jpa3Project.entities.BookUnidirectional;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UnidirectionalDao
{
    public AuthorUnidirectional createData()
    {
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
        return authorUnidirectional;
    }
}
