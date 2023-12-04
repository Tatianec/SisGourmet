CREATE TABLE pedido (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_date DATE NOT NULL,
    employee_id BIGINT NOT NULL,
    desk_id BIGINT NOT NULL,
    total DOUBLE NOT NULL,
    observation VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Em Aberto', 
    FOREIGN KEY (employee_id) REFERENCES employee(id),
    FOREIGN KEY (desk_id) REFERENCES desk(id)
);

INSERT INTO pedido(order_date, employee_id, desk_id, total, observation, status) VALUES('2023-09-18', 1, 1, 7.5, 'Lunch for John', 'Em Aberto');
