-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 13, 2013 at 06:49 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

CREATE DATABASE gamlves;


--
-- User: `gamlves`
--

-- --------------------------------------------------------

CREATE USER 'gamlves'@'localhost' IDENTIFIED BY '***';

SET PASSWORD FOR 'gamlves'@'localhost' = ''

GRANT USAGE ON * . * TO 'gamlves'@'localhost' IDENTIFIED BY '***' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;

GRANT ALL PRIVILEGES ON `gamlves` . * TO 'gamlves'@'localhost' WITH GRANT OPTION ;


--
-- Database: `gamlves`
--

-- --------------------------------------------------------

USE gamlves;

--
-- Table structure for table `Juegos`
--

CREATE TABLE IF NOT EXISTS `Juegos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Usuarios`
--

CREATE TABLE IF NOT EXISTS `Usuarios` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) NOT NULL,
  `User` varchar(15) NOT NULL,
  `Pass` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`,`User`),
  KEY `ID` (`ID`),
  KEY `User` (`User`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Usuarios`
--

INSERT INTO `Usuarios` (`ID`, `Nombre`, `User`, `Pass`) VALUES
(1, 'Administrador', 'admin', 'baaab6fa3b287456d2ff691027920826');

-- --------------------------------------------------------

--
-- Table structure for table `UsuariosJuegos`
--

CREATE TABLE IF NOT EXISTS `UsuariosJuegos` (
  `IDJuego` int(11) NOT NULL,
  `User` varchar(15) NOT NULL,
  PRIMARY KEY (`IDJuego`,`User`),
  KEY `User` (`User`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `UsuariosJuegos`
--
ALTER TABLE `UsuariosJuegos`
  ADD CONSTRAINT `UsuariosJuegos_ibfk_4` FOREIGN KEY (`User`) REFERENCES `Usuarios` (`User`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `UsuariosJuegos_ibfk_3` FOREIGN KEY (`IDJuego`) REFERENCES `Juegos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
