package com.jpa3.jpa3Project.repositories;

import com.jpa3.jpa3Project.entities.AuthorManyToMany;
import org.springframework.data.repository.CrudRepository;

public interface AuthorManyToManyRepository extends CrudRepository<AuthorManyToMany, Integer> {

}
