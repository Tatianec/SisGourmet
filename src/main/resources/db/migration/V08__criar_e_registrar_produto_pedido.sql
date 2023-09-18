CREATE TABLE order_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    qtd_items INT NOT NULL,
    id_order BIGINT NOT NULL,
    id_product BIGINT NOT NULL,
    FOREIGN KEY (id_order) REFERENCES pedido(id),
    FOREIGN KEY (id_product) REFERENCES product(id)
);

INSERT INTO order_items(qtd_items, id_order, id_product) VALUES(1, 1, 1); -- 1 Burger for Order #1
INSERT INTO order_items(qtd_items, id_order, id_product) VALUES(1, 1, 2); -- 1 Soda for Order #1
