package com.example.demo.Controller;

import com.example.demo.Dao.EmployeeDaoService;
import com.example.demo.ExceptionHandler.NotFoundException;
import com.example.demo.ModelClasses.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController
{
    @Autowired
    private EmployeeDaoService employeeDaoService;
    @GetMapping("/welcome")
    public String a()
    {
        return "Welcome to Spring Boot";
    }
    @GetMapping("/employees")
    public List<Employees> findAllEmployees()
    {
        return employeeDaoService.getAllEmployees();
    }
    @GetMapping("/employees/{id}")
    public Employees getParticularEmployee(@PathVariable int id)
    {
        Employees employees =  employeeDaoService.getOneEmployee(id);
        if (employees==null)
        {
            throw new NotFoundException("employee with this id is not present ");
        }
        return employees;
    }
    @PostMapping("/employees")
    public void createEmployee(@Valid @RequestBody Employees employees)
    {
        Employees employees1 = employeeDaoService.addEmployee(employees);
    }
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id)
    {

        Employees employees = employeeDaoService.deleteEmployee(id);
       if (employees==null)
        {
            throw new RuntimeException();
        }
    }
    @PutMapping("employees/update/{id}")
    public void updateOne(@PathVariable int id, @RequestBody Employees employees){
        employeeDaoService.updateRecord(id, employees);
    }
}
