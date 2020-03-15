package com.jpa2.jpa2project.controller;

import com.jpa2.jpa2project.entities.Doctor;
import com.jpa2.jpa2project.entities.Employee;
import com.jpa2.jpa2project.entities.Person;
import com.jpa2.jpa2project.entities.Student;
import com.jpa2.jpa2project.repositories.EmployeeRepository;
import com.jpa2.jpa2project.repositories.PersonRepository;
import com.jpa2.jpa2project.service.EmployeeDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;


    @GetMapping("/test")
    public void createSampleData() {
        Employee e = new Employee();
        e.setAge(23);
        e.setFirstName("himanshu");
        e.setLastName("bhansali");
        e.setSalary(1001);
        employeeRepository.save(e);
    }

    @GetMapping("/jpqlques1")
    public void find_Partial_Data() {
        Sort sort = Sort.by(Sort.Order.asc("age"), Sort.Order.desc("salary"));
        List<Object[]> objects = employeeRepository.findPartialData(PageRequest.of(0, 2, sort));
        for (Object[] objects1 : objects) {
            System.out.print(objects1[0] + " ");
            System.out.print(objects1[1]);
            System.out.println();
        }
    }

    @Transactional
    @GetMapping("/jpqlques2")
    public void updating_Salary_Of_Employees_Having_Salary_Less_Than_Average_Salary() {
        int average = employeeRepository.findaverage();
        employeeRepository.updatingSalaryOfEmployeesHavingSalaryLessThanAverageSalary(average, 50000);
    }


    @Transactional
    @DeleteMapping("/jpqlques3")
    public void deleting_Employees_Having_Minimum_Salary() {
        int minsal = employeeRepository.findminsal();
        employeeRepository.deletingEmployeesHavingMinimumSalary(minsal);
    }


    @GetMapping("/nativeques1")
    public void find_Employees_Whose_LastName_Ends_With_Singh() {
        List<Object[]> objects = employeeRepository.findEmployeesWhoseLastNameEndsWithSingh();
        for (Object[] objects1 : objects) {
            System.out.print(objects1[0] + " " + objects1[1] + " " + objects1[2]);
            System.out.println();
        }
    }

    @DeleteMapping("/nativeques2")
    public void deleting_Salary_Having_Age_Greater_Than_Passed_Age() {
        employeeRepository.deletingSalaryHavingAgeGreaterThanPassedAge(45);
    }


    @Transactional
    @GetMapping("/getall")
    public Iterable<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }


}
