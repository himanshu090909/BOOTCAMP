package com.jpa3.jpa3Project.repositories;

import com.jpa3.jpa3Project.entities.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
