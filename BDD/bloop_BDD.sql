CREATE DATABASE IF NOT EXISTS bloop;

use bloop;

CREATE TABLE Usuario (
Id_usuario INT PRIMARY KEY AUTO_INCREMENT,
Nombre VARCHAR (50) NOT NULL,
Contrasena VARCHAR(255) NOT NULL
); -- EN JAVA TENDREMOS QUE HACER EN HASH

CREATE TABLE Contacto_Usuario (
Id_usuario INT PRIMARY KEY,
Telefono INT,
Email varchar(50) NOT NULL,
FOREIGN KEY (Id_usuario) REFERENCES Usuario(Id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Usuario_Administrador (
Id_usuario INT PRIMARY KEY,
nivel_acceso boolean NOT NULL DEFAULT 1,
FOREIGN KEY (Id_usuario) REFERENCES Usuario(Id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Usuario_general (
Id_usuario INT PRIMARY KEY,
nivel_acceso boolean NOT NULL DEFAULT 0,
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
DROP DATABASE bloop;


