package com.jpa3.jpa3Project.dao;

import com.jpa3.jpa3Project.entities.Address;
import com.jpa3.jpa3Project.entities.Author;
import com.jpa3.jpa3Project.entities.Book;
import com.jpa3.jpa3Project.entities.Subject;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class AuthorDao
{
    /**creating a author and setting his book details and 3 subjects*/
    public Author createAuthorData() {
        Address address = new Address();
        address.setLocation("vivek vihar");
        address.setState("delhi");
        address.setStreetNumber("jhilmil");
        Author author = new Author();
        author.setName("himanshu");
        author.setAddress(address);
        Subject subject = new Subject();
        HashSet<Subject> subjects = new HashSet<>();
        subject.setSubjectName("spring");
        subjects.add(subject);
        Subject subject1 = new Subject();
        subject1.setSubjectName("dbms");
        subjects.add(subject1);
        Subject subject2 = new Subject();
        subject2.setSubjectName("java");
        subjects.add(subject2);
        subject.setAuthor(author);
        subject1.setAuthor(author);
        subject2.setAuthor(author);
        author.setSubjects(subjects);
        Book book = new Book();
        book.setBookName("harry potter");
        author.setBook(book);
        book.setAuthor(author);
        return author;
    }

}
