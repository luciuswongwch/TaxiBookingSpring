package com.luciuswong.taxicabbooking.repository;

import com.luciuswong.taxicabbooking.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Person readByUsername(String username);
    Person readByEmail(String email);
}
