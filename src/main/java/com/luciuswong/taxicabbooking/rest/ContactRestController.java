package com.luciuswong.taxicabbooking.rest;

import com.luciuswong.taxicabbooking.constants.TaxiCabBookingConstants;
import com.luciuswong.taxicabbooking.model.Contact;
import com.luciuswong.taxicabbooking.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
//@Controller
@RequestMapping(path="/api/contact")
//@RequestMapping(path="/api/contact", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//@CrossOrigin(origins="*")
public class ContactRestController {
    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
//    @ResponseBody
    public List<Contact> getMessagesByStatus(@RequestParam(name="status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getMessagesByContactStatus")
//    @ResponseBody
    public List<Contact> getMessagesByContactStatus(@RequestBody Contact contact) {
        if (contact != null && contact.getStatus() != null) {
            return contactRepository.findByStatus(contact.getStatus());
        } else {
            return List.of();
        }
    }

    @PostMapping("/saveContactMessage")
//    @ResponseBody
    public ResponseEntity<Response> saveContactMessage(
            @RequestHeader("invocationFrom") String invocationFrom,
            @Valid @RequestBody Contact contact) {
        log.info(String.format("Header invocationFrom = %s", invocationFrom));
        contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMessage("Message saved successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("isMessageSaved", "true")
                .body(response);
    }

    @DeleteMapping("/deleteContactMessage")
//    @ResponseBody
    public ResponseEntity<Response> deleteContactMessage(RequestEntity<Contact> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });
        Contact contact = requestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMessage("Message successfully deleted");
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/closeMessageStatus")
    public ResponseEntity<Response> closeMessageStatus(@RequestBody Contact contactRequest) {
        Response response = new Response();
        Optional<Contact> contact = contactRepository.findById(contactRequest.getContactId());
        if (contact.isPresent()) {
            contact.get().setStatus(TaxiCabBookingConstants.CONTACT_MESSAGE_CLOSED);
            contactRepository.save(contact.get());
        } else {
            response.setStatusCode("400");
            response.setStatusMessage("Invalid contact id received");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setStatusCode("200");
        response.setStatusMessage("Message successfully closed");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
