INSERT INTO `role` (`role_name`, `created_at`, `created_by`)
  VALUES ('ADMIN', CURDATE(), 'DBA');

INSERT INTO `role` (`role_name`, `created_at`, `created_by`)
  VALUES ('INDIVIDUAL', CURDATE(), 'DBA');


INSERT INTO `address` (`street_address`, `city`, `region_or_country`, `zip_code`)
  VALUES ('Main street', 'CityA', 'CountryA', 'V5X V6X');

INSERT INTO `address` (`street_address`, `city`, `region_or_country`, `zip_code`)
  VALUES ('Main street', 'CityA', 'CountryA', 'V5X V6X');

-- BCryptPasswordEncoded value for '67890'
INSERT INTO `person` (`username`, `email`, `password`, `role_id`, `address_id`)
  VALUES ('admin', 'admin@example.com', '$2a$10$0/0INRA0ibN9mwZEjCKc9uRF5nAF4hc9kyHSXKIRr0o5.PGT8H7WW', 1, 1);

-- BCryptPasswordEncoded value for '67890'
INSERT INTO `person` (`username`, `email`, `password`, `role_id`, `address_id`)
  VALUES ('user', 'user@example.com', '$2a$10$0/0INRA0ibN9mwZEjCKc9uRF5nAF4hc9kyHSXKIRr0o5.PGT8H7WW', 2, 2);


INSERT INTO `booking` (`name`, `phone_number`, `email`, `cab_type`, `pick_up_date`, `pick_up_time`, `pick_up_location`, `drop_off_location`, `number_of_passengers`, `direction`, `status`, `created_at`, `created_by`)
  VALUES ('user1', '236 111 1234', 'user1@example.com', 'Elite', '01/15/2023', '12 : 00 PM', 'Location1', 'Location101', 1, 'One_way', 'OPEN', CURDATE(), 'DBA');

INSERT INTO `booking` (`name`, `phone_number`, `email`, `cab_type`, `pick_up_date`, `pick_up_time`, `pick_up_location`, `drop_off_location`, `number_of_passengers`, `direction`, `status`, `created_at`, `created_by`)
  VALUES ('user2', '236 122 1234', 'user2@example.com', 'Sedan', '01/16/2023', '01 : 00 PM', 'Location2', 'Location102', 2, 'Return', 'OPEN', TIMESTAMPADD(DAY, 1, CURRENT_DATE), 'DBA');

INSERT INTO `booking` (`name`, `phone_number`, `email`, `cab_type`, `pick_up_date`, `pick_up_time`, `pick_up_location`, `drop_off_location`, `number_of_passengers`, `direction`, `status`, `created_at`, `created_by`)
  VALUES ('user3', '236 133 1234', 'user3@example.com', 'Mini', '01/17/2023', '02 : 00 PM', 'Location3', 'Location103', 3, 'One_way', 'OPEN', TIMESTAMPADD(DAY, 2, CURRENT_DATE), 'DBA');

INSERT INTO `booking` (`name`, `phone_number`, `email`, `cab_type`, `pick_up_date`, `pick_up_time`, `pick_up_location`, `drop_off_location`, `number_of_passengers`, `direction`, `status`, `created_at`, `created_by`)
  VALUES ('user4', '236 144 1234', 'user4@example.com', 'Elite', '01/18/2023', '03 : 00 PM', 'Location4', 'Location104', 4, 'Return', 'OPEN', TIMESTAMPADD(DAY, 3, CURRENT_DATE), 'DBA');

INSERT INTO `booking` (`name`, `phone_number`, `email`, `cab_type`, `pick_up_date`, `pick_up_time`, `pick_up_location`, `drop_off_location`, `number_of_passengers`, `direction`, `status`, `created_at`, `created_by`)
  VALUES ('user5', '236 155 1234', 'user5@example.com', 'Sedan', '01/19/2023', '04 : 00 PM', 'Location5', 'Location105', 5, 'One_way', 'OPEN', TIMESTAMPADD(DAY, 4, CURRENT_DATE), 'DBA');

