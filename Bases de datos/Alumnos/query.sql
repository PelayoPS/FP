-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bdMatriculaciones
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdMatriculaciones
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdMatriculaciones` ;
USE `bdMatriculaciones` ;

-- -----------------------------------------------------
-- Table `bdMatriculaciones`.`ALUMNOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdMatriculaciones`.`ALUMNOS` ;

CREATE TABLE IF NOT EXISTS `bdMatriculaciones`.`ALUMNOS` (
  `idAlumno` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `apellido1` VARCHAR(45) NULL,
  `poblacion` VARCHAR(45) NULL,
  `nif` VARCHAR(9) NULL,
  `fechaNacimiento` DATE NULL,
  `estaBecado` TINYINT NULL,
  PRIMARY KEY (`idAlumno`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdMatriculaciones`.`MODULOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdMatriculaciones`.`MODULOS` ;

CREATE TABLE IF NOT EXISTS `bdMatriculaciones`.`MODULOS` (
  `idMODULOS` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `nombreCompleto` VARCHAR(45) NULL,
  `duracion` INT NULL,
  PRIMARY KEY (`idMODULOS`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdMatriculaciones`.`MATRICULACIONES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdMatriculaciones`.`MATRICULACIONES` ;

CREATE TABLE IF NOT EXISTS `bdMatriculaciones`.`MATRICULACIONES` (
  `idAlumno` INT NOT NULL,
  `idModulo` INT NOT NULL,
  PRIMARY KEY (`idAlumno`, `idModulo`),
  INDEX `idModulo_idx` (`idModulo` ASC) VISIBLE,
  CONSTRAINT `idAlumno`
    FOREIGN KEY (`idAlumno`)
    REFERENCES `bdMatriculaciones`.`ALUMNOS` (`idAlumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idModulo`
    FOREIGN KEY (`idModulo`)
    REFERENCES `bdMatriculaciones`.`MODULOS` (`idMODULOS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



insert into alumnos (idAlumno,nombre, apellido1, poblacion, nif, fechaNacimiento, estaBecado) values
(0,'Martín', 'García', 'Oviedo','12345678A', '2003-01-12', 1),
(1,'Alicia', 'López', 'Oviedo','12345678B', '2001-02-15', 0),
(2,'Carmen', 'Fernández','Gijón','12345678C', '2002-06-20', 1),
(3,'Hugo', 'García', 'Oviedo','12345678D', '2000-10-22', 1),
(4,'Gabriel', 'Pérez', 'Avilés','12345678E', '2000-01-12', 1),
(5,'Laura', 'López', 'Gijón','12345678F', '2003-11-25', 0);

insert into modulos (idModulos,nombre, nombreCompleto, duracion) values
(1,'BBDD', 'Bases de Datos',192),
(2,'SSII', 'Sistemas Informáticos',180),
(3,'LLMM', 'Lenguaje de Marcas y Sistemas de Información',134),
(4,'FOL', 'Formación y Orientación Laboral',82);

insert into matriculaciones (idAlumno, idModulo) values
(1,1),
(1,2),
(1,3),
(2,1),
(2,2),
(3,1),
(4,3), 
(4,2);



-- Crear un procedimiento que reciba como argumento el identificador de un alumno y visualice la información del alumno que tiene ese código. 
-- Si el alumno no existe, se visualizará un mensaje de error.
DROP PROCEDURE IF EXISTS visualizarAlumno;
DELIMITER //
CREATE PROCEDURE visualizarAlumno(IN idAlumno INT)
BEGIN
    DECLARE nombreAlumno VARCHAR(45);
    DECLARE apellidoAlumno VARCHAR(45);
    DECLARE poblacionAlumno VARCHAR(45);
    DECLARE nifAlumno VARCHAR(9);
    DECLARE fechaNacimientoAlumno DATE;
    DECLARE estaBecadoAlumno TINYINT;
    
    SELECT nombre, apellido1, poblacion, nif, fechaNacimiento, estaBecado INTO nombreAlumno, apellidoAlumno, poblacionAlumno, nifAlumno, fechaNacimientoAlumno, estaBecadoAlumno
    FROM alumnos
    WHERE idAlumno = idAlumno;
    
    IF nombreAlumno IS NULL THEN
        SELECT 'El alumno no existe';
    ELSE
        SELECT nombreAlumno, apellidoAlumno, poblacionAlumno, nifAlumno, fechaNacimientoAlumno, estaBecadoAlumno;
    END IF;
END //
DELIMITER ;

-- Crear un procedimiento que reciba como argumento dos números y visualice su suma.
DROP PROCEDURE IF EXISTS suma;
DELIMITER //
CREATE PROCEDURE suma(IN num1 INT, IN num2 INT)
BEGIN
    SELECT num1 + num2;
END //
DELIMITER ;
-- Realizar el ejercicio anterior, pero con una función en vez de con un procedimiento.
-- Recordad ejecutar la siguiente instrucción: set global log_bin_trust_function_creators = 1;
DROP FUNCTION IF EXISTS suma;
set global log_bin_trust_function_creators = 1;
DELIMITER //
CREATE FUNCTION suma(num1 INT, num2 INT) RETURNS INT
BEGIN
    RETURN num1 + num2;
END //
DELIMITER ;


-- Crear un procedimiento que reciba el nombre de una tabla y visualice todos los registros que contiene. Después hacer una llamada al procedimiento por cada una de las tablas de la base de datos.
DROP PROCEDURE IF EXISTS visualizarTabla;
DELIMITER //
CREATE PROCEDURE visualizarTabla(IN nombreTabla VARCHAR(45))
BEGIN
    SET @sql = CONCAT('SELECT * FROM ', nombreTabla);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END //
DELIMITER ;