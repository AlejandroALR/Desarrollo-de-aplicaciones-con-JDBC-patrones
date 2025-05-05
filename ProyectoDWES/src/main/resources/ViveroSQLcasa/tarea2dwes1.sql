-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-05-2025 a las 12:38:44
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
-- Base de datos: `tarea2dwes1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

CREATE TABLE `credenciales` (
  `id` int(11) NOT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `fk_idPersona` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `credenciales`
--

INSERT INTO `credenciales` (`id`, `usuario`, `password`, `fk_idPersona`) VALUES
(1, 'admin', 'admin', 0),
(2, 'alex', 'alex', 2),
(3, 'luis', 'luis', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplares`
--

CREATE TABLE `ejemplares` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `fk_planta` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `ejemplares`
--

INSERT INTO `ejemplares` (`id`, `nombre`, `fk_planta`) VALUES
(1, 'ROSA_1', 'ROSA'),
(2, 'ROSA_2', 'ROSA'),
(3, 'ROSA_3', 'ROSA'),
(4, 'MARGARITA_1', 'MARGARITA'),
(5, 'MARGARITA_2', 'MARGARITA'),
(6, 'AMAPOLA_1', 'AMAPOLA'),
(7, 'AMAPOLA_2', 'AMAPOLA'),
(8, 'AMAPOLA_3', 'AMAPOLA'),
(9, 'AMAPOLA_4', 'AMAPOLA'),
(11, 'PETUNIA_1', 'PETUNIA'),
(12, 'MANZANO_1', 'MANZANO'),
(13, 'PERAL_1', 'PERAL'),
(14, 'OLIVO_1', 'OLIVO'),
(15, 'OLIVO_2', 'OLIVO'),
(16, 'OLIVO_3', 'OLIVO'),
(17, 'OLIVO_4', 'OLIVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `id` int(11) NOT NULL,
  `fechaHora` datetime DEFAULT NULL,
  `mensaje` varchar(500) DEFAULT NULL,
  `fk_idPersona` int(11) DEFAULT NULL,
  `fk_idEjemplar` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`id`, `fechaHora`, `mensaje`, `fk_idPersona`, `fk_idEjemplar`) VALUES
(4, '2025-05-04 01:05:32', 'Rosita', 0, 3),
(5, '2025-05-04 01:12:33', 'mensaje ', 0, 7),
(6, '2025-05-04 01:26:51', 'La margarita crece correctamente, ha sido regada hoy a la mañana', 0, 5),
(7, '2025-05-04 01:32:32', 'mensaje ', 2, 8),
(8, '2025-05-04 01:51:00', 'mensaje ', 0, 9),
(9, '2025-05-04 01:51:58', 'mensaje ', 0, 10),
(10, '2025-05-04 01:52:54', 'Esta rosa esta mal por el nombre que se repite', 0, 1),
(11, '2025-05-05 10:30:43', 'mensaje ', 0, 11),
(12, '2025-05-05 10:31:57', 'petunia crece correctamente', 0, 11),
(13, '2025-05-05 11:51:08', 'mensaje ', 0, 12),
(14, '2025-05-05 11:59:27', 'mensaje ', 0, 14),
(15, '2025-05-05 12:23:11', 'admin insertó el ejemplar OLIVO_4 el 2025-05-05T12:23:11.321706100', 0, 17);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `fk_idCredenciales` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `nombre`, `email`, `fk_idCredenciales`) VALUES
(0, 'admin', 'admin@vivero.com', 1),
(2, 'alex', 'alex@vivero.es', 2),
(3, 'luis', 'luis@vivero.es', 3),
(11, 'oscar mateos', 'oscar@vivero.es', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plantas`
--

CREATE TABLE `plantas` (
  `codigo` varchar(10) NOT NULL,
  `nombreComun` varchar(100) DEFAULT NULL,
  `nombreCientifico` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `plantas`
--

INSERT INTO `plantas` (`codigo`, `nombreComun`, `nombreCientifico`) VALUES
('AMAPOLA', 'amapola', 'amapolade'),
('CAMELIA', 'camelia', 'cameliade'),
('GIRASOL', 'girasol', 'girasolde'),
('MANZANO', 'manzano comun', 'manzanode comun'),
('MARGARITA', 'margarita', 'margaritade'),
('OLIVO', 'olivo', 'olivode'),
('PERAL', 'peral', 'peralde'),
('PETUNIA', 'petunia', 'petuniade'),
('ROSA', 'rosa', 'rosade'),
('TULIPAN', 'tulipan', 'tulipande');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_planta` (`fk_planta`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_idPersona` (`fk_idPersona`),
  ADD KEY `fk_idEjemplar` (`fk_idEjemplar`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_idCredenciales` (`fk_idCredenciales`);

--
-- Indices de la tabla `plantas`
--
ALTER TABLE `plantas`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD CONSTRAINT `ejemplares_ibfk_1` FOREIGN KEY (`fk_planta`) REFERENCES `plantas` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
