package com.example.demo.Dao;

import com.example.demo.ModelClasses.Employees;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class EmployeeDaoService
{
    private static List<Employees> employeesList = new ArrayList<>();
    static
    {
        employeesList.add(new Employees(1,"himanshu",12));
        employeesList.add(new Employees(2,"ankit",34));
        employeesList.add(new Employees(3,"azeem",12));
        employeesList.add(new Employees(4,"divyansh",35));
    }

    //getting list of employees
    public List<Employees> getAllEmployees()
    {
        return employeesList;
    }

    //getting one employee by id
    public Employees getOneEmployee(int id)
    {
        for (Employees employees : employeesList )
        {
              if (employees.getId()==id)
              {
                  return employees;
              }
        }
        return null;
    }

    //adding new employee
    public Employees addEmployee(Employees employees)
    {
        employeesList.add(employees);
        return employees;
    }

    //deleting one employee by id
    public Employees deleteEmployee(int id)
    {
        Iterator<Employees> employeesIterator = employeesList.iterator();
        while (employeesIterator.hasNext())
        {
            Employees employees = employeesIterator.next();
            if (employees.getId()==id)
            {
                employeesIterator.remove();
                return employees;
            }
        }
        return null;
    }

    //updating records
    public void updateRecord(int id, Employees employees) {
        Iterator<Employees> iterator=employeesList.iterator();
        while (iterator.hasNext()){
            Employees employees1=iterator.next();
            if(employees1.getId()==id){
                employees1.setId(employees.getId());
                employees1.setName(employees.getName());
                employees1.setAge(employees.getAge());
            }
        }
    }


}
