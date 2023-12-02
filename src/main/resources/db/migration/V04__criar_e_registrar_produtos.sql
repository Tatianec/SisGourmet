CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(250) NOT NULL,
    total DOUBLE NOT NULL,
    estoque BOOLEAN,
    qtd_items INT NOT NULL
);

INSERT INTO product(name, description, total, estoque, qtd_items) VALUES('Burger', 'Tasty beef burger', 5.5, true, 10);
INSERT INTO product(name, description, total, estoque, qtd_items) VALUES('Soda', 'Refreshing cola', 2.0, true, 20);
