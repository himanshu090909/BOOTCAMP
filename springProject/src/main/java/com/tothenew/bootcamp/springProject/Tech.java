package com.tothenew.bootcamp.springProject;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Tech implements Department1 {

    @Override
    public void getDepartment() {
        System.out.println("in Tech");

    }
}
