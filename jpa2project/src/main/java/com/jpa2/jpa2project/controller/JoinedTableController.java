package com.jpa2.jpa2project.controller;

import com.jpa2.jpa2project.entities.Doctor;
import com.jpa2.jpa2project.entities.Doctorjoined;
import com.jpa2.jpa2project.entities.Student;
import com.jpa2.jpa2project.entities.Studentjoined;
import com.jpa2.jpa2project.repositories.PersonjoinedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinedTableController
{
    @Autowired
    PersonjoinedRepository personjoinedRepository;
    @GetMapping("/joinedtableinheritance")
    public void joined_table_inheritance()
    {
        Studentjoined student = new Studentjoined();
        student.setAge(24);
        student.setCollegename("CDAC");
        student.setName("himanshu");
        Doctorjoined doctor = new Doctorjoined();
        doctor.setClinicname("dentalclinic");
        doctor.setAge(35);
        doctor.setName("sehgal");
        personjoinedRepository.save(student);
        personjoinedRepository.save(doctor);


    }
}
