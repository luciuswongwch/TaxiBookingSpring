package com.luciuswong.taxicabbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    private int bookingId;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;
    @NotBlank(message="Phone number must not be blank")
    private String phoneNumber;
    @Email(message="Email address is invalid")
    private String email;
    @NotBlank(message="Pick-up date must not be blank")
    private String pickUpDate;
    @NotBlank(message="Pick-up time must not be blank")
    private String pickUpTime;
    @NotBlank(message="Pick-up location must not be blank")
    private String pickUpLocation;
    @NotBlank(message="Drop-off location must not be blank")
    private String dropOffLocation;
    @NotBlank(message="Number of passengers must not be blank")
    @Size(min=1, max=5, message="Number of passengers must be within 1 to 5")
    private int numberOfPassengers;
    @NotBlank(message="Direction must not be blank")
    private String direction;
}
