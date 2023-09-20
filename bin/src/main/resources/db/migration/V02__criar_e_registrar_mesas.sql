CREATE TABLE desk (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nro_desk INT NOT NULL,
    capacity INT NOT NULL,
    available BOOLEAN NOT NULL
);

INSERT INTO desk(nro_desk, capacity, available) VALUES(1, 4, TRUE);
INSERT INTO desk(nro_desk, capacity, available) VALUES(2, 5, TRUE);
