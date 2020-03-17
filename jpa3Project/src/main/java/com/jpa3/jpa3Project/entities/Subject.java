package com.jpa3.jpa3Project.entities;

import javax.persistence.*;

@Entity
public class Subject {
    @Id
    @GeneratedValue
    int id;
    String subjectName;

    @ManyToOne
    @JoinColumn(name = "author_subjects")
    private Author author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
