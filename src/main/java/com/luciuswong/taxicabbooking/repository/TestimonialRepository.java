package com.luciuswong.taxicabbooking.repository;

import com.luciuswong.taxicabbooking.model.Testimonial;
import com.luciuswong.taxicabbooking.model.Testimonial.TestimonialType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestimonialRepository extends CrudRepository<Testimonial, Integer> {
    List<Testimonial> findByTestimonialType(TestimonialType testimonialType);
}
