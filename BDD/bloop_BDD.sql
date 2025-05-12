CREATE DATABASE IF NOT EXISTS bloop;

use bloop;

CREATE TABLE Usuario (
Id_usuario INT PRIMARY KEY AUTO_INCREMENT,
Nombre VARCHAR (50) NOT NULL,
Contrasena VARCHAR(255) NOT NULL,
nivel_acceso Boolean NOT NULL
); -- EN JAVA TENDREMOS QUE HACER EN HASH

CREATE TABLE Contacto_Usuario (
Id_usuario INT PRIMARY KEY,
Telefono INT,
Email varchar(50) NOT NULL,
FOREIGN KEY (Id_usuario) REFERENCES Usuario(Id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Categoria_evento (
Id_Categoria_Evento INT PRIMARY KEY auto_increment,
Categoria VARCHAR (50) NOT NULL
);

CREATE TABLE Evento (
Id_evento INT PRIMARY KEY AUTO_INCREMENT,
Id_Categoria_Evento INT,
Titulo VARCHAR(100) NOT NULL,
Fecha DATE NOT NULL,
Ubicacion VARCHAR(100) NOT NULL,
Plazas_totales INT NOT NULL,
Plazas_disponibles INT NOT NULL,
FOREIGN KEY (Id_Categoria_Evento) REFERENCES Categoria_evento(Id_Categoria_Evento) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Tipo_reserva (
Id_Tipo_reserva INT PRIMARY KEY auto_increment,
Tipo VARCHAR(50) NOT NULL
);

CREATE TABLE Reservas(
Id_reserva INT PRIMARY KEY AUTO_INCREMENT,
Id_usuario INT,
Id_evento INT,
Id_Tipo_Reserva INT,
Fecha_Reserva date NOT NULL,
num_entradas INT NOT NULL,
FOREIGN KEY (Id_usuario) REFERENCES Usuario(Id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (Id_evento) REFERENCES Evento(Id_evento) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (Id_Tipo_Reserva) REFERENCES Tipo_reserva(Id_Tipo_reserva) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Reserva_Grupal(
Id_reserva INT PRIMARY KEY,
Lista_Acompaniantes TEXT,
FOREIGN KEY (Id_reserva) REFERENCES Reservas (Id_reserva) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Usuarios
INSERT INTO Usuario (Nombre, Contrasena, nivel_acceso) VALUES
('admin1', '1234', 1), -- Administrador
('usuario1', '1234', 0); -- Usuario General

-- Contacto del usuario
INSERT INTO Contacto_usuario (Id_Usuario, Telefono, Email) VALUES
(1, 601238327, "admin1@gmail.com"), -- Administrador
(2, 646312835, "usuario1@gmail.com"); -- Usuario General

-- Tipos de Reserva
INSERT INTO tipo_reserva (Tipo) VALUES
('Normal'),
('VIP');

-- Categorías de Evento
INSERT INTO categoria_evento (Categoria) VALUES
('Tardeo'),
('Concierto'),
('Charla'),
('Taller');

-- Eventos
INSERT INTO evento (Id_Categoria_Evento, Titulo, Fecha, Ubicacion, Plazas_totales, Plazas_disponibles) VALUES
(1, 'Tardeo Primavera', '2025-06-01', 'Terraza Centro', 80, 80),
(2, 'Concierto Pop', '2025-06-10', 'Auditorio Norte', 150, 150),
(3, 'Charla Motivacional', '2025-06-15', 'Sala Roja', 60, 60),
(4, 'Taller de Fotografía', '2025-06-20', 'Aula Creativa', 25, 25);

-- Reservas
INSERT INTO reservas (Id_usuario, Id_evento, Id_Tipo_Reserva, Fecha_Reserva, num_entradas) VALUES
(2, 1, 1, '2025-05-12', 1),  -- Normal individual
(2, 2, 2, '2025-05-12', 4);  -- VIP grupal

-- Reserva Grupal (solo para la segunda reserva, ID = 2)
INSERT INTO reserva_grupal (Id_reserva, Lista_Acompaniantes) VALUES
(2, 'Lucía Pérez, Andrés Gil, Sonia Ramos');


/*SELECT * FROM categoria_evento;
SELECT * FROM contacto_usuario;
SELECT * FROM evento;
SELECT * FROM reserva_grupal;
SELECT * FROM reservas;
SELECT * FROM tipo_reserva;
SELECT * FROM usuario;*/ 





