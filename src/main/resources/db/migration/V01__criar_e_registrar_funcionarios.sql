CREATE TABLE employee (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    manager BOOLEAN NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(500) NOT NULL
);

INSERT INTO employee(name, manager, email, password) VALUES('John Doe', FALSE, 'john.doe@email.com', '$2a$10$GFThtl8gzC6ThyjnjOQVs.ee46LseovGIkKRdG0Xj27pXiUHjyYGa');
INSERT INTO employee(name, manager, email, password) VALUES('Jane Smith', TRUE, 'jane.smith@email.com', '$2a$10$m6lKgGI08ti97gcqC1RqEebpQKmvLBRVTw.JYI5nPXc6QYrHUvXU2');
