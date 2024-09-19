SET GLOBAL LOG_BIN_TRUST_FUNCTION_CREATORS=1;
DROP DATABASE IF EXISTS bdPruebaDisparadores;
CREATE DATABASE bdPruebaDisparadores;
USE bdPruebaDisparadores;

DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios(
    nombre varchar(30),
    clave varchar(30),
    primary key (nombre)
);

DROP TABLE IF EXISTS clavesanteriores;
CREATE TABLE clavesanteriores(
    numero int auto_increment,
    nombre varchar(30),
    clave varchar(30),
    primary key (numero)
);

-- Ejercicio 1
DELIMITER %%
DROP TRIGGER IF EXISTS BEFORE_USUARIOS_UPDATE %%
CREATE TRIGGER BEFORE_USUARIOS_UPDATE
BEFORE UPDATE
ON usuarios
FOR EACH ROW
BEGIN
    INSERT INTO clavesanteriores (nombre, clave)
    VALUES (OLD.nombre, OLD.clave);
END %%
DELIMITER ;

INSERT INTO usuarios(nombre,clave) VALUES ('marcos','123abc');

UPDATE usuarios SET clave='999zzz' WHERE nombre='marcos';

SELECT * FROM clavesanteriores;

UPDATE usuarios SET clave='123456' WHERE nombre='marcos';

SELECT * FROM clavesanteriores;

SELECT * FROM usuarios;

DROP TABLE IF EXISTS libros;
CREATE TABLE libros(
  codigo int auto_increment,
  titulo varchar(50),
  autor varchar(50),
  editorial varchar(30),
  precio float, 
  stock int,
  primary key (codigo)
 );

DROP TABLE IF EXISTS ventas;
CREATE TABLE ventas(
  numero int auto_increment,
  codigolibro int,
  precio float,
  cantidad int,
  primary key (numero)
 );

INSERT INTO libros(titulo, autor, editorial, precio, stock)
  VALUES('Uno','Richard Bach','Planeta',15,100);   
 INSERT INTO libros(titulo, autor, editorial, precio, stock)
  VALUES('Ilusiones','Richard Bach','Planeta',18,50);
 INSERT INTO libros(titulo, autor, editorial, precio, stock)
  VALUES('El aleph','Borges','Emece',25,200);
 INSERT INTO libros(titulo, autor, editorial, precio, stock)
  VALUES('Aprenda PHP','Mario Molina','Emece',45,200);

INSERT INTO ventas(codigolibro, precio, cantidad) VALUES(1, 15, 1);

DROP TRIGGER IF EXISTS BEFORE_VENTAS_INSERT;

-- Ejercicio 2
DELIMITER $$
CREATE TRIGGER BEFORE_VENTAS_INSERT
BEFORE INSERT
ON ventas
FOR EACH ROW
BEGIN
    UPDATE libros
    SET stock = libros.stock - NEW.cantidad
    WHERE NEW.codigolibro = libros.codigo;
END $$
DELIMITER ;

-- Ejercicio 3
DROP TRIGGER IF EXISTS BEFORE_VENTAS_DELETE;

DELIMITER $$
CREATE TRIGGER BEFORE_VENTAS_DELETE
BEFORE DELETE
ON ventas
FOR EACH ROW
BEGIN
    UPDATE libros
    SET stock = libros.stock + OLD.cantidad
    WHERE OLD.codigolibro = libros.codigo;
END $$
DELIMITER ;

DROP TRIGGER IF EXISTS BEFORE_DEVOLUCION_DELETE;

DELIMITER $$
CREATE TRIGGER BEFORE_DEVOLUCION_DELETE
BEFORE DELETE
ON ventas
FOR EACH ROW
BEGIN
    UPDATE libros
    SET stock = CASE
        WHEN libros.stock + OLD.cantidad >= 0 THEN libros.stock + OLD.cantidad
        ELSE 0 END
    WHERE OLD.codigolibro = libros.codigo;
END $$
DELIMITER ;

INSERT INTO ventas(codigolibro, precio, cantidad) VALUES(1, 15, 1);

SELECT * FROM libros;

DELETE FROM ventas WHERE numero=1;

SELECT * FROM libros;

CREATE TABLE empleados
(dni char(4) PRIMARY KEY,
Nomemp varchar(15),
Mgr char(4),
Salario integer DEFAULT 1000,
Usuario integer DEFAULT 1000,
Fecha date );

DROP TRIGGER IF EXISTS BEFORE_EMPLEADOS_UPDATE;

-- Ejercicio 4
DELIMITER $$
CREATE TRIGGER BEFORE_EMPLEADOS_UPDATE
BEFORE UPDATE
ON empleados
FOR EACH ROW
BEGIN
    IF NEW.salario > OLD.salario * 1.2 THEN
        SIGNAL SQLSTATE '45000'
        SET message_text = 'Error: El salario no puede aumentar más de un 20%';
    END IF;
END $$
DELIMITER ;