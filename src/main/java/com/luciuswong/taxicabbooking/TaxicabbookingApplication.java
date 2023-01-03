package com.luciuswong.taxicabbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.luciuswong.taxicabbooking.repository")
@EntityScan("com.luciuswong.taxicabbooking.model")
public class TaxicabbookingApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaxicabbookingApplication.class, args);
	}
}
