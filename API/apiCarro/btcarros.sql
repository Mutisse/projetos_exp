-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 17, 2023 at 02:28 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbapicarros`
--

-- --------------------------------------------------------

--
-- Table structure for table `btcarros`
--

CREATE TABLE `btcarros` (
  `codigo` int(11) NOT NULL,
  `modelo` varchar(30) NOT NULL,
  `placa` varchar(20) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `btcarros`
--

INSERT INTO `btcarros` (`codigo`, `modelo`, `placa`, `status`) VALUES
(1, ' Fusca', 'AGR 3876 mp', 1),
(2, 'Mazda Bt50', 'ASX 8364 mc', 1),
(3, 'Isuzu', 'AKZ 9823 mp', 1),
(4, 'Marcedez', 'AKJ 873 mp', 0),
(5, 'BMW', 'AKZ 28376  mp', 1),
(6, 'Nissa Navara', 'ACM 1697  mc', 1),
(7, 'VW Golf', 'AQC 1697  mc', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `btcarros`
--
ALTER TABLE `btcarros`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `btcarros`
--
ALTER TABLE `btcarros`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