INSERT INTO `booking` (`name`, `phone_number`, `email`, `cab_type`, `pick_up_date`, `pick_up_time`, `pick_up_location`, `drop_off_location`, `number_of_passengers`, `direction`, `status`, `created_at`, `created_by`)
  VALUES ('user6', '236 166 1234', 'user6@example.com', 'Mini', '01/20/2023', '05 : 00 PM', 'Location6', 'Location106', 1, 'Return', 'OPEN', TIMESTAMPADD(DAY, 5, CURRENT_DATE), 'DBA');

INSERT INTO `booking` (`name`, `phone_number`, `email`, `cab_type`, `pick_up_date`, `pick_up_time`, `pick_up_location`, `drop_off_location`, `number_of_passengers`, `direction`, `status`, `created_at`, `created_by`)
  VALUES ('user7', '236 177 1234', 'user7@example.com', 'Elite', '01/21/2023', '06 : 00 PM', 'Location7', 'Location107', 2, 'One_way', 'OPEN', TIMESTAMPADD(DAY, 6, CURRENT_DATE), 'DBA');

INSERT INTO `booking` (`name`, `phone_number`, `email`, `cab_type`, `pick_up_date`, `pick_up_time`, `pick_up_location`, `drop_off_location`, `number_of_passengers`, `direction`, `status`, `created_at`, `created_by`)
  VALUES ('user8', '236 188 1234', 'user8@example.com', 'Sedan', '01/22/2023', '07 : 00 PM', 'Location8', 'Location108', 3, 'Return', 'OPEN', TIMESTAMPADD(DAY, 7, CURRENT_DATE), 'DBA');


INSERT INTO `contact` (`name`, `subject`, `email`, `message`, `status`, `created_at`, `created_by`)
  VALUES ('user1', 'subject of contact message 1', 'user1@example.com', 'contact message 1', 'OPEN', CURDATE(), 'DBA');

INSERT INTO `contact` (`name`, `subject`, `email`, `message`, `status`, `created_at`, `created_by`)
  VALUES ('user2', 'subject of contact message 2', 'user2@example.com', 'contact message 2', 'OPEN', TIMESTAMPADD(DAY, 1, CURRENT_DATE), 'DBA');

INSERT INTO `contact` (`name`, `subject`, `email`, `message`, `status`, `created_at`, `created_by`)
  VALUES ('user3', 'subject of contact message 3', 'user3@example.com', 'contact message 3', 'OPEN', TIMESTAMPADD(DAY, 2, CURRENT_DATE), 'DBA');

INSERT INTO `contact` (`name`, `subject`, `email`, `message`, `status`, `created_at`, `created_by`)
  VALUES ('user4', 'subject of contact message 4', 'user4@example.com', 'contact message 4', 'OPEN', TIMESTAMPADD(DAY, 3, CURRENT_DATE), 'DBA');

INSERT INTO `contact` (`name`, `subject`, `email`, `message`, `status`, `created_at`, `created_by`)
  VALUES ('user5', 'subject of contact message 5', 'user5@example.com', 'contact message 5', 'OPEN', TIMESTAMPADD(DAY, 4, CURRENT_DATE), 'DBA');

INSERT INTO `contact` (`name`, `subject`, `email`, `message`, `status`, `created_at`, `created_by`)
  VALUES ('user6', 'subject of contact message 6', 'user6@example.com', 'contact message 6', 'OPEN', TIMESTAMPADD(DAY, 5, CURRENT_DATE), 'DBA');

INSERT INTO `contact` (`name`, `subject`, `email`, `message`, `status`, `created_at`, `created_by`)
  VALUES ('user7', 'subject of contact message 7', 'user7@example.com', 'contact message 7', 'OPEN', TIMESTAMPADD(DAY, 6, CURRENT_DATE), 'DBA');

INSERT INTO `contact` (`name`, `subject`, `email`, `message`, `status`, `created_at`, `created_by`)
  VALUES ('user8', 'subject of contact message 8', 'user8@example.com', 'contact message 8', 'OPEN', TIMESTAMPADD(DAY, 7, CURRENT_DATE), 'DBA');


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