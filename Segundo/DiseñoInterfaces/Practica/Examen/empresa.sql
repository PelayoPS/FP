CREATE database if not exists empresa;

USE empresa;

CREATE TABLE if not exists employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    profesion VARCHAR(100) NOT NULL,
    telefono VARCHAR(100) NOT NULL
);

INSERT INTO employees (name, edad, profesion, telefono) VALUES ('Juan', 25, 'Ingeniero', '123456789');
INSERT INTO employees (name, edad, profesion, telefono) VALUES ('Pedro', 30, 'Doctor', '987654321');
INSERT INTO employees (name, edad, profesion, telefono) VALUES ('Maria', 35, 'Abogado', '123456789');