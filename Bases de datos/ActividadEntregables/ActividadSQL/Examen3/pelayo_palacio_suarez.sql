-- Pelayo Palacio Suarez
SET GLOBAL log_bin_trust_function_creators = 1;

-- Pregunta 1
-- drop si ya existe
DROP FUNCTION IF EXISTS sumar_precio_funcion;
-- funcion
DELIMITER $$
CREATE FUNCTION sumar_precio_funcion() RETURNS DECIMAL(10, 2)
BEGIN
    DECLARE total_price DECIMAL(10, 2);
    SELECT ROUND(SUM(p.precio), 2) INTO total_price
    FROM productos p;
    RETURN total_price;
END $$
DELIMITER ;
-- llamada a la funcion
SELECT sumar_precio_funcion() AS total_price;

-- Pregunta 2
-- drop si existe
DROP PROCEDURE IF EXISTS evaluar_suma_precio;
-- procedimiento
DELIMITER !!
CREATE PROCEDURE evaluar_suma_precio()
BEGIN
    DECLARE total_price DECIMAL(10, 2);
    DECLARE message VARCHAR(50);

    SELECT sumar_precio_funcion() INTO total_price;

    IF total_price < 1000 THEN
        SET message = 'la suma es demasiado pequeña';
    ELSE
        SET message = 'la suma no es demasiado grande';
    END IF;

    SELECT total_price, message;
END !!
DELIMITER ;

-- llama al procedimiento
CALL evaluar_suma_precio();

-- Pregunta 3
-- drop si existe
DROP VIEW IF EXISTS sinFamilia;
-- crea la vista
CREATE VIEW sinFamilia AS
SELECT codProducto, nombre, precio, marca FROM productos;

-- muestra la vista
SELECT * FROM sinFamilia;

-- Pregunta 4
-- drop si existe
DROP VIEW IF EXISTS conMarca;
-- crea la vista
CREATE VIEW conMarca AS
SELECT p.nombre, f.nombre as familia, marca
FROM productos p
JOIN familias f ON p.codFamilia = f.codFamilia
WHERE marca IS NOT NULL;
-- muestra la vista
SELECT * FROM conMarca;


-- Pregunta 5
-- drop si existe
DROP PROCEDURE IF EXISTS bucleSumarNumeros;
-- procedimiento
DELIMITER ¿¿
CREATE PROCEDURE bucleSumarNumeros(IN num INT, OUT suma INT)
BEGIN
  DECLARE i INT DEFAULT 0;
  SET suma := 0;
  WHILE i < num DO
    SET suma := suma + i;
    SET i := i + 1;
  END WHILE;
END ¿¿
DELIMITER ;
-- llama al procedimiento
CALL bucleSumarNumeros(2, @suma);
SELECT @suma;