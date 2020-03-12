package com.product.productPoject.controller;
import com.product.productPoject.Entities.Employee;
import com.product.productPoject.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;
@RestController
public class EmployeeController
{
    @Autowired
    EmployeeRepository employeeRepository;

    //create new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody  Employee employee)
    {
       return employeeRepository.save(employee);
    }

    //updating employee
    @PostMapping("/updateemployees")
    public Employee updateEmployee(@RequestBody  Employee employee)
    {
        return employeeRepository.save(employee);
    }

    //uri for getting all employees
    @GetMapping("/employees")
    public Iterable<Employee> getAll()
    {
        System.out.println("enter");
        return  employeeRepository.findAll();
    }

    //deleting employee
    @DeleteMapping("deleteuser/{id}")
    public void deletUser(@PathVariable int id)
    {
        employeeRepository.deleteById(id);
    }

    //get a particular employee
    @GetMapping("/employees/{id}")
    public String  findOne(@PathVariable int id)
    {
        Optional<Employee> user = employeeRepository.findById(id);
        Employee employee = user.get();
        return employee.getName();
    }

    //counting the total number of employees
    @GetMapping("/count")
    public long findCount()
    {
        return employeeRepository.count();
    }

    //finding employee by name
    @GetMapping("/employeefindByName/{name}")
    public List<Employee> findbyname(@PathVariable String name)
    {
       return employeeRepository.findByName(name);
    }

    //employee starting with a character
    @GetMapping("/employeefindByNameContains")
    public List<Employee> findpattern()
    {
        return employeeRepository.findByNameLike("a%");

    }

    //implementing paging and sorting
    @GetMapping("/pagingandsorting")
    public  void pagingandsortingonemployeeage()
    {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("age"));
        employeeRepository.findAll(pageable).forEach(p-> System.out.println(p.getName()));
    }

    //employees between ages 28 and 32
    @GetMapping("/betweenage")
    public List<Employee> employeesBetweenAge()
    {
        return employeeRepository.findByAgeBetween(28,32);
    }

}
