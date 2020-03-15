package com.jpa2.jpa2project.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Personjoined
{
    @Id
    @GeneratedValue
    int id;
    int age;
    String name;

    public Personjoined() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
