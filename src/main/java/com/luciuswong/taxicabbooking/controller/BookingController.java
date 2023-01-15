package com.luciuswong.taxicabbooking.controller;

import com.luciuswong.taxicabbooking.model.Booking;
import com.luciuswong.taxicabbooking.model.Person;
import com.luciuswong.taxicabbooking.service.BookingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @RequestMapping("/booking")
    public String displayBookingPage(Model model, Authentication authentication) {
        Booking booking = new Booking();
        if (authentication != null && authentication.isAuthenticated()) {
            Person loggedInPerson = bookingService.getLoggedInPerson(authentication.getName());
            if (loggedInPerson != null) {
                booking.setName(authentication.getName());
                booking.setEmail(loggedInPerson.getEmail());
            }
        }
        model.addAttribute("booking", booking);
        return "booking.html";
    }

    @RequestMapping(value="/saveBooking", method=POST)
    public String saveBooking(@Valid @ModelAttribute("booking") Booking booking, Authentication authentication, Errors errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            log.error("Booking form validation error: " + errors.toString());
            return "booking.html";
        }
        if (authentication != null && authentication.isAuthenticated()) {
            bookingService.saveBookingDetails(booking, authentication.getName());
        } else {
            bookingService.saveBookingDetails(booking, null);
        }
        redirectAttributes.addFlashAttribute("message", "Booking is sent successfully");
        return "redirect:/booking";
    }
}
