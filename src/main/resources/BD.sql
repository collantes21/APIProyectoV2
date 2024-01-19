DROP DATABASE IF EXISTS api_hoteles;
CREATE DATABASE api_hoteles;
USE api_hoteles;

CREATE TABLE Hotel (
                         idHotel INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         descripcion VARCHAR (100),
                         categoria VARCHAR(1),
                         tiene_piscina BOOLEAN,
                         localidad VARCHAR(50) NOT NULL
);

INSERT INTO Hotel VALUES (1,'Olid','Centro','4',true,'Valladolid'),
                           (2,'Paris', 'Centro','3',false,'Valladolid'),
                           (3,'Lasa Sport', 'Deportivo','4',true,'Valladolid'),
                           (4,'Felipe IV', 'Centro','2',false,'Valladolid');

CREATE TABLE Habitacion (
                              idHabitacion INT PRIMARY KEY,
                              idHotel INT,
                              capacidad int,
                              precioNoche DECIMAL(10, 2),
                              incluyeDesayuno BOOLEAN,
                              ocupada BOOLEAN,
                              FOREIGN KEY (idHotel) REFERENCES Hotel(idHotel)
);

INSERT INTO Habitacion (idHabitacion, idHotel, capacidad, precioNoche, incluyeDesayuno, ocupada)
VALUES
    (101, 1, 2, 150.00, true, false),
    (102, 1, 1, 100.00, false, false),
    (201, 2, 2, 300.00, true, true),
    (202, 2, 2, 280.00, false, false),
    (301, 3, 1, 80.00, true, false),
    (302, 4, 2, 120.00, false, true);