package com.luciuswong.taxicabbooking.repository;

import com.luciuswong.taxicabbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
