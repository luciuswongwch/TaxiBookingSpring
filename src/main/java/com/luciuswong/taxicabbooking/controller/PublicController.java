package com.luciuswong.taxicabbooking.controller;

import com.luciuswong.taxicabbooking.model.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PublicController {
    @RequestMapping(value="/register", method={RequestMethod.GET})
    public String displayRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register.html";
    }

    @RequestMapping(value="/createUser", method={RequestMethod.POST})
    public String createUser(@Valid @ModelAttribute("user") User user, Errors errors) {
        if (errors.hasErrors()) {
            return "register.html";
        }
        return "redirect:/login?register=true";
    }
}
