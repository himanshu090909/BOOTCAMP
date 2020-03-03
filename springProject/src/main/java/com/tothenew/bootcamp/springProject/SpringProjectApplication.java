package com.tothenew.bootcamp.springProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringProjectApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringProjectApplication.class, args);
        Employee e = applicationContext.getBean(Employee.class);

        //this uses application context
        e.setName("himanshu");
        e.show();

        //without using application context
        Employee e1 = new Employee();
        e1.shows();
    }
}
