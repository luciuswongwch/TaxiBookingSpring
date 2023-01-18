package com.luciuswong.taxicabbookingrestcaller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages="com.luciuswong.taxicabbookingrestcaller.proxy")
public class TaxicabbookingrestcallerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaxicabbookingrestcallerApplication.class, args);
	}
}
