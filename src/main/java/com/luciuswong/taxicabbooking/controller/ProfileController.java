package com.luciuswong.taxicabbooking.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
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

    @RequestMapping(value="/updateProfile", method=POST)
    public String updateProfile(@Valid @ModelAttribute("person") Person person, Errors errors, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            log.error("Profile form validation error: " + errors.toString());
            return "profile.html";
        }
        if (authentication.getName() != person.getUsername()) {
            model.addAttribute("errorMessage", "Username cannot be changed");
            return "profile.html";
        }
        Person checkPerson = personRepository.readByEmail(person.getEmail());
        if (checkPerson != null && checkPerson.getUsername() != authentication.getName()) {
            model.addAttribute("errorMessage", "Email address is already taken. Please choose another email address");
            return "profile.html";
        }
        person.setRole(personRepository.readByUsername(authentication.getName()).getRole());
        personRepository.save(person);
        redirectAttributes.addFlashAttribute("message", "Profile is saved successfully");
        return "redirect:/profile";
    }
}
