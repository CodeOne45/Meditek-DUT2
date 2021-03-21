-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 20 mars 2021 à 23:55
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_medlatek`
--

-- --------------------------------------------------------

--
-- Structure de la table `book`
--

CREATE TABLE `book` (
  `id_doc` int(11) NOT NULL,
  `auteur` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `book`
--

INSERT INTO `book` (`id_doc`, `auteur`) VALUES
(1, 'J. K. Rowling'),
(8, 'TestMan');

-- --------------------------------------------------------

--
-- Structure de la table `cd`
--

CREATE TABLE `cd` (
  `id_doc` int(11) NOT NULL,
  `artiste` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cd`
--

INSERT INTO `cd` (`id_doc`, `artiste`) VALUES
(7, 'PNL');

-- --------------------------------------------------------

--
-- Structure de la table `documents`
--

CREATE TABLE `documents` (
  `id_doc` int(11) NOT NULL,
  `titre_doc` longtext NOT NULL,
  `description` varchar(20) NOT NULL,
  `borrow` varchar(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `id_borrower` int(11) DEFAULT NULL,
  `date_doc` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `documents`
--

INSERT INTO `documents` (`id_doc`, `titre_doc`, `description`, `borrow`, `type`, `id_borrower`, `date_doc`) VALUES
(1, 'Harry Poter', 'HP tkkt tu connais', 'libre', 'Livre', NULL, '2021-03-20'),
(6, 'God plans', 'Drake', 'libre', 'CD', NULL, '2021-03-20'),
(7, 'Au DD', 'Rap-Cloud\r\n', 'libre', 'CD', NULL, '2021-03-20');

-- --------------------------------------------------------

--
-- Structure de la table `dvd`
--

CREATE TABLE `dvd` (
  `id_doc` int(11) NOT NULL,
  `realisateur` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `dvd`
--

INSERT INTO `dvd` (`id_doc`, `realisateur`) VALUES
(5, 'David Yates');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `isBibliothecaire` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id_user`, `email`, `pwd`, `isBibliothecaire`) VALUES
(1, 'admin@exemple.fr', 'admin', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id_doc`);

ALTER TABLE `book`
ADD CONSTRAINT 'FK_dvd'
  FOREIGN KEY (`id_doc`)
  REFERENCES `DOCUMENTS` (`id_doc`)
  ON DELETE CASCADE;

--
-- Index pour la table `cd`
--
ALTER TABLE `cd`
  ADD PRIMARY KEY (`id_doc`);

ALTER TABLE `cd`
ADD CONSTRAINT 'FK_cd'
  FOREIGN KEY (`id_doc`)
  REFERENCES `documents` (`id_doc`)
  ON DELETE CASCADE;
--
-- Index pour la table `documents`
--
ALTER TABLE `documents`
  ADD PRIMARY KEY (`id_doc`);

--
-- Index pour la table `dvd`
--
ALTER TABLE `dvd`
  ADD PRIMARY KEY (`id_doc`);

ALTER TABLE `dvd`
ADD CONSTRAINT 'FK_dvd'
  FOREIGN KEY (`id_doc`)
  REFERENCES `documents` (`id_doc`)
  ON DELETE CASCADE;
--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `documents`
--
ALTER TABLE `documents`
  MODIFY `id_doc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
