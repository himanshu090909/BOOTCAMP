package com.jpa2.jpa2project.controller;

import com.jpa2.jpa2project.entities.Doctor;
import com.jpa2.jpa2project.entities.Person;
import com.jpa2.jpa2project.entities.Student;
import com.jpa2.jpa2project.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SIngleTableController
{
    @Autowired
    PersonRepository personRepository;

    @GetMapping("/singletableinheritance")
    public void single_table_inheritance()
    {
        Student student = new Student();
        student.setAge(24);
        student.setCollegename("CDAC");
        student.setName("himanshu");
        Doctor doctor = new Doctor();
        doctor.setClinicname("dentalclinic");
        doctor.setAge(35);
        doctor.setName("sehgal");
        personRepository.save(student);
        personRepository.save(doctor);


    }
}
