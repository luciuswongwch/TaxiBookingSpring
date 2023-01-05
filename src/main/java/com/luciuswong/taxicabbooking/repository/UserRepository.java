package com.luciuswong.taxicabbooking.repository;

import com.luciuswong.taxicabbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
