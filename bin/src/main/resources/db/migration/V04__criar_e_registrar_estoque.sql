CREATE TABLE stock (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    qtd_stock INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

INSERT INTO stock(product_id, qtd_stock) VALUES(1, 100);
INSERT INTO stock(product_id, qtd_stock) VALUES(2, 200);
