DROP DATABASE IF EXISTS bdSupermercado;
CREATE DATABASE bdSupermercado;
USE bdSupermercado;

CREATE TABLE `familias` (
  `codFamilia` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codFamilia`)
);

CREATE TABLE `productos` (
  `codProducto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `precio` double DEFAULT '0',
  `codFamilia` int NOT NULL,
   PRIMARY KEY (`codProducto`),
   FOREIGN KEY (`codFamilia`) REFERENCES `familias` (`codFamilia`)
);

Insert into FAMILIAS(nombre) values
('papeleria'),
('ropa'),
('informatica'),
('hogar'),
('deportes'),
('electrónica'),
('juguetes');

Insert into PRODUCTOS(nombre, precio, codFamilia) values
('libreta muelles din a4',5,1),
('boli bic azul',1,1),
('block dibujo',4,1),
('goma milán',0.50,1),
('pantalón corto niño',15,2),
('chandal caballero', 30,2),
('ratón inalámbrico',12,3),
('ratón con cable usb',14,3),
('teclado inalámbrico',25,3),
('auriculares con cable',15,3),
('auriculares bluetooth',24,3),
('altavoces 10W',12,3),
('sartén 22cm',12,4),
('molde silicona',8,4);