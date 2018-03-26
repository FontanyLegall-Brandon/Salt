-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: mysql-lpepd.alwaysdata.net
-- Generation Time: Mar 26, 2018 at 10:09 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lpepd_database`
--
CREATE DATABASE IF NOT EXISTS `lpepd_database` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `lpepd_database`;

-- --------------------------------------------------------

--
-- Table structure for table `exercice`
--

CREATE TABLE `exercice` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `exerciceAvancement`
--

CREATE TABLE `exerciceAvancement` (
  `id` int(11) NOT NULL,
  `pseudoId` int(11) NOT NULL,
  `exerciceId` int(11) NOT NULL,
  `fait` tinyint(1) NOT NULL COMMENT 'boolean',
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `note` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `exerciceNiveau`
--

CREATE TABLE `exerciceNiveau` (
  `id` int(11) NOT NULL,
  `niveauId` int(11) NOT NULL,
  `exerciceId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `joueurAvancement`
--

CREATE TABLE `joueurAvancement` (
  `id` int(11) NOT NULL,
  `pseudoId` int(11) NOT NULL,
  `niveauId` int(11) NOT NULL,
  `pourcentage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `membre`
--

CREATE TABLE `membre` (
  `id` int(11) NOT NULL,
  `pseudo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `niveau`
--

CREATE TABLE `niveau` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `exercice`
--
ALTER TABLE `exercice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `exerciceAvancement`
--
ALTER TABLE `exerciceAvancement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pseudo` (`pseudoId`),
  ADD KEY `exercice` (`exerciceId`);

--
-- Indexes for table `exerciceNiveau`
--
ALTER TABLE `exerciceNiveau`
  ADD PRIMARY KEY (`id`),
  ADD KEY `niveau` (`niveauId`),
  ADD KEY `exercice2` (`exerciceId`);

--
-- Indexes for table `joueurAvancement`
--
ALTER TABLE `joueurAvancement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pseudo2` (`pseudoId`),
  ADD KEY `niveau2` (`niveauId`);

--
-- Indexes for table `membre`
--
ALTER TABLE `membre`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pseudo` (`pseudo`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `niveau`
--
ALTER TABLE `niveau`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `exercice`
--
ALTER TABLE `exercice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `exerciceAvancement`
--
ALTER TABLE `exerciceAvancement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `exerciceNiveau`
--
ALTER TABLE `exerciceNiveau`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `joueurAvancement`
--
ALTER TABLE `joueurAvancement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `membre`
--
ALTER TABLE `membre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;

--
-- AUTO_INCREMENT for table `niveau`
--
ALTER TABLE `niveau`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `exerciceAvancement`
--
ALTER TABLE `exerciceAvancement`
  ADD CONSTRAINT `exercice` FOREIGN KEY (`exerciceId`) REFERENCES `exercice` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pseudo` FOREIGN KEY (`pseudoId`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `exerciceNiveau`
--
ALTER TABLE `exerciceNiveau`
  ADD CONSTRAINT `exercice2` FOREIGN KEY (`exerciceId`) REFERENCES `exercice` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `niveau` FOREIGN KEY (`niveauId`) REFERENCES `niveau` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `joueurAvancement`
--
ALTER TABLE `joueurAvancement`
  ADD CONSTRAINT `niveau2` FOREIGN KEY (`niveauId`) REFERENCES `niveau` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pseudo2` FOREIGN KEY (`pseudoId`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
