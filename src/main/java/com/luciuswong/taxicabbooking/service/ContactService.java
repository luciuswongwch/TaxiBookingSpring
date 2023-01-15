package com.luciuswong.taxicabbooking.service;

import com.luciuswong.taxicabbooking.model.Contact;
import com.luciuswong.taxicabbooking.model.Person;
import com.luciuswong.taxicabbooking.repository.ContactRepository;
import com.luciuswong.taxicabbooking.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.luciuswong.taxicabbooking.constants.TaxiCabBookingConstants.CONTACT_MESSAGE_OPEN;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private PersonRepository personRepository;

    public boolean saveMessageDetails(Contact contact, String loggedInUsername) {
        boolean isSaved = false;
        contact.setStatus(CONTACT_MESSAGE_OPEN);
        if (loggedInUsername != null) {
            Person loggedInPerson = getLoggedInPerson(loggedInUsername);
            contact.setPerson(loggedInPerson);
        }
        Contact savedContact = contactRepository.save(contact);
        if (savedContact != null && savedContact.getContactId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public Person getLoggedInPerson(String loggedInUsername) {
        return personRepository.readByUsername(loggedInUsername);
    }
}
