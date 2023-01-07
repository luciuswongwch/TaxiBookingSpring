package com.luciuswong.taxicabbooking.repository;

import com.luciuswong.taxicabbooking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getByRoleName(String studentRole);
}
