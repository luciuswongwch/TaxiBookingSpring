package com.luciuswong.taxicabbooking.repository;

import com.luciuswong.taxicabbooking.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
