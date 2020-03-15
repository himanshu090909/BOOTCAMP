package com.jpa2.jpa2project.controller;

import com.jpa2.jpa2project.entities.Embeddedemployee;
import com.jpa2.jpa2project.entities.Salary;
import com.jpa2.jpa2project.repositories.EmbeddedEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmbeddedEMployeeController
{
    @Autowired
    EmbeddedEmployeeRepository embeddedEmployeeRepository;

    @GetMapping("/embeddeddemo")
    public void embeddeddemofunc()
    {
        Salary salary = new Salary();
        salary.setBasicSalary(100000);
        salary.setBonusSalary(5000);
        salary.setSpecialAllowanceSalary(56);
        salary.setTaxAmount(34);
        Embeddedemployee embeddedemployee = new Embeddedemployee();
        embeddedemployee.setAge(24);
        embeddedemployee.setFirstName("himanshu");
        embeddedemployee.setLastName("bhansali");
        embeddedemployee.setSalary(salary);
        embeddedEmployeeRepository.save(embeddedemployee);
    }
}
