package com.jpa3.jpa3Project.repositories;

import com.jpa3.jpa3Project.entities.AuthorBidirectional;
import org.springframework.data.repository.CrudRepository;

public interface AuthorBidirectionalRepository extends CrudRepository<AuthorBidirectional, Integer> {
}
