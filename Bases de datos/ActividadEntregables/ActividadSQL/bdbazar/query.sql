/*1.1.4 Escriba un procedimiento llamado listar_productos que reciba como entrada
 el codFamilia y muestre un listado de todos los productos que existen
  dentro de esa familia. Este procedimiento no devuelve ningún parámetro de salida,
   lo que hace es mostrar el listado de los productos.*/
DELIMITER $$
DROP PROCEDURE IF EXISTS listar_productos$$
CREATE PROCEDURE listar_productos(IN codFamilia VARCHAR(50))
BEGIN
  SELECT *
  FROM producto
  WHERE producto.codFamilia = codFamilia;
END
$$

/*1.1.5 Para hacer la llamada a un procedimiento almacenado se utiliza la palabra reservada CALL.*/
DELIMITER ??
CALL listar_productos(1);
SELECT * FROM producto;
??

/*1.1.6.1 Escriba un procedimiento llamado contar_productos que reciba como entrada el nombre del codFamilia
 y devuelva el número de productos que existen dentro de esa familia. Resuelva el ejercicio de dos
  formas distintas, utilizando SET y SELECT ... INTO.*/
-- Solución 1
DELIMITER %%
DROP PROCEDURE IF EXISTS contar_productos%%
CREATE PROCEDURE contar_productos(IN codFamilia VARCHAR(50), OUT numProductos INT)
BEGIN
  SET numProductos = (SELECT COUNT(*)
                      FROM producto
                      WHERE producto.codFamilia = codFamilia);
END
%%

DELIMITER ;
CALL contar_productos(1, @numProductos);
SELECT @numProductos;

-- Solución 2
DELIMITER ??
CALL contar_productos(1, @numProductos);
SELECT @numProductos;
??

DELIMITER ;
CALL contar_productos(1, @numProductos);
SELECT @numProductos;

/* 1.1.6.2 Escribe un procedimiento que se llame calcular_max_min_media, que reciba como
 parámetro de entrada el codFamilia de un producto y devuelva como salida
tres parámetros. El precio máximo, el precio mínimo y la media de los productos
que existen en esa familia. Resuelva el ejercicio de dos formas distintas, utilizando
SET y SELECT ... INTO.*/

-- Solución 1 Utilizando SET
DELIMITER %%
DROP PROCEDURE IF EXISTS calcular_max_min_media%%
CREATE PROCEDURE calcular_max_min_media(IN codFamilia VARCHAR(50),
 OUT maxPrecio DECIMAL(10, 2), OUT minPrecio DECIMAL(10, 2), OUT mediaPrecio DECIMAL(10, 2))
BEGIN
  SET maxPrecio = (SELECT MAX(precio)
                   FROM producto
                   WHERE producto.codFamilia = codFamilia);
  SET minPrecio = (SELECT MIN(precio)
                   FROM producto
                   WHERE producto.codFamilia = codFamilia);
  SET mediaPrecio = (SELECT AVG(precio)
                     FROM producto
                     WHERE producto.codFamilia = codFamilia);
END
%%
DELIMITER ;

CALL calcular_max_min_media(1, @maxPrecio, @minPrecio, @mediaPrecio);
SELECT @maxPrecio, @minPrecio, @mediaPrecio;

-- Solución 2 Utilizando SELECT ... INTO

DELIMITER &&
DROP PROCEDURE IF EXISTS calcular_max_min_media&&
CREATE PROCEDURE calcular_max_min_media(IN codFamilia VARCHAR(50),
 OUT maxPrecio DECIMAL(10, 2), OUT minPrecio DECIMAL(10, 2), OUT mediaPrecio DECIMAL(10, 2))
BEGIN
  SELECT MAX(precio), MIN(precio), AVG(precio)
  INTO maxPrecio, minPrecio, mediaPrecio
  FROM producto
  WHERE producto.codFamilia = codFamilia;
END
&&	


