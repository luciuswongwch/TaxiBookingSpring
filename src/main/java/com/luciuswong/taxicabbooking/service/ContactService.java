package com.luciuswong.taxicabbooking.service;

import com.luciuswong.taxicabbooking.model.Contact;
import com.luciuswong.taxicabbooking.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.luciuswong.taxicabbooking.constants.TaxiCabBookingConstants.CONTACT_MESSAGE_OPEN;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(CONTACT_MESSAGE_OPEN);
        Contact savedContact = contactRepository.save(contact);
        if (savedContact != null && savedContact.getContactId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
