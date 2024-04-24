use bdSupermercado;
set global log_bin_trust_function_creators = 1;
/*
Escriba un procedimiento llamado listar_productos que reciba como entrada el código  de la familia y 
muestre un listado de todos los productos que existen dentro de esa familia. Este procedimiento no devuelve 
ningún parámetro de salida, lo que hace es mostrar el listado de los productos.
*/

DELIMITER $$
DROP PROCEDURE IF EXISTS listar_productos$$
CREATE PROCEDURE listar_productos(IN codFamilia VARCHAR(50))
BEGIN
  SELECT *
  FROM productos
  WHERE productos.codFamilia = codFamilia;
END
$$

/* usa el procedimiento listar_productos */
DELIMITER ??
CALL listar_productos(1);
SELECT * FROM productos;
??



####################################################
/* Procedimiento que recibe el código de una familia y muestra el número de productos
que pertenecen a esa familia
*/

DELIMITER $$
DROP PROCEDURE IF EXISTS contar_productos$$
CREATE PROCEDURE contar_productos(IN codFamilia VARCHAR(50))
BEGIN
  SELECT COUNT(*)
  FROM productos
  WHERE productos.codFamilia = codFamilia;
END
$$

/* usa el procedimiento contar_productos */
DELIMITER ??
CALL contar_productos(1);
SELECT * FROM productos;
??





###########################################

/* Función que recibe el código de una familia y devuelve el número de productos
que pertenecen a esa familia
*/

DELIMITER $$
DROP FUNCTION IF EXISTS contar_productos$$
CREATE FUNCTION contar_productos(codFamilia VARCHAR(50)) RETURNS INT
BEGIN
  DECLARE numProductos INT;
  SELECT COUNT(*)
  INTO numProductos
  FROM productos
  WHERE productos.codFamilia = codFamilia;
  RETURN numProductos;
END
$$

/* usa la función contar_productos */
DELIMITER ??
SELECT contar_productos(1);
SELECT * FROM productos;
??










###################################################
/* Función que recibe el código de una familia y devuelva el número de productos
que pertenecen a esa familia utilizando SELECT ... INTO
*/

DELIMITER $$
DROP FUNCTION IF EXISTS contar_productos$$
CREATE FUNCTION contar_productos(codFamilia VARCHAR(50)) RETURNS INT
BEGIN
  DECLARE numProductos INT;
  SELECT COUNT(*)
  INTO numProductos
  FROM productos
  WHERE productos.codFamilia = codFamilia;
  RETURN numProductos;
END
$$

/* usa la función contar_productos */
DELIMITER ??
SELECT contar_productos(1);
SELECT * FROM productos;
??



###################################################
/* Procedimiento que recibe el código de una familia y devuelve el número de productos
que pertenecen a esa familia a través de una variable de salida
*/

DELIMITER $$
DROP PROCEDURE IF EXISTS contar_productos$$
CREATE PROCEDURE contar_productos(IN codFamilia VARCHAR(50), OUT numProductos INT)
BEGIN
  SELECT COUNT(*)
  INTO numProductos
  FROM productos
  WHERE productos.codFamilia = codFamilia;
END
$$

/* usa el procedimiento contar_productos */
DELIMITER ??
CALL contar_productos(1, @numProductos);
SELECT @numProductos;
SELECT * FROM productos;
??



##########################################################
/*
Procedimiento que suba el precio a todos los productos 
el tanto por ciento pasado como parámetro
*/

DELIMITER $$
DROP PROCEDURE IF EXISTS subir_precio$$
CREATE PROCEDURE subir_precio(IN porcentaje INT)
BEGIN
  UPDATE productos
  SET precio = precio + precio * porcentaje / 100;
END
$$

/* usa el procedimiento subir_precio */
DELIMITER ??
CALL subir_precio(10);
SELECT * FROM productos;
??









##########################################################
/*
Procedimiento que suba el precio a todos los productos de la familia
con el codigo que se le pasa como parámetro, el tanto por ciento pasado como parámetro
*/








##########################################################
/*
Procedimiento que suba o baje el precio a todos los productos de la familia
con el codigo que se le pasa como parámetro, el tanto por ciento pasado como parámetro. 
Se subirá el precio si recibe un 1 en el parámetro modificar y 
se bajará el precio si recibe un 0 en el prámetro modificar.
*/







##########################################################
/*
Procedimiento que itere un bucle el número de veces que se le pasa como parámetro
En cada iteración irá acumulando el valor del contador en una variable llamada suma
En el punto de llamada se mostrará el valor final de la variable suma
*/





###################################################
/* Crear una función que devuelva el área de un triángulo,
se le pasará como parámetro la base y la altura
*/








