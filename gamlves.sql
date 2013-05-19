-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 19, 2013 at 01:59 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `gamlves`
--
CREATE DATABASE `gamlves` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `gamlves`;

-- --------------------------------------------------------

GRANT USAGE ON *.* TO 'gamlves'@'localhost' IDENTIFIED BY PASSWORD '*B1920BDF7B224EDDE4E039DA931D2DD8D178989B';

GRANT ALL PRIVILEGES ON `gamlves`.* TO 'gamlves'@'localhost' WITH GRANT OPTION;

USE gamlves;

--
-- Table structure for table `Juegos`
--

CREATE TABLE IF NOT EXISTS `Juegos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(40) NOT NULL,
  `Genero` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `Juegos`
--

INSERT INTO `Juegos` (`ID`, `Nombre`, `Genero`) VALUES
(2, 'test', 'Otro'),
(3, 'test2', 'Acción'),
(4, 'test3', 'Casual');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `Usuarios`
--

INSERT INTO `Usuarios` (`ID`, `Nombre`, `User`, `Pass`) VALUES
(1, 'Administrador', 'admin', '21232f297a57a5a743894a0e4a801fc3'),
(2, 'test', 'test', '098f6bcd4621d373cade4e832627b4f6'),
(3, 'TEST', 'test2', 'ad0234829205b9033196ba818f7a872b'),
(4, 'test', 'test3', '098f6bcd4621d373cade4e832627b4f6'),
(5, 'test4', 'test4', '86985e105f79b95d6bc918fb45ec7727');

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
-- RELATIONS FOR TABLE `UsuariosJuegos`:
--   `User`
--       `Usuarios` -> `User`
--   `IDJuego`
--       `Juegos` -> `ID`
--

--
-- Dumping data for table `UsuariosJuegos`
--

INSERT INTO `UsuariosJuegos` (`IDJuego`, `User`) VALUES
(2, 'test'),
(3, 'test'),
(4, 'test'),
(2, 'test2'),
(3, 'test2');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `UsuariosJuegos`
--
ALTER TABLE `UsuariosJuegos`
  ADD CONSTRAINT `UsuariosJuegos_ibfk_4` FOREIGN KEY (`User`) REFERENCES `Usuarios` (`User`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `UsuariosJuegos_ibfk_3` FOREIGN KEY (`IDJuego`) REFERENCES `Juegos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

