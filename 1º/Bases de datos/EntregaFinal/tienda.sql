-- Crear la tabla Libros

CREATE TABLE Libros (
  ID_Libro INT PRIMARY KEY,
  Titulo VARCHAR(255) NOT NULL,
  Autor VARCHAR(255) NOT NULL,
  ID_Comprador INT FOREIGN KEY REFERENCES Comprador(ID_Comprador)
);

-- Crear la tabla Categorías

CREATE TABLE Categorias (
  ID_Categoria INT PRIMARY KEY,
  Nombre VARCHAR(255) NOT NULL,
  Descripcion VARCHAR(255) NOT NULL
);

-- Crear la tabla Libros_Categorias

CREATE TABLE Libros_Categorias (
  ID_Libro INT FOREIGN KEY REFERENCES Libros(ID_Libro),
  ID_Categoria INT FOREIGN KEY REFERENCES Categorias(ID_Categoria)
);

-- Crear la tabla Comprador

CREATE TABLE Comprador (
  ID_Comprador INT PRIMARY KEY,
  Nombre VARCHAR(255) NOT NULL,
  Apellido VARCHAR(255) NOT NULL,
  Email VARCHAR(255) NOT NULL
);

-- Insertar datos de ejemplo

INSERT INTO Libros (ID_Libro, Titulo, Autor, ID_Comprador) VALUES
  (1, 'El Principito', 'Antoine de Saint-Exupéry', 1),
  (2, 'Cien años de soledad', 'Gabriel García Márquez', 2),
  (3, '1984', 'George Orwell', 1);

INSERT INTO Categorias (ID_Categoria, Nombre, Descripcion) VALUES
  (1, 'Novela', 'Ficción narrativa extensa'),
  (2, 'Ciencia ficción', 'Subgénero de la ficción especulativa'),
  (3, 'Literatura clásica', 'Obras literarias de reconocido valor cultural');

INSERT INTO Libros_Categorias (ID_Libro, ID_Categoria) VALUES
  (1, 1),
  (1, 3),
  (2, 1),
  (2, 3),
  (3, 2);

INSERT INTO Comprador (ID_Comprador, Nombre, Apellido, Email) VALUES
  (1, 'Juan', 'Pérez', 'juan.perez@email.com'),
  (2, 'María', 'Gómez', 'maria.gomez@email.com'),
  (3, 'Pedro', 'García', 'pedro.garcia@email.com');

-- Consultas

-- Sentencia DDL para modificar apellido a apellidos en la tabla Comprador

ALTER TABLE Comprador
  CHANGE Apellido Apellidos VARCHAR(255) NOT NULL;

-- Sentencia DML para eliminar datos de la base de datos
-- con una condición 

DELETE FROM Comprador
  WHERE ID_Comprador = 3;

-- Leer información de la base de datos
-- Consulta 1: consulta multitabla

SELECT * FROM Libros, Comprador
  WHERE Libros.ID_Comprador = Comprador.ID_Comprador;

-- Consulta 2: subconsulta

SELECT * FROM Libros
  WHERE ID_Libro IN 
  (SELECT ID_Libro 
  FROM Libros_Categorias 
  WHERE ID_Categoria = 1);

-- Consulta 3: consulta con JOIN

SELECT * FROM Libros
  JOIN Comprador ON Libros.ID_Comprador = Comprador.ID_Comprador;

-- Consulta 4: consulta con LIKE

SELECT * FROM Comprador
  WHERE Email LIKE '%@email.com';

-- Consulta 5: consulta con una función de agregación

SELECT COUNT(*) FROM Libros;

-- Consulta 6: consulta con GROUP BY... HAVING

SELECT ID_Libro, COUNT(*) AS Cantidad
  FROM Libros_Categorias
  GROUP BY ID_Libro
  HAVING COUNT(*) > 1;
