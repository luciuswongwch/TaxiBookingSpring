package com.luciuswong.taxicabbooking.controller;

import com.luciuswong.taxicabbooking.model.Person;
import com.luciuswong.taxicabbooking.repository.PersonRepository;
import com.luciuswong.taxicabbooking.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("public")
public class PublicController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value="/register", method={RequestMethod.GET})
    public String displayRegisterPage(Model model, Principal user) {
        if (user != null) {
            return "redirect:/profile";
        }
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @RequestMapping(value="/createUser", method={RequestMethod.POST})
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors, Model model) {
        List<String> errorMessages = new ArrayList<>();
        boolean isUsernameRegistered = personService.isUsernameRegistered(person);
        if (isUsernameRegistered) {
            errorMessages.add("Username is already registered");
        }
        boolean isEmailRegistered = personService.isEmailRegistered(person);
        if (isEmailRegistered) {
            errorMessages.add("Email is already registered");
        }
        if (!errors.hasErrors() && !isUsernameRegistered && !isEmailRegistered) {
            boolean isSaved = personService.createNewUser(person);
            if (isSaved) {
                return "redirect:/login?register=true";
            } else {
                errorMessages.add("Failed to register user, please try again later");
            }
        }
        model.addAttribute("errorMessages", errorMessages);
        return "register.html";
    }
}
