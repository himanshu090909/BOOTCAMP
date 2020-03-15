package com.jpa2.jpa2project.repositories;

import com.jpa2.jpa2project.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {
}
