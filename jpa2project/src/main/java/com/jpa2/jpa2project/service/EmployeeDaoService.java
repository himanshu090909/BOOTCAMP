package com.jpa2.jpa2project.service;

import com.jpa2.jpa2project.entities.Employee;
import com.jpa2.jpa2project.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class EmployeeDaoService
{
    @Autowired
    EmployeeRepository employeeRepository;
    public int getAverage()
    {
        Iterable<Employee> list = employeeRepository.findAll();
        Iterator<Employee> iterator = list.iterator();
        int sum =0;
        int count =0;
        while (iterator.hasNext())
        {
            Employee e = iterator.next();
            sum = sum + e.getSalary();
            count++;
        }
        int average = sum/count;
        return average;
    }

}
