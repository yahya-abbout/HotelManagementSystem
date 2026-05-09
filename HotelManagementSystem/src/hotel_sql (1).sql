DROP DATABASE IF EXISTS hotel_abbot;
CREATE DATABASE hotel_abbot;
USE hotel_abbot;

CREATE TABLE customers (
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    id          INT         NOT NULL PRIMARY KEY,
    total_price DOUBLE      NOT NULL
);


CREATE TABLE employees (
    first_name VARCHAR(50) NOT NULL,
    phone      INT         NOT NULL,
    age        INT         NOT NULL,
    id         INT         NOT NULL PRIMARY KEY,
    salary     DOUBLE      NOT NULL,
    department VARCHAR(50)
);


CREATE TABLE plans (
    plan_id            INT         NOT NULL PRIMARY KEY,
    plan_name          VARCHAR(50) NOT NULL,
    room_type          VARCHAR(50) NOT NULL,
    breakfast_included BOOLEAN     NOT NULL,
    gym_access         BOOLEAN     NOT NULL,
    pool_access        BOOLEAN     NOT NULL,
    price_per_night    DOUBLE      NOT NULL
);


CREATE TABLE rooms (
    room_id      INT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    room_number  INT     NOT NULL,
    plan_id      INT     NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (plan_id) REFERENCES plans(plan_id)
);


INSERT INTO plans (plan_id, plan_name, room_type, breakfast_included, gym_access, pool_access, price_per_night)
VALUES
(1, 'King Suite',   '2 bedrooms 2 beds', TRUE,  TRUE,  TRUE,  250),
(2, 'Master Suite', '2 bedrooms 1 bed',  TRUE,  TRUE,  TRUE,  200),
(3, 'Medium Suite', '1 bedroom 2 beds',  FALSE, TRUE,  TRUE,  150),
(4, 'Medium Suite', '1 bedroom 2 beds',  FALSE, TRUE,  FALSE, 120),
(5, 'Normal Suite', '1 bedroom 1 bed',   FALSE, TRUE,  TRUE,  100),
(6, 'Normal Suite', '1 bedroom 1 bed',   FALSE, TRUE,  FALSE,  80),
(7, 'Basic',        '1 bedroom 1 bed',   FALSE, FALSE, FALSE,  60);


INSERT INTO rooms (room_number, plan_id, is_available)
VALUES
(101, 1, TRUE),
(102, 2, TRUE),
(103, 2, TRUE),
(104, 3, TRUE),
(105, 3, TRUE),
(106, 4, TRUE),
(107, 5, TRUE),
(108, 5, TRUE),
(109, 6, TRUE),
(110, 6, TRUE),
(111, 7, TRUE),
(112, 7, TRUE),
(113, 7, TRUE);


INSERT INTO employees (first_name, phone, age, id, salary, department)
VALUES ('Yahya', 1234567, 21, 123, 1000.0, 'Admin');
