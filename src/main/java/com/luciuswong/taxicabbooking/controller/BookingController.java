package com.luciuswong.taxicabbooking.controller;

import com.luciuswong.taxicabbooking.model.Booking;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BookingController {
    @RequestMapping("/booking")
    public String displayBookingPage() {
        //model.addAttribute("booking", new Booking());
        return "booking.html";
    }
}
