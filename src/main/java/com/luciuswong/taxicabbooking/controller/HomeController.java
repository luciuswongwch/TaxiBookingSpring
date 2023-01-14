package com.luciuswong.taxicabbooking.controller;

import com.luciuswong.taxicabbooking.model.Testimonial;
import com.luciuswong.taxicabbooking.model.Testimonial.TestimonialType;
import com.luciuswong.taxicabbooking.repository.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private TestimonialRepository testimonialRepository;

    @RequestMapping(value={"", "/", "/home"})
    public String displayHomePage(Model model) {
        List<Testimonial> testimonials = testimonialRepository.findByTestimonialType(TestimonialType.Customer);
        model.addAttribute("testimonials", testimonials);
        return "index.html";
    }
}
