USE tienda;
DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS fabricante;
CREATE TABLE fabricante (
    id INT PRIMARY KEY,
    nombre VARCHAR(100)
);
CREATE TABLE producto (
    id INT PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10,2),
    FOREIGN KEY (id) REFERENCES fabricante(id)
);

SHOW TABLES;
DESCRIBE fabricante;
DESCRIBE producto;
RENAME TABLE producto TO productos_informaticos;
SHOW TABLES;
ALTER TABLE fabricante ADD COLUMN provincia VARCHAR(20) AFTER id;
ALTER TABLE fabricante ADD COLUMN garantia VARCHAR(20) AFTER nombre;
ALTER TABLE fabricante DROP COLUMN garantia;

CREATE TABLE alumnos (
    idAlumnos INT PRIMARY KEY,
    nombre VARCHAR(50)
);

INSERT INTO alumnos (idAlumnos, nombre) VALUES (1, 'Alumno 1');
INSERT INTO alumnos (idAlumnos, nombre) VALUES (2, 'Alumno 2');

INSERT INTO alumnos (idAlumnos, nombre) VALUES 
(3, 'Alumno 3'), 
(4, 'Alumno 4');

INSERT INTO alumnos VALUES 
(5, 'Alumno 5'), 
(6, 'Alumno 6');

SELECT * FROM alumnos;

ALTER TABLE alumnos ADD COLUMN notas DECIMAL(6,2);

UPDATE alumnos SET notas = 8.5 WHERE idAlumnos = 1;
UPDATE alumnos SET notas = 7.2 WHERE idAlumnos = 2;
UPDATE alumnos SET notas = 9.1 WHERE idAlumnos = 3;
UPDATE alumnos SET notas = 6.7 WHERE idAlumnos = 4;
UPDATE alumnos SET notas = 8.9 WHERE idAlumnos = 5;
UPDATE alumnos SET notas = 7.4 WHERE idAlumnos = 6;

ALTER TABLE alumnos DROP COLUMN notas;

DROP TABLE alumnos;






