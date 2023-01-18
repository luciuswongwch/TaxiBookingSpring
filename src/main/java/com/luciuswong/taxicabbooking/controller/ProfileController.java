package com.luciuswong.taxicabbooking.controller;

import com.luciuswong.taxicabbooking.annotation.PasswordValidator;
import com.luciuswong.taxicabbooking.config.CustomProperties;
import com.luciuswong.taxicabbooking.model.Address;
import com.luciuswong.taxicabbooking.model.Person;
import com.luciuswong.taxicabbooking.repository.PersonRepository;
import com.luciuswong.taxicabbooking.validations.FieldsValueMatchValidator;
import com.luciuswong.taxicabbooking.validations.PasswordStrengthValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class ProfileController {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomProperties customProperties;

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

    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam Map<String, String> formData, Authentication authentication, RedirectAttributes redirectAttributes) {
        if (formData.isEmpty() || formData.get("oldPassword") == null || formData.get("newPassword") == null || formData.get("confirmPassword") == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Passwords must not be null");
            return "redirect:/profile";
        }
        if (authentication == null || !authentication.isAuthenticated()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Session expired. Please log in again to proceed.");
            return "redirect:/profile";
        }
        Person loggedInPerson = personRepository.readByUsername(authentication.getName());
        if (!passwordEncoder.matches(formData.get("oldPassword"), loggedInPerson.getPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Old password does not match");
            return "redirect:/profile";
        }

        if (!formData.get("newPassword").equals(formData.get("confirmPassword"))) {
            redirectAttributes.addFlashAttribute("errorMessage", "Confirm password does not match");
            return "redirect:/profile";
        }
        if (formData.get("newPassword").length() < 5) {
            redirectAttributes.addFlashAttribute("errorMessage", "Password must be at least 5 characters long");
            return "redirect:/profile";
        }
        List<String> weakPasswords = customProperties.getWeakPasswords();
        for (String pwd : weakPasswords) {
            if (formData.get("newPassword").toLowerCase().contains(pwd)) {
                redirectAttributes.addFlashAttribute("errorMessage", "Please choose a stronger password");
                return "redirect:/profile";
            }
        }
        loggedInPerson.setPassword(passwordEncoder.encode(formData.get("newPassword")));
        personRepository.save(loggedInPerson);
        redirectAttributes.addFlashAttribute("message", "Password is updated successfully");
        return "redirect:/profile";
    }
}
