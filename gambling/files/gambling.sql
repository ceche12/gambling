drop database if exists gambling;
CREATE DATABASE gambling;
use gambling;



CREATE TABLE sorteo (
    id integer NOT NULL AUTO_INCREMENT primary key ,
    fecha_apertura DATE NOT NULL,
    fecha_cierre DATE NOT NULL,
    fecha_hora_celebracion TIMESTAMP NOT NULL,
    resultado VARCHAR(100),
    tipo VARCHAR(100) NOT NULL
    
);


CREATE TABLE jugador (
    correo_electronico VARCHAR(100) NOT NULL PRIMARY KEY,
    contraseña VARCHAR(100) NOT NULL,
    dni VARCHAR(10) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    dinero DECIMAL(18,2) NOT NULL
);

CREATE TABLE apuesta (
    id SERIAL PRIMARY KEY,
    fecha_apuesta DATE NOT NULL,
    combinacion VARCHAR(100) NOT NULL,
    tipo ENUM('LOTERIA','QUINIELA','GORDO','PRIMITIVA','EUROMILLON') NOT NULL,
    precio DECIMAL(6,2) NOT NULL,
    ganado DECIMAL(10,2),
    reintegro INTEGER ,
    estrellas varchar(100),
    num_clave INTEGER ,
    complementario INTEGER ,
    id_sorteo integer not null ,
    correo_jugador VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_sorteo) REFERENCES sorteo (id),
    FOREIGN KEY (correo_jugador) REFERENCES jugador (correo_electronico)
);


alter table apuesta ADD INDEX fecha_apuesta (fecha_apuesta);



INSERT INTO jugador (correo_electronico, contraseña, dni, telefono, dinero)
VALUES ('jugador1@example.com', 'contraseña1', '1234567890', '1234567890', 100.00);


INSERT INTO apuesta (fecha_apuesta, combinacion, tipo, precio, ganado, reintegro, estrellas, num_clave, complementario, id_sorteo, correo_jugador)
VALUES ('2023-06-01', '1-2-3-4-5', 'LOTERIA', 10.00, NULL, 0, NULL, NULL, NULL, 1, 'jugador1@example.com');