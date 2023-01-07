package com.luciuswong.taxicabbooking.repository;

import com.luciuswong.taxicabbooking.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
}
