package com.example.demo.ModelClasses;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Employees
{

    @Positive
    @NotBlank(message = "id is mandatory")
    private int id;

     @Size(min = 4)
    private String name;

    @Positive
    @NotBlank(message = "age is mandatory")
    private int age;

    public Employees()
    {

    }
    public Employees(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
