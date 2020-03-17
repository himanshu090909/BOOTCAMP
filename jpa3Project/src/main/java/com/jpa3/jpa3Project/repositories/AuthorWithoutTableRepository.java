package com.jpa3.jpa3Project.repositories;

import com.jpa3.jpa3Project.entities.AuthorWithoutTable;
import org.springframework.data.repository.CrudRepository;

public interface AuthorWithoutTableRepository extends CrudRepository<AuthorWithoutTable, Integer> {
}
