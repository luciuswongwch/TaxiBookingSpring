package com.luciuswong.taxicabbooking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Testimonial extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    private int testimonialId;

    @NotBlank(message="Name must not be blank")
    private String name;
    @Enumerated(EnumType.STRING)
    private TestimonialType testimonialType;
    @NotBlank(message="Comment must not be blank")
    private String comment;
    @NotBlank(message="Profile path must not be blank")
    private String profilePath;

    public enum TestimonialType {
        Customer, Driver
    }
}