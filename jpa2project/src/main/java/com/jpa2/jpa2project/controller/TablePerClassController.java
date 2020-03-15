package com.jpa2.jpa2project.controller;

import com.jpa2.jpa2project.entities.Doctorjoined;
import com.jpa2.jpa2project.entities.Doctorperclass;
import com.jpa2.jpa2project.entities.Studentjoined;
import com.jpa2.jpa2project.entities.Studentperclass;
import com.jpa2.jpa2project.repositories.PersonperclassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TablePerClassController
{
    @Autowired
    PersonperclassRepository personperclassRepository;
    @GetMapping("/tableperclassinheritance")
    public void _table_perclass_inheritance()
    {
        Studentperclass student = new Studentperclass();
        student.setAge(24);
        student.setCollegename("CDAC");
        student.setName("himanshu");
        Doctorperclass doctor = new Doctorperclass();
        doctor.setClinicname("dentalclinic");
        doctor.setAge(35);
        doctor.setName("sehgal");
        personperclassRepository.save(student);
        personperclassRepository.save(doctor);


    }
}
