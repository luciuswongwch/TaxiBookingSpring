package com.luciuswong.taxicabbooking.service;

import com.luciuswong.taxicabbooking.constants.TaxiCabBookingConstants;
import com.luciuswong.taxicabbooking.model.Address;
import com.luciuswong.taxicabbooking.model.Role;
import com.luciuswong.taxicabbooking.model.Person;
import com.luciuswong.taxicabbooking.repository.RoleRepository;
import com.luciuswong.taxicabbooking.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewUser(Person person) {
        Role role = roleRepository.getByRoleName(TaxiCabBookingConstants.INDIVIDUAL_ROLE);
        person.setRole(role);
        person.setAddress(new Address());
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Person savedUser = personRepository.save(person);
        if (savedUser != null && savedUser.getUserId() > 0) {
            return true;
        }
        return false;
    }

    public boolean isUsernameRegistered(Person person) {
        return personRepository.existsByUsername(person.getUsername());
    }

    public boolean isEmailRegistered(Person person) {
        return personRepository.existsByEmail(person.getEmail());
    }
}
