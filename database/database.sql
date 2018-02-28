-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le :  mer. 28 fév. 2018 à 10:51
-- Version du serveur :  10.1.30-MariaDB
-- Version de PHP :  7.0.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `u606391292_lpepd`
--

-- --------------------------------------------------------

--
-- Structure de la table `exercice`
--

CREATE TABLE `exercice` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `exerciceAvancement`
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
-- Structure de la table `exerciceNiveau`
--

CREATE TABLE `exerciceNiveau` (
  `id` int(11) NOT NULL,
  `niveauId` int(11) NOT NULL,
  `exerciceId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `joueurAvancement`
--

CREATE TABLE `joueurAvancement` (
  `id` int(11) NOT NULL,
  `pseudoId` int(11) NOT NULL,
  `niveauId` int(11) NOT NULL,
  `pourcentage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `membre`
--

CREATE TABLE `membre` (
  `id` int(11) NOT NULL,
  `pseudo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `age` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

CREATE TABLE `niveau` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `exercice`
--
ALTER TABLE `exercice`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `exerciceAvancement`
--
ALTER TABLE `exerciceAvancement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pseudo` (`pseudoId`),
  ADD KEY `exercice` (`exerciceId`);

--
-- Index pour la table `exerciceNiveau`
--
ALTER TABLE `exerciceNiveau`
  ADD PRIMARY KEY (`id`),
  ADD KEY `niveau` (`niveauId`),
  ADD KEY `exercice2` (`exerciceId`);

--
-- Index pour la table `joueurAvancement`
--
ALTER TABLE `joueurAvancement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pseudo2` (`pseudoId`),
  ADD KEY `niveau2` (`niveauId`);

--
-- Index pour la table `membre`
--
ALTER TABLE `membre`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pseudo` (`pseudo`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `niveau`
--
ALTER TABLE `niveau`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `exercice`
--
ALTER TABLE `exercice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `exerciceAvancement`
--
ALTER TABLE `exerciceAvancement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `exerciceNiveau`
--
ALTER TABLE `exerciceNiveau`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `joueurAvancement`
--
ALTER TABLE `joueurAvancement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `membre`
--
ALTER TABLE `membre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `niveau`
--
ALTER TABLE `niveau`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `exerciceAvancement`
--
ALTER TABLE `exerciceAvancement`
  ADD CONSTRAINT `exercice` FOREIGN KEY (`exerciceId`) REFERENCES `exercice` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pseudo` FOREIGN KEY (`pseudoId`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `exerciceNiveau`
--
ALTER TABLE `exerciceNiveau`
  ADD CONSTRAINT `exercice2` FOREIGN KEY (`exerciceId`) REFERENCES `exercice` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `niveau` FOREIGN KEY (`niveauId`) REFERENCES `niveau` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `joueurAvancement`
--
ALTER TABLE `joueurAvancement`
  ADD CONSTRAINT `niveau2` FOREIGN KEY (`niveauId`) REFERENCES `niveau` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pseudo2` FOREIGN KEY (`pseudoId`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
