-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 26 oct. 2021 à 20:32
-- Version du serveur :  10.1.36-MariaDB
-- Version de PHP :  7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `sadak@`
--

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `commande_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `PrixTot` float NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `facture_produit`
--

CREATE TABLE `facture_produit` (
  `id_fac` int(10) NOT NULL,
  `date_fac` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `famille`
--

CREATE TABLE `famille` (
  `id_f` int(11) NOT NULL,
  `adresse_f` varchar(255) NOT NULL,
  `ville_f` varchar(50) NOT NULL,
  `cp_f` int(11) NOT NULL,
  `nom_p` varchar(50) NOT NULL,
  `prenom_p` varchar(50) NOT NULL,
  `tele_p` int(11) NOT NULL,
  `d_naissance_p` varchar(255) NOT NULL,
  `s_revenue_p` varchar(50) NOT NULL,
  `nom_m` varchar(50) NOT NULL,
  `prenom_m` varchar(50) NOT NULL,
  `tele_m` int(11) NOT NULL,
  `d_naissance_m` varchar(255) NOT NULL,
  `s_revenue_m` varchar(50) NOT NULL,
  `r_brute_f` float NOT NULL,
  `nbr_enf` int(11) NOT NULL,
  `nbr_enf_malade` int(11) NOT NULL,
  `besoin_f` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `famille`
--

INSERT INTO `famille` (`id_f`, `adresse_f`, `ville_f`, `cp_f`, `nom_p`, `prenom_p`, `tele_p`, `d_naissance_p`, `s_revenue_p`, `nom_m`, `prenom_m`, `tele_m`, `d_naissance_m`, `s_revenue_m`, `r_brute_f`, `nbr_enf`, `nbr_enf_malade`, `besoin_f`) VALUES
(4, 'fsfddd', 'sfdssdf', 7070, 'mouhamed', 'mabrouk', 5245, '12/10/1969', 'temps partielle', 'samia', 'staali', 26890104, '10/08/1971', 'temp partielle', 3.9, 3, 1, 'nourriture');

-- --------------------------------------------------------

--
-- Structure de la table `news`
--

CREATE TABLE `news` (
  `event_id` int(10) NOT NULL,
  `event_date` varchar(20) NOT NULL,
  `event_content` varchar(10) NOT NULL,
  `event_organiser` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `id` int(11) NOT NULL,
  `commande_id` int(11) NOT NULL,
  `ref_produit` int(11) NOT NULL,
  `quantite_produit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `panier_temp`
--

CREATE TABLE `panier_temp` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `ref_produit` int(11) NOT NULL,
  `quantite_produit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `panier_temp`
--

INSERT INTO `panier_temp` (`id`, `user_id`, `ref_produit`, `quantite_produit`) VALUES
(1, 1, 10, 101),
(2, 1, 2, 100),
(3, 1, 11, 3),
(4, 1, 33, 3);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `ref_produit` int(10) NOT NULL,
  `nom_produit` varchar(20) NOT NULL,
  `prix_produit` float NOT NULL,
  `date_expiration` varchar(50) NOT NULL,
  `quantite_produit` int(5) NOT NULL,
  `categorie` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`ref_produit`, `nom_produit`, `prix_produit`, `date_expiration`, `quantite_produit`, `categorie`, `user_id`) VALUES
(2, 'sssss', 21, '2021-10-06', 10, 'mmmms', 1),
(10, 'lait', 2, '2021-10-06', 200, 'mmmm', 1),
(11, 'qq', 111, '2021-10-06', 3, 'mmmm', 1),
(33, 'ma9arouna', 0.5, '11/12/2021', 3, 'mmmmmm', 1);

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

CREATE TABLE `service` (
  `id_svc` int(10) NOT NULL,
  `nom_svc` varchar(20) NOT NULL,
  `intv_dispo` varchar(50) NOT NULL,
  `Profession` varchar(20) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `ville` varchar(10) NOT NULL,
  `num_tel` int(10) NOT NULL,
  `zone_dispo` varchar(30) NOT NULL,
  `nom_volontaire` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `username` varchar(50) NOT NULL,
  `nom_u` varchar(50) NOT NULL,
  `prenom_u` varchar(50) NOT NULL,
  `email_u` varchar(255) NOT NULL,
  `mdp_u` varchar(50) NOT NULL,
  `type_u` varchar(50) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`username`, `nom_u`, `prenom_u`, `email_u`, `mdp_u`, `type_u`, `user_id`) VALUES
('test', 'ttt', 'pppp', 'pp@', 'qqq', 'ppppp', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`commande_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Index pour la table `facture_produit`
--
ALTER TABLE `facture_produit`
  ADD PRIMARY KEY (`id_fac`);

--
-- Index pour la table `famille`
--
ALTER TABLE `famille`
  ADD PRIMARY KEY (`id_f`);

--
-- Index pour la table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`event_id`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id`),
  ADD KEY `commande_id` (`commande_id`,`ref_produit`),
  ADD KEY `ref_produit` (`ref_produit`);

--
-- Index pour la table `panier_temp`
--
ALTER TABLE `panier_temp`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `ref_produit` (`ref_produit`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`ref_produit`),
  ADD KEY `user_id` (`user_id`);

--
-- Index pour la table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id_svc`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `commande_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `facture_produit`
--
ALTER TABLE `facture_produit`
  MODIFY `id_fac` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `famille`
--
ALTER TABLE `famille`
  MODIFY `id_f` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `news`
--
ALTER TABLE `news`
  MODIFY `event_id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `panier_temp`
--
ALTER TABLE `panier_temp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `ref_produit` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT pour la table `service`
--
ALTER TABLE `service`
  MODIFY `id_svc` int(10) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`user_id`);

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `panier_ibfk_2` FOREIGN KEY (`ref_produit`) REFERENCES `produit` (`ref_produit`),
  ADD CONSTRAINT `panier_ibfk_3` FOREIGN KEY (`commande_id`) REFERENCES `commande` (`commande_id`);

--
-- Contraintes pour la table `panier_temp`
--
ALTER TABLE `panier_temp`
  ADD CONSTRAINT `panier_temp_ibfk_1` FOREIGN KEY (`ref_produit`) REFERENCES `produit` (`ref_produit`),
  ADD CONSTRAINT `panier_temp_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`user_id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produit_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
