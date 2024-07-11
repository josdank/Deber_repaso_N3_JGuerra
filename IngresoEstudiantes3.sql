CREATE DATABASE IF NOT EXISTS base;
USE base;

CREATE TABLE IF NOT EXISTS base_estudiante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(20) NOT NULL,
    cedula VARCHAR(10) NOT NULL,
    b1 decimal(4,2) NOT NULL,
    b2 decimal(4,2) NOT NULL
);

SELECT * FROM base_estudiante;