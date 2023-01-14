package com.luciuswong.taxicabbooking.controller;

import com.luciuswong.taxicabbooking.repository.BookingRepository;
import com.luciuswong.taxicabbooking.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @RequestMapping("/portal")
    public String displayAdminPage(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());
        model.addAttribute("bookings", bookingRepository.findAll());
        return "admin.html";
    }
}
