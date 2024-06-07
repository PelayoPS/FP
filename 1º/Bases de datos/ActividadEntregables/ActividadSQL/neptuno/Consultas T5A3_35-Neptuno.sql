USE neptuno;
/*
Ejemplo 21
¿Cuántos clientes tenemos?
cuenta todos los registros de la tabla aunque los campos no tengan valor (estén a null)
*/
SELECT COUNT(*) FROM clientes;

/* EJEMPLO 22
Mostrar el número de países diferentes que hay en la tabla de clientes
*/
SELECT COUNT(DISTINCT pais) FROM clientes;



 
/* EJEMPLO 23
Mostrar el cargo medio de los PEDIDOS realizados en el año 1996
*/
SELECT AVG(cargo) FROM pedidos WHERE YEAR(fechaPedido)=1996;


# la sentencia antrior pero pero mostrando sólo dos decimales
SELECT ROUND(AVG(cargo),2) FROM pedidos WHERE YEAR(fechaPedido)=1996;


/*EJEMPLO 24  
Calcular los precioUnidad: mínimo, máximo y medio de todos los productos
*/
SELECT MIN(precioUnidad),MAX(precioUnidad),AVG(precioUnidad) FROM productos;





/* 
EJEMPLO 25 
Calculas la suma de los cargos de todos los pedidos realizados durante el año 1996
daremos a la suma el alias 'Ventas 1996'
*/
SELECT SUM(cargo) AS 'Ventas 1996' FROM pedidos WHERE YEAR(fechaPedido)=1996;




/*
EJEMPLO 26
Queremos hacer un descuento del 25% en todos nuestros productos.
Mostrar el nombre del producto, el precioUnidad sin y con descuento.
*/
SELECT nombreProducto,precioUnidad,precioUnidad*0.75 AS 'Precio con descuento' FROM productos;





/*
EJEMPLO 27
Queremos ver en una consulta sobre la tabla de PEDIDOS: el idPedido, idCliente,
idEmpleado, fechaPedido, fechaEntrega, número de días transcurridos desde el pedido hasta la entrega del mismo
al cliente, cargo del pedido, comision del vendedor (15%) y beneficio neto (cargo - comision: 85).
Solamente se contemplarán los pedidos con FechaPedido a partir del 1 de enero de 1997 y
que se haya tardado menos de 20 días en entregar
*/
SELECT idPedido,idCliente,idEmpleado,fechaPedido,fechaEntrega,DATEDIFF(fechaEntrega,fechaPedido)
AS 'Días transcurridos',cargo,cargo*0.15 AS 'Comisión',cargo*0.85 AS 'Beneficio neto'
FROM pedidos WHERE fechaPedido>='1997-01-01' AND DATEDIFF(fechaEntrega,fechaPedido)<20;





/*
EJEMPLO 28
 Se desea ver el nombreProducto, precioUnidad y unidadesEnExistencia de todos
 los PRODUCTOS ordenados descendentemente por el precioUnidad.
*/
SELECT nombreProducto,precioUnidad,unidadesEnExistencia FROM productos ORDER BY precioUnidad DESC;




/*
EJEMPLO 29
 Se desea ver el nombreCompañia, Ciudad y Pais de todos los CLIENTES
 ordenados ascendentemente primero por el Pais, después por la Ciudad y por
 último por el nombreCompañia.
*/
SELECT nombreCompañia,ciudad,pais FROM clientes ORDER BY pais,ciudad,nombreCompañia;




/*
EJEMPLO 30
Se desea ver el nombreProducto, precioUnidad y unidadesEnExistencia de los 8 productos más caros
*/
SELECT nombreProducto,precioUnidad,unidadesEnExistencia 
FROM productos ORDER BY precioUnidad DESC LIMIT 8;


 
/*
EJEMPLO 31
Se desea ver el nombreProducto, precioUnidad y unidadesEnExistencia de los 6
 productos siguientes al 4º producto más caro
 Nota: los registros empiezan a numerarse en 0, así que el cuarto está en la posición 3
*/
SELECT nombreProducto,precioUnidad,unidadesEnExistencia
FROM productos ORDER BY precioUnidad DESC LIMIT 3,6;





/*
 EJEMPLO 32
 Se desea calcular el descuento aplicado a los PEDIDOS sabiendo que si el Cargo
 del pedido es mayor de 200 se le aplica un 25% y en caso contrario le
 aplicaremos un 10%. Mostrar el idPedido, fechaPedido, Destinatario y Cargo de
 los pedidos, así como dicho descuento
*/
SELECT idPedido,fechaPedido,Destinatario,cargo,
CASE WHEN cargo>200 THEN cargo*0.25 ELSE cargo*0.10 END AS 'Descuento'
FROM pedidos;





/*
EJEMPLO 33
 Se quiere hacer una consulta sobre la tabla de PEDIDOS que muestre: 
 idPedido, Destinatario, fechaPedido, Cargo y la compañía de envíos que se ha utilizado
 para enviarlo. No debe aparecer el código de la compañía (campo
 FormaEnvio),sino su nombre.Hacer la consulta anterior para los pedidos del mes
 de septiembre de 1996
*/
SELECT idPedido,Destinatario,fechaPedido,cargo,formaEnvio 
FROM pedidos WHERE MONTH(fechaPedido)=9 AND YEAR(fechaPedido)=1996;





/*
EJEMPLO 34
Mostrar la suma total de todos los cargos agrupados por la ciudad del destinatario
*/
SELECT CiudadDestinatario,SUM(cargo) FROM pedidos GROUP BY CiudadDestinatario;




/*
EJEMPLO 34 
Calcular el número de CLIENTES que tenemos en cada país
*/
SELECT pais,COUNT(*) FROM clientes GROUP BY pais;

 