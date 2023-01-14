package com.luciuswong.taxicabbooking.service;

import com.luciuswong.taxicabbooking.model.Booking;
import com.luciuswong.taxicabbooking.model.Person;
import com.luciuswong.taxicabbooking.repository.BookingRepository;
import com.luciuswong.taxicabbooking.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.luciuswong.taxicabbooking.constants.TaxiCabBookingConstants.BOOKING_STATUS_OPEN;


@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PersonRepository personRepository;

    public boolean saveBookingDetails(Booking booking) {
        boolean isSaved = false;
        booking.setStatus(BOOKING_STATUS_OPEN);
        Booking savedBooking = bookingRepository.save(booking);
        if (savedBooking != null && savedBooking.getBookingId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public Person getLoggedInPerson(String username) {
        return personRepository.readByUsername(username);
    }
}
