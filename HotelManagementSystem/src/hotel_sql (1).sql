CREATE DATABASE hotel_abbot;

USE hotel_abbot;

CREATE TABLE customers (
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    id INT PRIMARY KEY,
    total_price DOUBLE NOT NULL
);

CREATE TABLE employees (
    first_name VARCHAR(50) NOT NULL,
    phone INT NOT NULL,
    age INT NOT NULL,
    id INT PRIMARY KEY,
    salary DOUBLE NOT NULL,
    department VARCHAR(50)
);

CREATE TABLE plans(
    plan_id INT PRIMARY KEY,
    plan_name VARCHAR(50) NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    breakfast_included BOOLEAN NOT NULL,
    gym_access BOOLEAN NOT NULL,
    pool_access BOOLEAN NOT NULL,
    price_per_night double NOT NULL
);
    
INSERT INTO plans (plan_id, plan_name, room_type, breakfast_included, gym_access, pool_access, price_per_night)
VALUES
(1,'King Suite', '2 bedrooms 2 beds', true, true, true, 250),
(2,'Master Suite', '2 bedrooms 1 bed', true, true, true, 200),
(3,'Medium Suite', '1 bedroom 2 beds', false, true, true, 150),
(4,'Medium Suite', '1 bedroom 2 beds', false, true, false, 120),
(5,'Normal Suite', '1 bedroom 1 bed', false, true, true, 100),
(6,'Normal Suite', '1 bedroom 1 bed', false, true, false, 80),
(7,'Basic', '1 bedroom 1 bed', false, false, false, 60);   

CREATE TABLE rooms(
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number INT NOT NULL,
    plan_id INT NOT NULL,
    is_available BOOLEAN,
    FOREIGN KEY (plan_id) REFERENCES plans(plan_id)
);

INSERT INTO rooms (room_number, plan_id, is_available) VALUES
(101, 1, true),
(102, 2, true),
(103, 2, true),
(104, 3, true),
(105, 3, true),
(106, 4, true),
(107, 5, true),
(108, 5, true),
(109, 6, true),
(110, 6, true),
(111, 7, true),
(112, 7, true),
(113, 7, true);
 
-- Insert a new customer used in Admin
INSERT INTO customers (first_name, last_name, id, plan, total_price)
VALUES (?, ?, ?, ?, ?);

-- Delete a customer by ID used in Admin
DELETE FROM customers WHERE id = ?;
