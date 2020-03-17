package com.jpa3.jpa3Project.controller;

import com.jpa3.jpa3Project.entities.Caching;
import com.jpa3.jpa3Project.repositories.CachingRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.Optional;

@RestController
public class CachingController {
    @Autowired
    CachingRepository cachingRepository;

    @Autowired
    EntityManager entityManager;


    @GetMapping("/cachingcreate")
    public void cachingdemo() {
        Caching caching = new Caching();
        caching.setName("himanshu");
        cachingRepository.save(caching);
        Caching caching1 = new Caching();
        caching1.setName("ankit");
        cachingRepository.save(caching1);
        Session session = entityManager.unwrap(Session.class);

    }

    /**
     * only one select statement will be performed as every other time object is picked from cache
     * and after using evict object is removed from cache so select query is fired again
     */
    @GetMapping("/evict")
    public void evictingObject() {
        Caching caching1 = null;
        Optional<Caching> caching = cachingRepository.findById(1);
        if (caching.isPresent())
            caching1 = caching.get();
        cachingRepository.findById(1);
        cachingRepository.findById(1);
        Session session = entityManager.unwrap(Session.class);
        session.evict(caching1);
        cachingRepository.findById(1);
    }
}
