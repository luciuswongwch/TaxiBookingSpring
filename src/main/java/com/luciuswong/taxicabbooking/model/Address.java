package com.luciuswong.taxicabbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    private int addressId;

    @NotBlank(message="Street address must not be blank")
    @Size(min=5, message="Street address must be at least 5 characters long")
    private String streetAddress;

    @NotBlank(message="City must not be blank")
    @Size(min=5, message="City must be at least 5 characters long")
    private String city;

    @NotBlank(message="Region/Country must not be blank")
    @Size(min=5, message="Region/Country must be at least 5 characters long")
    private String regionOrCountry;

    @NotBlank(message="Zip code must not be blank")
    @Size(min=5, message="Zip code must be at least 5 characters long")
    private String zipCode;
}
