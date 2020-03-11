package com.example.Restdemo2.ModelClasses;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "class created for versioning questions")
public class UserVersion1
{
    int id;
    String name;
    int password;

    public UserVersion1(int id, String name, int password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public UserVersion1() {
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

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
