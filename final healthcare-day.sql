drop database healthcare;
create database healthcare;

USE `healthcare` 

CREATE TABLE IF NOT EXISTS `healthcare`.`users` (
  `user_id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,  
 `first_name` VARCHAR(30) NOT NULL,  
  `last_name` VARCHAR(40) ,
  `dob` DATE NULL ,
  `email` VARCHAR(80)  UNIQUE NOT NULL, 
  `password` VARCHAR(300) NOT NULL,
  `phone` VARCHAR(14) UNIQUE NOT NULL,
  `reg_amount` INT NOT NULL,
  `role` ENUM('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_PATIENT') NOT NULL
);
  

CREATE TABLE IF NOT EXISTS `healthcare`.`patients` (
  `patient_id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,  
  `blood_group` ENUM('AB_NEGATIVE', 'AB_POSITIVE', 'A_NEGATIVE', 'A_POSITIVE', 'B_NEGATIVE', 'B_POSITIVE', 'O_NEGATIVE', 'O_POSITIVE') NULL DEFAULT NULL,
  `family_history` VARCHAR(500) NULL DEFAULT NULL,
  `gender` ENUM('FEMALE', 'MALE', 'OTHER') NULL DEFAULT NULL,
  `user_id` BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id));

 


CREATE TABLE IF NOT EXISTS `healthcare`.`doctors` (
  `doctor_id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,  
  `experience_in_years` INT NULL DEFAULT NULL,
  `fees` INT NOT NULL,
  `qualifications` VARCHAR(255) NULL DEFAULT NULL,
  `speciality` VARCHAR(100) NULL DEFAULT NULL,
  `user_id` BIGINT NOT NULL,
   FOREIGN KEY (user_id) REFERENCES users(user_id));


CREATE TABLE IF NOT EXISTS `healthcare`.`appointments` (
  `appointment_id`  BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
  `appointment_date_time` DATETIME(6) NULL ,
  `status` ENUM('CANCELLED', 'COMPLETED', 'SCHEDULED') NULL DEFAULT NULL,
  `doctor_id` BIGINT NOT NULL,
  `patient_id` BIGINT NOT NULL,
 UNIQUE(doctor_id,  appointment_date_time), 
 FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);


CREATE TABLE IF NOT EXISTS `healthcare`.`diagnostic_tests` (
  `test_id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `name` VARCHAR(100) NOT NULL,
  `price` INT NOT NULL);
 

CREATE TABLE IF NOT EXISTS `healthcare`.`patient_tests` (
  `patient_id` BIGINT NOT NULL,
  `test_id` BIGINT NOT NULL,
  PRIMARY KEY (`patient_id`, `test_id`),
FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
FOREIGN KEY (test_id) REFERENCES diagnostic_tests(test_id));


USE `healthcare`;

-- --------------------------
-- USERS
-- --------------------------
INSERT INTO users (first_name, last_name, dob, email, password, phone, reg_amount, role)
VALUES
-- Admin
('Admin', 'Kumar', '1980-04-12', 'admin@healthcare.in', 'admin123', '9876500000', 0, 'ROLE_ADMIN'),

-- Doctors
('Amit', 'Sharma', '1983-09-15', 'amit.sharma@healthcare.in', 'doc123', '9876500001', 500, 'ROLE_DOCTOR'),
('Priya', 'Nair', '1987-03-22', 'priya.nair@healthcare.in', 'doc123', '9876500002', 600, 'ROLE_DOCTOR'),
('Rohan', 'Mehta', '1982-01-05', 'rohan.mehta@healthcare.in', 'doc123', '9876500006', 700, 'ROLE_DOCTOR'),
('Neha', 'Reddy', '1989-11-25', 'neha.reddy@healthcare.in', 'doc123', '9876500007', 650, 'ROLE_DOCTOR'),

