use bdhipermercado;

drop table if exists productosEliminados;

create table productosEliminados(
codEliminado int primary key,
nombreEliminado varchar(50),
precioEliminado int
);

-- Ejercicio 1

delimiter $$
drop trigger if exists familiaPorDefecto$$
create trigger familiaPorDefecto
before insert 
on productos
for each row
begin
    if new.marca is null then
        set new.marca = "Marquita";
    end if;
end$$
delimiter ;
insert into productos (nombre, precio, codFamilia) values ('Producto 1', 10, 1); 

select * from productos;

-- End ejercicio 1

-- Ejercicio 2


delimiter $$
drop trigger if exists guardarProductoEliminado$$
create trigger guardarProductoEliminado
after delete on productos
for each row
begin
    insert into productosEliminados (codEliminado, nombreEliminado, precioEliminado)
    values (old.codProducto, old.nombre, old.precio);
end$$
delimiter ;
insert into productos (nombre, precio, codFamilia) values ('Producto 1', 10, 1); 
delete from productos where codFamilia = 1;

select * from productosEliminados;

-- End ejercicio 2



