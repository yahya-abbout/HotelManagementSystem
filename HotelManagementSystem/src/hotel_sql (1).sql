CREATE TABLE customers (
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    id          INT PRIMARY KEY,
    plan        INT NOT NULL,
    total_price DOUBLE NOT NULL
);

CREATE TABLE employees (
    first_name  VARCHAR(50) NOT NULL,
    phone       INT NOT NULL,
    age         INT NOT NULL,
    id          INT PRIMARY KEY,
    salary      DOUBLE NOT NULL,
    department  VARCHAR(50)
);


-- Insert a new employee used in HR
INSERT INTO employees (first_name, phone, age, id, salary, department)
VALUES (?, ?, ?, ?, ?, ?);

-- Delete an employee by ID used in HR
DELETE FROM employees WHERE id = ?;

-- Update an employee's name and salary used in HR
UPDATE employees
SET first_name = ?, salary = ?
WHERE id = ?;

-- Insert a new customer used in Admin
INSERT INTO customers (first_name, last_name, id, plan, total_price)
VALUES (?, ?, ?, ?, ?);

-- Delete a customer by ID used in Admin
DELETE FROM customers WHERE id = ?;
