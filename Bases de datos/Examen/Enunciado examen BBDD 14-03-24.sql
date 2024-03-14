/* CONSULTAS SOBRE BBDD bdBazar*/ 

/*PREGUNTA 1: 
mostrar todos los productos cuyo nombre acabe en la letra ‘o’*/
/*
Respuesta:
    select con filtro
*/
SELECT * FROM PRODUCTO WHERE NOMBRE LIKE '%o';




/*PREGUNTA 2: Mostrar todos los productos cuyo nombre acabe en la letra O y no comience por la palabra block.*/
/*
Respuesta:
    select con dos filtros
    de igual y distinto
*/
SELECT * FROM PRODUCTO WHERE NOMBRE LIKE '%O' AND NOMBRE NOT LIKE 'block%';





/*PREGUNTA 3
Mostrar todas las familias que tengan un codigo mayor que 2 y menor que 6. .*/
/*
Respuesta:
    select con dos filtros
    de mayor y menor
*/
SELECT * FROM FAMILIA WHERE codFamilia > 2 AND codFamilia < 6;






/*PREGUNTA 4
Realizar la consulta anterior de otra forma distinta.*/
/*
Respuesta:
    select con un filtro
    de entre
*/
SELECT * FROM FAMILIA WHERE codFamilia BETWEEN 3 AND 5;




/*PREGUNTA 5
Mostrar nombre de producto con el alias producto, el código de la familia a la que 
pertenecen con el alias 'código familia' y el nombre de la familia con el alias familia.*/
/*
Respuesta:
    select sobre dos tablas
    con alias y un filtro de igualdad
*/
SELECT p.nombre AS producto, p.codFamilia AS 'código familia', f.nombre AS familia
FROM PRODUCTO p, FAMILIA f
WHERE p.codFamilia = f.codFamilia;



/*PREGUNTA 6
Mostrar el nombre y la familia de los productos que pertenezcan a la familia de informatica. .*/
/*
Respuesta:
    select sobre dos tablas
    con alias de variables y dos filtro de igualdad
*/
SELECT p.nombre, f.nombre
FROM PRODUCTO p, FAMILIA f
WHERE p.codFamilia = f.codFamilia AND f.nombre = 'informatica';




/*PREGUNTA 7
Mostar el número de productos (con el alias 'total productos') que hay en la tabla PRODUCTO.*/
/*
Respuesta:
    select con un alias
    de conteo
*/
SELECT COUNT(*) AS 'total productos' FROM PRODUCTO;





/*PREGUNTA 8
Mostar todos los productos de las familias 1, 2, 3, ordenado de mayor a menor por el nombre del producto.*/
/*
Respuesta:
    select con un filtro de igualdad
    y ordenado de forma descendente
*/
SELECT * FROM PRODUCTO WHERE codFamilia IN (1, 2, 3) ORDER BY nombre DESC;



/*PREGUNTA 9
Mostrar todos los productos que no tengas asociada ninguna familia. */
/*
Respuesta:
    select con un filtro de igualdad
    y ordenado de forma descendente
*/
SELECT nombre AS 'producto sin familia' FROM PRODUCTO WHERE codFamilia IS NULL;



/*PREGUNTA 10
Mostrar todos los productos y la familia a la que pertenecen. */
/*
Respuesta:
    select sobre dos tablas
    con alias de variables y un join
*/
SELECT p.nombre, f.nombre
FROM PRODUCTO p LEFT JOIN FAMILIA f
ON p.codFamilia = f.codFamilia;




/*PREGUNTA 11
Mostrar la frase "Buenas tardes a todos" y después reemplazar tardes por noches y mostrarlo también en pantalla. Utilizad los alias 'original' y 'modificada'.*/
/*
Respuesta:
    select con un alias
    y una función de reemplazo
*/
SELECT 'Buenas tardes a todos' AS original, REPLACE('Buenas tardes a todos', 'tardes', 'noches') AS modificada;




/*PREGUNTA 12
Mostrar todas las familias de la tabla PRODUCTO eliminando las repetida.*/
/*
Respuesta:
    select con un filtro de desigualdad
    y ordenado de forma descendente
*/
SELECT DISTINCT codFamilia FROM PRODUCTO;




/*PREGUNTA 13
Mostar todas las familias que lleven una 'a' en la cuarta posición.*/
/*
Respuesta:
    select con un filtro de igualdad
    y ordenado de forma descendente
*/
SELECT * FROM FAMILIA WHERE nombre LIKE '___a%';




/*PREGUNTA 14
Mostrar en pantalla la frase “HOY ES LUNES” con el alias 'mayúsculas' y esa misma frase en minúsculas usando una función, darle el alias minúsculas.*/
/*
Respuesta:
    select con un alias
    y una función de cambio de mayúsculas a minúsculas
*/
SELECT 'HOY ES LUNES' AS mayúsculas, LOWER('HOY ES LUNES') AS minúsculas;



/*PREGUNTA 15
Mostrar el nombre y el código de los productos que terminen en 'o' y que no contengan la letra 't'.*/
/*
Respuesta:
    select con dos filtros
    de igualdad y desigualdad
*/
SELECT nombre, codProducto
FROM PRODUCTO
WHERE nombre LIKE '%o' AND nombre NOT LIKE '%t%';




/*PREGUNTA 16
Muestra por pantalla el nombre de todos lso prodctos precedidos de la palabra PRODUCTO y dos puntos, darle el alias de 'mis productos' y también la longitud en bytes del nombre del producto con el alias de tamaño.*/
/*
Respuesta:
    select con un alias
    y una función de longitud
*/
SELECT CONCAT('PRODUCTO: ', nombre) AS 'mis productos', LENGTH(nombre) AS tamaño
FROM PRODUCTO;




/*PREGUNTA 17
Mostrar el código de la familia y el nombre de todos los productos ordenados descendentemente por familia y después ordenados ascendentemente por código familia.*/
/*
Respuesta:
    select con dos ordenaciones
    de forma descendente y ascendente
*/
SELECT codFamilia, nombre
FROM PRODUCTO
ORDER BY codFamilia DESC, nombre ASC;

