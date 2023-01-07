package com.luciuswong.taxicabbooking.controller;

import com.luciuswong.taxicabbooking.model.Address;
import com.luciuswong.taxicabbooking.model.Person;
import com.luciuswong.taxicabbooking.repository.PersonRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class ProfileController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/profile")
    public ModelAndView displayProfilePage(Authentication authentication) {
        Person person = personRepository.readByUsername(authentication.getName());
        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("person", person);
        return modelAndView;
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("person") Person person, Errors errors, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        if (errors.hasFieldErrors("email") || errors.hasFieldErrors("address.*")) {
            return "profile.html";
        }
        Person checkPerson = personRepository.readByEmail(person.getEmail());
        if (checkPerson != null && !checkPerson.getUsername().equals(authentication.getName())) {
            model.addAttribute("errorMessage", "Email address is already taken. Please choose another email address");
            return "profile.html";
        }
        Person loggedInPerson = personRepository.readByUsername(authentication.getName());
        loggedInPerson.setEmail(person.getEmail());
        loggedInPerson.getAddress().setStreetAddress(person.getAddress().getStreetAddress());
        loggedInPerson.getAddress().setCity(person.getAddress().getCity());
        loggedInPerson.getAddress().setRegionOrCountry(person.getAddress().getRegionOrCountry());
        loggedInPerson.getAddress().setZipCode(person.getAddress().getZipCode());
        personRepository.save(loggedInPerson);
        redirectAttributes.addFlashAttribute("message", "Profile is saved successfully");
        return "redirect:/profile";
    }
}
