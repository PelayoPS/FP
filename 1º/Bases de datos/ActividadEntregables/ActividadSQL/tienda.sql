USE instituto;
#Apartado 1
DROP DATABASE IF EXISTS instituto;
CREATE DATABASE instituto;
#Apartado 2-4
DROP TABLE IF EXISTS PROFESOR;
DROP TABLE IF EXISTS CURSO;
DROP TABLE IF EXISTS DEPTO;
CREATE TABLE DEPTO (
    Depto_ID INT PRIMARY KEY,
    nombre VARCHAR(100),
    director VARCHAR(100),
    descripcion TEXT
);

CREATE TABLE PROFESOR (
    Prof_ID INT PRIMARY KEY,
    Depto_ID INT,
    nombre VARCHAR(100),
    direccion VARCHAR(100),
    telefono VARCHAR(15),
    FOREIGN KEY (Depto_ID) REFERENCES DEPTO(Depto_ID)
);

CREATE TABLE CURSO (
    Curso_ID INT PRIMARY KEY,
    Prof_ID INT,
    nombre VARCHAR(100),
    nivel INT,
    descripcion TEXT,
    FOREIGN KEY (Prof_ID) REFERENCES PROFESOR(Prof_ID)
);

#Apartado 5
INSERT INTO DEPTO (Depto_ID, nombre, director, descripcion) VALUES 
(1, 'Informatica', 'Manuel', 'departamento de Informática'),
(2, 'Lengua Extranjera', 'Andrés', 'departamento de Lengua Extranjera');

#Apartado 6
ALTER TABLE DEPTO ADD COLUMN subdirector VARCHAR(100) AFTER director;

#Apartado 7
SHOW DATABASES;

#Apartado 8
SHOW TABLES;

#Apartado 9
DESCRIBE PROFESOR;

#Apartado 10
RENAME TABLE DEPTO TO DEPARTAMENTO;