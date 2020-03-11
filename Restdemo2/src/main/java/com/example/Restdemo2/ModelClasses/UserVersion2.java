package com.example.Restdemo2.ModelClasses;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "class created for versioning questions")
public class UserVersion2
{
    int id;
    String name;
    int password;
    String email;
    String address;

    public UserVersion2(int id, String name, int password, String email, String address) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public UserVersion2() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
