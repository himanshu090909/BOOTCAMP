package com.example.Restdemo2.ModelClasses;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "class created for filter operations")
//for dynamic filtering
@JsonFilter("filter")
public class NewUser
{
    private int id;
    private String name;

    //for static filtering
    @JsonIgnore
    private int password;

    public NewUser(int id, String name, int password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public NewUser() {
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
