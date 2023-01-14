INSERT INTO `role` (`role_name`,`created_at`, `created_by`)
  VALUES ('ADMIN', CURDATE(), 'DBA');

INSERT INTO `role` (`role_name`,`created_at`, `created_by`)
  VALUES ('INDIVIDUAL', CURDATE(), 'DBA');


INSERT INTO `address` (`street_address`, `city`, `region_or_country`, `zip_code`)
  VALUES ('Main street', 'CityA', 'CountryA', 'V5X V6X');

-- BCryptPasswordEncoded value for '67890'
INSERT INTO `person` (`username`, `email`, `password`, `role_id`, `address_id`)
  VALUES ('admin', 'admin@example.com', '$2a$10$0/0INRA0ibN9mwZEjCKc9uRF5nAF4hc9kyHSXKIRr0o5.PGT8H7WW', 1, 1);


INSERT INTO `testimonial` (`name`, `testimonial_type`, `comment`, `profile_path`)
  VALUES ('Kevin Francis', 'Customer', 'Sed semper leo metus, a lacinia eros semper at. Etiam sodales orci sit amet vehicula pellentesque.', 'team1.jpg');

INSERT INTO `testimonial` (`name`, `testimonial_type`, `comment`, `profile_path`)
  VALUES ('Peter Guptill', 'Customer', 'Sed semper leo metus, a lacinia eros semper at. Etiam sodales orci sit amet vehicula pellentesque.', 'team2.jpg');

INSERT INTO `testimonial` (`name`, `testimonial_type`, `comment`, `profile_path`)
  VALUES ('Steven Wilson', 'Customer', 'Sed semper leo metus, a lacinia eros semper at. Etiam sodales orci sit amet vehicula pellentesque.', 'team1.jpg');

INSERT INTO `testimonial` (`name`, `testimonial_type`, `comment`, `profile_path`)
  VALUES ('Kevin Francis', 'Customer', 'Sed semper leo metus, a lacinia eros semper at. Etiam sodales orci sit amet vehicula pellentesque.', 'team2.jpg');

INSERT INTO `testimonial` (`name`, `testimonial_type`, `comment`, `profile_path`)
  VALUES ('Peter Guptill', 'Customer', 'Sed semper leo metus, a lacinia eros semper at. Etiam sodales orci sit amet vehicula pellentesque.', 'team1.jpg');

INSERT INTO `testimonial` (`name`, `testimonial_type`, `comment`, `profile_path`)
  VALUES ('Steven Wilson', 'Customer', 'Sed semper leo metus, a lacinia eros semper at. Etiam sodales orci sit amet vehicula pellentesque.', 'team2.jpg');