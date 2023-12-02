CREATE TABLE pedido_product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_pedido BIGINT NOT NULL,
    id_product BIGINT NOT NULL,
    qtd_sold INT NOT NULL, 
    FOREIGN KEY (id_pedido) REFERENCES pedido(id),
    FOREIGN KEY (id_product) REFERENCES product(id)
);

INSERT INTO pedido_product(id_pedido, id_product, qtd_sold) VALUES(1, 1, 2);  -- 2 Burgers
INSERT INTO pedido_product(id_pedido, id_product, qtd_sold) VALUES(1, 2, 3);  -- 3 Sodas