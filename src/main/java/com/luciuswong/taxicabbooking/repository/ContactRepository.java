package com.luciuswong.taxicabbooking.repository;

import com.luciuswong.taxicabbooking.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

}
