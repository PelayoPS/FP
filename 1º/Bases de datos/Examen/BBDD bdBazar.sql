Drop database if exists bdBazar;
Create database bdBazar;
Use bdBazar;

drop table if exists familia;
Create table FAMILIA(
codFamilia int auto_increment primary key,
nombre varchar(50)
);

drop table if exists producto;
Create table PRODUCTO(
codProducto int auto_increment primary key,
nombre varchar(50),
codFamilia int not null,
foreign key (codFamilia) references FAMILIA(codFamilia)
);

Insert into FAMILIA(nombre) values
('papeleria'),
('ropa'),
('informatica'),
('hogar'),
('deportes'),
('electrónica'),
('juguetes');

Insert into PRODUCTO(nombre, codFamilia) values
('libreta muelles din a4',1),
('boli bic azul',1),
('block dibujo',1),
('goma milán',1),
('pantalón corto niño',2),
('chandal caballero', 2),
('ratón inalámbrico',3),
('ratón con cable usb',3),
('teclado inalámbrico',3),
('auriculares con cable',3),
('auriculares bluetooth',3),
('altavoces 10W',3),
('sartén 22cm',4),
('molde silicona',4);