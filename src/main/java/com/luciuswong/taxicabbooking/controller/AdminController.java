package com.luciuswong.taxicabbooking.controller;

import com.luciuswong.taxicabbooking.config.CustomProperties;
import com.luciuswong.taxicabbooking.constants.TaxiCabBookingConstants;
import com.luciuswong.taxicabbooking.model.Booking;
import com.luciuswong.taxicabbooking.model.Contact;
import com.luciuswong.taxicabbooking.repository.BookingRepository;
import com.luciuswong.taxicabbooking.repository.ContactRepository;
import com.luciuswong.taxicabbooking.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@Controller
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CustomProperties customProperties;

    @RequestMapping("/contact")
    public String displayAdminContact() {
        return "redirect:/admin/contact/page/1?sortField=createdAt&sortDir=asc";
    }
    @RequestMapping("/booking")
    public String displayAdminBooking() {
        return "redirect:/admin/booking/page/1?sortField=createdAt&sortDir=asc";
    }
    @RequestMapping("/contact/page/{pageNumber}")
    public ModelAndView displayAdminContact(Model model, @PathVariable(name="pageNumber") int pageNumber,
                                      @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
        Page<Contact> contactPages = adminService.findContactsAndPaginate(customProperties.getPageSize(), pageNumber, sortField, sortDir);
        ModelAndView modelAndView = new ModelAndView("admin_contact.html");
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", contactPages.getTotalPages());
        model.addAttribute("totalMsgs", contactPages.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contacts", contactPages.getContent());
        return modelAndView;
    }
    @RequestMapping("/booking/page/{pageNumber}")
    public ModelAndView displayAdminBooking(Model model, @PathVariable(name="pageNumber") int pageNumber,
                                      @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
        Page<Booking> bookingPages = adminService.findBookingsAndPaginate(customProperties.getPageSize(), pageNumber, sortField, sortDir);
        ModelAndView modelAndView = new ModelAndView("admin_booking.html");
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", bookingPages.getTotalPages());
        model.addAttribute("totalMsgs", bookingPages.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("bookings", bookingPages.getContent());
        return modelAndView;
    }

    @GetMapping("/closeContactMsg")
    public ModelAndView closeContactMsg(@RequestParam int contactId, RedirectAttributes redirectAttributes) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        if (contact.isPresent()) {
            contact.get().setStatus(TaxiCabBookingConstants.CONTACT_MESSAGE_CLOSED);
            contactRepository.save(contact.get());
            redirectAttributes.addFlashAttribute("message", "Status of contact message with contactId " + contactId + " is changed to CLOSED");
        }
        return new ModelAndView("redirect:/admin/contact");
    }
    @GetMapping("/deleteContactMsg")
    public ModelAndView deleteContactMsg(@RequestParam int contactId, RedirectAttributes redirectAttributes) {
        contactRepository.deleteById(contactId);
        redirectAttributes.addFlashAttribute("message", "Contact message with contactId " + contactId + " is deleted");
        return new ModelAndView("redirect:/admin/contact");
    }
    @GetMapping("/closeBooking")
    public ModelAndView closeBookingMsg(@RequestParam int bookingId, RedirectAttributes redirectAttributes) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            booking.get().setStatus(TaxiCabBookingConstants.BOOKING_STATUS_CLOSED);
            bookingRepository.save(booking.get());
            redirectAttributes.addFlashAttribute("message", "Status of booking with bookingId " + bookingId + " is changed to CLOSED");
        }
        return new ModelAndView("redirect:/admin/booking");
    }
    @GetMapping("/deleteBooking")
    public ModelAndView deleteBooking(@RequestParam int bookingId, RedirectAttributes redirectAttributes) {
        bookingRepository.deleteById(bookingId);
        redirectAttributes.addFlashAttribute("message", "Booking with bookingId " + bookingId + " is deleted");
        return new ModelAndView("redirect:/admin/booking");
    }
}
