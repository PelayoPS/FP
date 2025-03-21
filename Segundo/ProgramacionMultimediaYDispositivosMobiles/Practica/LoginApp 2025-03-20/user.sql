-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS bcmeoubxg58ehm98q6rj;

-- Usar la base de datos
USE bcmeoubxg58ehm98q6rj;

-- Crear la tabla Login
CREATE TABLE IF NOT EXISTS Login (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Identificador único para cada registro
    user VARCHAR(50) NOT NULL,         -- Nombre de usuario
    password VARCHAR(255) NOT NULL,    -- Contraseña (puedes usar hashing para mayor seguridad)
    public_id VARCHAR(100) NOT NULL    -- Identificador público único
);

-- Insertar datos de ejemplo
INSERT INTO Login (user, password, public_id) VALUES
('usuario1', 'contraseña1', 'public_id_1');