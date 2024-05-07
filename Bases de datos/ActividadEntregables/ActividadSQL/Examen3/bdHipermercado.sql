DROP DATABASE IF EXISTS bdHipermercado;
CREATE DATABASE bdHipermercado;
USE bdHipermercado;

CREATE TABLE `familias` (
  `codFamilia` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codFamilia`)
);

CREATE TABLE `productos` (
  `codProducto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `codFamilia` int NOT NULL,
   PRIMARY KEY (`codProducto`),
   FOREIGN KEY (`codFamilia`) REFERENCES `familias` (`codFamilia`)
);

Insert into FAMILIAS(nombre, descripcion) values
('papeleria', 'artículos de papeleria'),
('ropa', 'ropa hombre,mujer, niño'),
('informatica', 'artículos de informática y tecnología'),
('hogar', null),
('deportes', null),
('electrónica', null),
('juguetes',null);

Insert into PRODUCTOS(nombre, precio, marca,codFamilia) values
('libreta muelles din a4',5,'Oxford',1),
('boli bic azul',1,'Bic', 1),
('block dibujo',4,'Oxford',1),
('goma milán',0.50,null,1),
('pantalón corto niño',15,'Pequeñines',2),
('chandal caballero', 30,null,2),
('ratón inalámbrico',12,'Mars Gaming',3),
('ratón con cable usb',14,'Mars Gaming',3),
('teclado inalámbrico',25,null,3),
('auriculares con cable',15,'Sony',3),
('auriculares bluetooth',24,'Sony',3),
('altavoces 10W',12,null,3),
('sartén 22cm',12,null,4),
('molde silicona',8,null,4);