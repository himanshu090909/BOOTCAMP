package com.tothenew.bootcamp.springProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Employee {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private Department1 department1;

    /**
     * constructor dependency injection
     */
    @Autowired
    Employee(Department1 department1) {
        this.department1 = department1;
    }

    Employee() {

    }

    /**
     * without constructor dependency injection comment all the above code and uncomment this
     */
    /*@Autowired
       private Department1 department1;
    */
    public void show() {


        System.out.print(getName() + " ");
        department1.getDepartment();
    }

    public void shows() {
        HR h = new HR();
        h.getDepartment();
    }

}
