package com.luciuswong.taxicabbooking.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


@Controller
public class LoginController {
    @RequestMapping(value="/login", method={RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(@RequestParam(value="error", required=false) String error,
                                   @RequestParam(value="logout", required=false) String logout,
                                   @RequestParam(value="register", required=false) String register, Model model, Principal user) {
        if (user != null) {
            return "redirect:/profile";
        }
        if (error != null) {
            model.addAttribute("error", "Invalid login credentials");
        }
        if (logout != null) {
            model.addAttribute("logout", "You have successfully logged out");
        }
        if (register != null)  {
            model.addAttribute("register", "You have successfully registered, please login with the new credentials");
        }
        return "login.html";
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}
