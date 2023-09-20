CREATE TABLE employee (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    manager BOOLEAN NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(8) NOT NULL
);

INSERT INTO employee(name, manager, email, password) VALUES('John Doe', FALSE, 'john.doe@email.com', 'pass123');
INSERT INTO employee(name, manager, email, password) VALUES('Jane Smith', TRUE, 'jane.smith@email.com', 'pass456');
