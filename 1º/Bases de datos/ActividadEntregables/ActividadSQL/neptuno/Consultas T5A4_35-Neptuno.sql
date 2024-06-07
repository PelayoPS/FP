USE neptuno;
/*
EJEMPLO 35
 Mostrar a partir de la tabla de PEDIDOS:el nombre de la compañía de envío
 (utilizar el campo FormaEnvio en un CASE) y la suma de los cargos de los pedidos
 enviados con cada compañía,así como los cargos mínimo, medio y máximo
 */
SELECT
   CASE FormaEnvio
      WHEN 1 THEN 'Envío normal'
      WHEN 2 THEN 'Envío urgente'
      WHEN 3 THEN 'Envío rápido'
      ELSE 'Otro'
   END AS 'Empresa Transporte',
   SUM(Cargo) AS 'Suma Pedido',
   MAX(Cargo) AS 'Pedido Máximo',
   AVG(Cargo) AS 'Pedido Medio',
   MIN(Cargo) AS 'Pedido Mínimo'
FROM PEDIDOS
GROUP BY FormaEnvio;
        
        
/* EJEMPLO 36
 Mostrar a partir de la tabla de clientes: los clientes agrupados por país y el nombre del país,
 de todos los países que empiecen por A
  */   
SELECT
   Pais,
   COUNT(*) AS 'Número de clientes'
FROM CLIENTES
WHERE Pais LIKE 'A%'
GROUP BY Pais;

 
 /*
 Ejemplo 37 (ampliación al ejemplo 35: tabla Pdidos)
 Mejorar el ejemplo 35 para que además de mostrar los datos de los cargos medios, mínimos,
 máximos y suma de cargos,agrupados por formaenvío,también agrupe los pedidos por idempleado.
 Debe mostrar los datos como la tabla que aparece abajo.
 La consulta afectará solamente a los PEDIDOS de los años 1996 y 1997 (fechapedido)
 FORMATO TABLA:
 Empresa Transporte	Empleado	fechapedido		Suma Pedido  Pedido Máximo	Pedido Medio  Pedido Mínimo 
 */

SELECT
   CASE FormaEnvio
      WHEN 1 THEN 'Envío normal'
      WHEN 2 THEN 'Envío urgente'
      WHEN 3 THEN 'Envío rápido'
      ELSE 'Otro'
   END AS 'Empresa Transporte',
   IdEmpleado,
   YEAR(FechaPedido) AS 'Año Pedido',
   SUM(Cargo) AS 'Suma Pedido',
   MAX(Cargo) AS 'Pedido Máximo',
   AVG(Cargo) AS 'Pedido Medio',
   MIN(Cargo) AS 'Pedido Mínimo'
FROM PEDIDOS
WHERE YEAR(FechaPedido) IN (1996, 1997)
GROUP BY FormaEnvio, IdEmpleado, YEAR(FechaPedido);
 
/*
 EJEMPLO 38
 Mostrar el año y el número de PEDIDOS realizados cada año. Recordad que existe una función 
 llamada YEAR(fecha) que devuelve el añode una fecha. Agrupar utilizando esta función
*/
SELECT
   YEAR(FechaPedido) AS 'Año Pedido',
   COUNT(*) AS 'Número de pedidos'
FROM PEDIDOS
GROUP BY YEAR(FechaPedido);

    
   /* 
    Ejemplo 39
 Mostrar cuánto hemos vendido (Cargo de PEDIDOS) agrupando los resultados por
 año y por PaisDestinatario de los pedidos, mostra también el país destinarario 
 y el año del pedido*/
SELECT
   YEAR(FechaPedido) AS 'Año Pedido',
   PaisDestinatario,
   SUM(Cargo) AS 'Suma Pedido'
FROM PEDIDOS
GROUP BY YEAR(FechaPedido), PaisDestinatario;

