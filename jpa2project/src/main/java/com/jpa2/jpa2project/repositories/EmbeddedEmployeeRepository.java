package com.jpa2.jpa2project.repositories;

import com.jpa2.jpa2project.entities.Embeddedemployee;
import org.springframework.data.repository.CrudRepository;

public interface EmbeddedEmployeeRepository extends CrudRepository<Embeddedemployee,Integer>
{
}
