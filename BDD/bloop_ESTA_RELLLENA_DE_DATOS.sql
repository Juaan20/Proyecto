-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-05-2025 a las 19:02:23
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bloop`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_evento`
--

CREATE TABLE `categoria_evento` (
  `Id_Categoria_Evento` int(11) NOT NULL,
  `Categoria` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria_evento`
--

INSERT INTO `categoria_evento` (`Id_Categoria_Evento`, `Categoria`) VALUES
(1, 'Tardeo'),
(2, 'Nocturno'),
(3, 'After');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contacto_usuario`
--

CREATE TABLE `contacto_usuario` (
  `Id_usuario` int(11) NOT NULL,
  `Telefono` int(11) DEFAULT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `contacto_usuario`
--

INSERT INTO `contacto_usuario` (`Id_usuario`, `Telefono`, `Email`) VALUES
(31, 222111333, 'manuel.diaz@example.com'),
(32, 333222444, 'isabela.romero@example.com'),
(33, 444333555, 'tomas.aguilar@example.com'),
(34, 555444666, 'renata.silva@example.com'),
(35, 666555777, 'sebastian.paredes@example.com'),
(36, 777666888, 'daniela.fuentes@example.com'),
(37, 888777999, 'alejandro.vega@example.com'),
(38, 999888000, 'julieta.castro@example.com'),
(39, 111222000, 'matias.herrera@example.com'),
(40, 222333111, 'victoria.nunez@example.com'),
(41, 123123123, 'user@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento`
--

CREATE TABLE `evento` (
  `Id_evento` int(11) NOT NULL,
  `Id_Categoria_Evento` int(11) DEFAULT NULL,
  `Titulo` varchar(100) NOT NULL,
  `Fecha` date NOT NULL,
  `Ubicacion` varchar(100) NOT NULL,
  `Plazas_totales` int(11) NOT NULL,
  `Plazas_disponibles` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `evento`
--

INSERT INTO `evento` (`Id_evento`, `Id_Categoria_Evento`, `Titulo`, `Fecha`, `Ubicacion`, `Plazas_totales`, `Plazas_disponibles`) VALUES
(2, 1, 'Tardeo de Kembo', '2025-07-09', 'Zaragoza', 100, 50),
(3, 2, 'Tardeo de Kembo', '2025-07-09', 'Zaragoza', 200, 150),
(4, 3, 'Tardeo de Kembo', '2025-07-09', 'Zaragoza', 100, 10),
(5, 3, 'Tardeo de Kembo', '2025-07-09', 'Zaragoza', 100, 10),
(6, 3, 'Tardeo de Kembo', '2025-07-09', 'Zaragoza', 100, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `Id_reserva` int(11) NOT NULL,
  `Id_usuario` int(11) DEFAULT NULL,
  `Id_evento` int(11) DEFAULT NULL,
  `Id_Tipo_Reserva` int(11) DEFAULT NULL,
  `Fecha_Reserva` date NOT NULL,
  `num_entradas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_reserva`
--

CREATE TABLE `tipo_reserva` (
  `Id_Tipo_reserva` int(11) NOT NULL,
  `Tipo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `Id_usuario` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Contrasena` varchar(255) NOT NULL,
  `nivel_acceso` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`Id_usuario`, `Nombre`, `Contrasena`, `nivel_acceso`) VALUES
(31, 'Manuel Díaz', 'manuel123', 0),
(32, 'Isabela Romero', 'isabela456', 0),
(33, 'Tomás Aguilar', 'tomas789', 0),
(34, 'Renata Silva', 'renata321', 1),
(35, 'Sebastián Paredes', 'sebas654', 0),
(36, 'Daniela Fuentes', 'daniela987', 0),
(37, 'Alejandro Vega', 'ale123', 0),
(38, 'Julieta Castro', 'julieta456', 1),
(39, 'Matías Herrera', 'matias789', 0),
(40, 'Victoria Núñez', 'victoria321', 0),
(41, 'user', 'user', 0),
(42, 'admin', 'admin', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria_evento`
--
ALTER TABLE `categoria_evento`
  ADD PRIMARY KEY (`Id_Categoria_Evento`);

--
-- Indices de la tabla `contacto_usuario`
--
ALTER TABLE `contacto_usuario`
  ADD PRIMARY KEY (`Id_usuario`);

--
-- Indices de la tabla `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`Id_evento`),
  ADD KEY `Id_Categoria_Evento` (`Id_Categoria_Evento`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`Id_reserva`),
  ADD KEY `Id_usuario` (`Id_usuario`),
  ADD KEY `Id_evento` (`Id_evento`),
  ADD KEY `Id_Tipo_Reserva` (`Id_Tipo_Reserva`);

--
-- Indices de la tabla `tipo_reserva`
--
ALTER TABLE `tipo_reserva`
  ADD PRIMARY KEY (`Id_Tipo_reserva`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`Id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria_evento`
--
ALTER TABLE `categoria_evento`
  MODIFY `Id_Categoria_Evento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `evento`
--
ALTER TABLE `evento`
  MODIFY `Id_evento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `Id_reserva` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_reserva`
--
ALTER TABLE `tipo_reserva`
  MODIFY `Id_Tipo_reserva` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `Id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `contacto_usuario`
--
ALTER TABLE `contacto_usuario`
  ADD CONSTRAINT `contacto_usuario_ibfk_1` FOREIGN KEY (`Id_usuario`) REFERENCES `usuario` (`Id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `evento`
--
ALTER TABLE `evento`
  ADD CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`Id_Categoria_Evento`) REFERENCES `categoria_evento` (`Id_Categoria_Evento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`Id_usuario`) REFERENCES `usuario` (`Id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`Id_evento`) REFERENCES `evento` (`Id_evento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservas_ibfk_3` FOREIGN KEY (`Id_Tipo_Reserva`) REFERENCES `tipo_reserva` (`Id_Tipo_reserva`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
