package com.jpa3.jpa3Project.dao;

import com.jpa3.jpa3Project.entities.AuthorManyToMany;
import com.jpa3.jpa3Project.entities.BookManyToMany;
import com.jpa3.jpa3Project.repositories.AuthorManyToManyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ManyToManyDao
{
    @Autowired
    AuthorManyToManyRepository authorManyToManyRepository;

    public void createData()
    {
        AuthorManyToMany authorManyToMany = new AuthorManyToMany();
        authorManyToMany.setName("himanshu");
        BookManyToMany bookManyToMany = new BookManyToMany();
        bookManyToMany.setBookName("dfghj");
        BookManyToMany bookManyToMany1 = new BookManyToMany();
        bookManyToMany1.setBookName("fghj");
        HashSet<BookManyToMany> bookManyToManyHashSet = new HashSet<>();
        bookManyToManyHashSet.add(bookManyToMany);
        bookManyToManyHashSet.add(bookManyToMany1);
        authorManyToMany.setBookManyToManySet(bookManyToManyHashSet);
        AuthorManyToMany authorManyToMany1 = new AuthorManyToMany();
        authorManyToMany1.setName("ankit");
        HashSet<AuthorManyToMany> authorManyToManyHashSet = new HashSet<>();
        authorManyToManyHashSet.add(authorManyToMany);
        authorManyToManyHashSet.add(authorManyToMany1);
        bookManyToMany.setAuthorManyToManySet(authorManyToManyHashSet);
        authorManyToManyRepository.save(authorManyToMany);
        authorManyToManyRepository.save(authorManyToMany1);

    }
}
