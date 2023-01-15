package com.luciuswong.taxicabbooking.repository;

import com.luciuswong.taxicabbooking.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
