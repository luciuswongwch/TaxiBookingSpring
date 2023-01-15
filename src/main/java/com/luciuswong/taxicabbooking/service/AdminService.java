package com.luciuswong.taxicabbooking.service;

import com.luciuswong.taxicabbooking.model.Booking;
import com.luciuswong.taxicabbooking.model.Contact;
import com.luciuswong.taxicabbooking.repository.BookingRepository;
import com.luciuswong.taxicabbooking.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public Page<Contact> findContactsAndPaginate(int pageSize, int pageNum, String sortField, String sortDirection) {
        Pageable pageable = PageRequest.of(
                pageNum - 1,
                pageSize,
                sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        return contactRepository.findAll(pageable);
    }

    public Page<Booking> findBookingsAndPaginate(int pageSize, int pageNum, String sortField, String sortDirection) {
        Pageable pageable = PageRequest.of(
                pageNum - 1,
                pageSize,
                sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        return bookingRepository.findAll(pageable);
    }
}
