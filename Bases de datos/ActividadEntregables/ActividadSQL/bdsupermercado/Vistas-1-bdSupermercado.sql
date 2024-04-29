use bdsupermercado;

/*Crear una vista que muestre todos los productos, sólo debe visualizar el
código del producto, el nombre y el código de familia al que pertenece, pero
no debe mostrar su precio
*/

DROP VIEW IF EXISTS productosSinPrecio;
CREATE VIEW productosSinPrecio AS
SELECT codProducto, nombre, codFamilia
FROM productos;


SELECT * FROM productosSinPrecio;




/*
 mostrar toda la información de la vista productosPorFamilia
*/

DROP VIEW IF EXISTS productosPorFamilia;
CREATE VIEW productosPorFamilia AS
SELECT codProducto, nombre, precio, codFamilia
FROM productos;

SELECT * FROM productosPorFamilia;




/*
mostrar el nombre del producto y el código de familia a la que pertenece
*/

DROP VIEW IF EXISTS productosNombreYCodFamilia;
CREATE VIEW productosNombreYCodFamilia AS
SELECT nombre, codFamilia
FROM productos;

SELECT * FROM productosNombreYCodFamilia;




/*Crear una vista que muestre todos los productos, sólo debe visualizar el
el nombre del producto, el código de familia al que pertenece y debe 
mostrar también el nombre de la familia pero no debe mostrar el precio del producto
*/

DROP VIEW IF EXISTS productosNombreYFamiliaSinPrecio;
CREATE VIEW productosNombreYFamiliaSinPrecio AS
SELECT p.nombre AS nombreProducto, f.nombre AS nombreFamilia
FROM productos p
INNER JOIN familias f ON p.codFamilia = f.codFamilia;

SELECT * FROM productosNombreYFamiliaSinPrecio;



/*
crear una vista que muestre la cantidad de productos que hay en cada familia,
debeberá mostrar también el código de la familia
*/

DROP VIEW IF EXISTS cantidadProductosPorFamilia;

CREATE VIEW cantidadProductosPorFamilia AS
SELECT codFamilia, COUNT(codProducto) AS cantidadProductos
FROM productos
GROUP BY codFamilia;

SELECT * FROM cantidadProductosPorFamilia;



/*
crear una vista que muestre el precio medio de los productos,
redondear a dos decimales
*/

DROP VIEW IF EXISTS precioMedio;
CREATE VIEW precioMedio AS
 SELECT ROUND(AVG(precio), 2)
  AS "precio medio" FROM productos;

SELECT * FROM precioMedio;



/*
modificar la vista precioMedio para redondear la media a dos decimales y 
darle el alias  as 'precio medio'
*/

DROP VIEW IF EXISTS precioMedio;
CREATE VIEW precioMedio AS
 SELECT ROUND(AVG(precio), 2)
  AS "precio medio" FROM productos;

SELECT * FROM precioMedio;



/* 
mostrar el código que se utilizó para crear la vista precioMedio
*/

SHOW CREATE VIEW precioMedio;



/*
eliminar la vista precioMedio
*/

DROP VIEW precioMedio;


/*
crear una vista que muestre el nombre y el precio de todos los productos
*/

DROP VIEW IF EXISTS productosNombreYPrecio;
CREATE VIEW productosNombreYPrecio AS
SELECT nombre, precio
FROM productos;

SELECT * FROM productosNombreYPrecio;



/*
mostrar la versión del servidor y la fecha actual
*/

SELECT VERSION() AS 'Server Version', NOW() AS 'Current Date';
