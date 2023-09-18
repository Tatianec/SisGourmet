CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(250) NOT NULL,
    total DOUBLE NOT NULL
);

INSERT INTO product(name, description, total) VALUES('Burger', 'Tasty beef burger', 5.5);
INSERT INTO product(name, description, total) VALUES('Soda', 'Refreshing cola', 2.0);