-- Patients
('Rahul', 'Patel', '1992-12-01', 'rahul.patel@healthcare.in', 'patient123', '9876500003', 300, 'ROLE_PATIENT'),
('Sneha', 'Iyer', '1998-06-18', 'sneha.iyer@healthcare.in', 'patient123', '9876500004', 300, 'ROLE_PATIENT'),
('Arjun', 'Deshmukh', '1995-03-09', 'arjun.deshmukh@healthcare.in', 'patient123', '9876500008', 400, 'ROLE_PATIENT'),
('Pooja', 'Singh', '2000-08-22', 'pooja.singh@healthcare.in', 'patient123', '9876500009', 350, 'ROLE_PATIENT'),
('Kiran', 'Pillai', '1993-02-11', 'kiran.pillai@healthcare.in', 'patient123', '9876500010', 250, 'ROLE_PATIENT'),
('Meena', 'Joshi', '1989-10-02', 'meena.joshi@healthcare.in', 'patient123', '9876500011', 300, 'ROLE_PATIENT');


-- --------------------------
-- DOCTORS (linked to user_id 2,3,4,5)
-- --------------------------
INSERT INTO doctors (experience_in_years, fees, qualifications, speciality, user_id)
VALUES
(12, 700, 'MBBS, MD (General Medicine)', 'General Physician', 2),
(8, 900, 'MBBS, MS (Gynaecology)', 'Gynaecologist', 3),
(15, 1000, 'MBBS, MS (Orthopedics)', 'Orthopedic Surgeon', 4),
(10, 750, 'MBBS, MD (Dermatology)', 'Dermatologist', 5);


-- --------------------------
-- PATIENTS (linked to user_id 6–11)
-- --------------------------
INSERT INTO patients (blood_group, family_history, gender, user_id)
VALUES
('B_POSITIVE', 'Father has hypertension', 'MALE', 6),
('O_POSITIVE', 'No major illness in family', 'FEMALE', 7),
('A_POSITIVE', 'Mother has diabetes', 'MALE', 8),
('AB_POSITIVE', 'No known history', 'FEMALE', 9),
('O_NEGATIVE', 'Grandfather had heart disease', 'MALE', 10),
('B_NEGATIVE', 'Asthma in family', 'FEMALE', 11);


-- --------------------------
-- DIAGNOSTIC TESTS
-- --------------------------
INSERT INTO diagnostic_tests (description, name, price)
VALUES
('Blood glucose level test (Fasting/PP)', 'Blood Sugar Test', 150),
('Full cholesterol profile test', 'Lipid Profile Test', 350),
('Complete blood count', 'CBC Test', 200),
('Chest X-ray for respiratory analysis', 'Chest X-Ray', 400),
('Thyroid hormone check (T3, T4, TSH)', 'Thyroid Test', 300);


-- --------------------------
-- APPOINTMENTS
-- --------------------------
INSERT INTO appointments (appointment_date_time, status, doctor_id, patient_id)
VALUES
('2025-11-10 09:30:00', 'SCHEDULED', 1, 1),
('2025-11-11 11:00:00', 'SCHEDULED', 1, 2),
('2025-11-12 16:15:00', 'SCHEDULED', 2, 3),
('2025-11-13 10:45:00', 'SCHEDULED', 3, 4),
('2025-11-14 17:00:00', 'SCHEDULED', 4, 5),
('2025-11-15 09:15:00', 'SCHEDULED', 2, 6);


-- --------------------------
-- PATIENT TESTS (many-to-many)
-- --------------------------
INSERT INTO patient_tests (patient_id, test_id)
VALUES
(1, 1), -- Rahul -> Blood Sugar Test
(1, 3), -- Rahul -> CBC Test
(2, 2), -- Sneha -> Lipid Profile Test
(2, 5), -- Sneha -> Thyroid Test
(3, 1), -- Arjun -> Blood Sugar Test
(3, 4), -- Arjun -> X-Ray
(4, 3), -- Pooja -> CBC Test
(5, 2), -- Kiran -> Lipid Profile Test
(5, 5), -- Kiran -> Thyroid Test
(6, 4); -- Meena -> Chest X-Ray
