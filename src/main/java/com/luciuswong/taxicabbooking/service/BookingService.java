package com.luciuswong.taxicabbooking.service;

import com.luciuswong.taxicabbooking.model.Booking;
import com.luciuswong.taxicabbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public boolean saveBookingDetails(Booking booking) {
        boolean isSaved = false;
        Booking savedBooking = bookingRepository.save(booking);
        if (savedBooking != null && savedBooking.getBookingId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
