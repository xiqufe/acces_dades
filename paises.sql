-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-01-2022 a las 18:37:54
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `paises`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `busquedas`
--

CREATE TABLE `busquedas` (
  `id` int(11) NOT NULL,
  `busqueda` varchar(50) NOT NULL,
  `nombre_oficial` varchar(50) NOT NULL,
  `nombre_comun` varchar(50) NOT NULL,
  `dominio` varchar(10) NOT NULL,
  `region` varchar(15) NOT NULL,
  `moneda` varchar(30) NOT NULL,
  `bandera` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `busquedas`
--

INSERT INTO `busquedas` (`id`, `busqueda`, `nombre_oficial`, `nombre_comun`, `dominio`, `region`, `moneda`, `bandera`) VALUES
(1, 'España', 'Reino de España', 'España', '.es', 'Europe', '€ Euro', 'https://flagcdn.com/w320/es.png'),
(2, 'United Kingdom', 'Reino Unido de Gran Bretaña e Irlanda del Norte', 'Reino Unido', '.uk', 'Europe', '£ British pound', 'https://flagcdn.com/w320/gb.png');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `busquedas`
--
ALTER TABLE `busquedas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `busquedas`
--
ALTER TABLE `busquedas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
