package com.jpa3.jpa3Project.dao;

import com.jpa3.jpa3Project.entities.AuthorWithoutTable;
import com.jpa3.jpa3Project.entities.BookWithoutTable;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class WithoutTableDao
{
    public AuthorWithoutTable createData()
    {
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
        return authorWithoutTable;
    }
}
