package com.luciuswong.taxicabbooking.controller;


import com.luciuswong.taxicabbooking.model.Contact;
import com.luciuswong.taxicabbooking.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @RequestMapping(value="/saveContactMsg", method=POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            log.error("Contact form validation error: " + errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
        redirectAttributes.addFlashAttribute("message", "Contact message is sent successfully");
        return "redirect:/contact";
    }
}
