-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 21, 2021 at 12:30 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sadak@`
--

-- --------------------------------------------------------

--
-- Table structure for table `facture_produit`
--

CREATE TABLE `facture_produit` (
  `id_fac` int(10) NOT NULL,
  `date_fac` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `famille`
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
-- Dumping data for table `famille`
--

INSERT INTO `famille` (`id_f`, `adresse_f`, `ville_f`, `cp_f`, `nom_p`, `prenom_p`, `tele_p`, `d_naissance_p`, `s_revenue_p`, `nom_m`, `prenom_m`, `tele_m`, `d_naissance_m`, `s_revenue_m`, `r_brute_f`, `nbr_enf`, `nbr_enf_malade`, `besoin_f`) VALUES
(4, 'fsfddd', 'sfdssdf', 7070, 'mouhamed', 'mabrouk', 5245, '12/10/1969', 'temps partielle', 'samia', 'staali', 26890104, '10/08/1971', 'temp partielle', 3.9, 3, 1, 'nourriture'),
(6, 'rue 15 janvier', 'ariana', 7070, 'mouhamed', 'mabrouk', 25840123, '12/10/1969', 'temps partielle', 'samia', 'staali', 26890104, '10/08/1971', 'temp partielle', 3.9, 3, 1, 'nourriture'),
(7, 'rue 15 janvier', 'ariana', 7070, 'mouhamed', 'mabrouk', 25840123, '12/10/1969', 'temps partielle', 'samia', 'staali', 26890104, '10/08/1971', 'temp partielle', 3.9, 3, 1, 'nourriture'),
(8, 'ruee 15 janvier', 'ariana', 7170, 'mouhamed', 'mabrouk', 25840123, '12/10/1969', 'temps partielle', 'samia', 'staali', 26890104, '10/08/1971', 'temp partielle', 3.9, 3, 1, 'nourriture'),
(9, 'ruee 15 janvier', 'ariana', 7170, 'mouhamed', 'mabrouk', 25840123, '12/10/1969', 'temps partielle', 'samia', 'staali', 26890104, '10/08/1971', 'temp partielle', 3.9, 3, 1, 'nourriture'),
(10, 'ruee 15 janvier', 'ariana', 7170, 'mouhamed', 'mabrouk', 25840123, '12/10/1969', 'temps partielle', 'samia', 'staali', 26890104, '10/08/1971', 'temp partielle', 3.9, 3, 1, 'nourriture');

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `event_id` int(10) NOT NULL,
  `event_date` varchar(20) NOT NULL,
  `event_content` varchar(10) NOT NULL,
  `event_organiser` varchar(20) NOT NULL,
  `event_image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`event_id`, `event_date`, `event_content`, `event_organiser`, `event_image`) VALUES
(1, 'ahahhaha', 'Noob', '2021/08', ''),
(2, 'Bye', 'Newbie', '2021/10/07', ''),
(3, 'Bye', 'alaa', '2021/10/07', '');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `ref_produit` int(10) NOT NULL,
  `nom_produit` varchar(20) NOT NULL,
  `prix_produit` float NOT NULL,
  `date_expiration` varchar(50) NOT NULL,
  `quantite_produit` int(5) NOT NULL,
  `categorie` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `produits_vendus`
--

CREATE TABLE `produits_vendus` (
  `id_v` int(10) NOT NULL,
  `date_v` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `service`
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
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `username` varchar(50) NOT NULL,
  `nom_u` varchar(50) NOT NULL,
  `prenom_u` varchar(50) NOT NULL,
  `email_u` varchar(255) NOT NULL,
  `mdp_u` varchar(50) NOT NULL,
  `type_u` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `vente`
--

CREATE TABLE `vente` (
  `id_produit` int(11) NOT NULL,
  `prix_produit` float NOT NULL,
  `date_expiration` varchar(10) NOT NULL,
  `quantite_produit` int(5) NOT NULL,
  `categorie_produit` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `facture_produit`
--
ALTER TABLE `facture_produit`
  ADD PRIMARY KEY (`id_fac`);

--
-- Indexes for table `famille`
--
ALTER TABLE `famille`
  ADD PRIMARY KEY (`id_f`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`event_id`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`ref_produit`);

--
-- Indexes for table `produits_vendus`
--
ALTER TABLE `produits_vendus`
  ADD PRIMARY KEY (`id_v`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id_svc`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `vente`
--
ALTER TABLE `vente`
  ADD PRIMARY KEY (`id_produit`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `facture_produit`
--
ALTER TABLE `facture_produit`
  MODIFY `id_fac` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `famille`
--
ALTER TABLE `famille`
  MODIFY `id_f` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `event_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `ref_produit` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `produits_vendus`
--
ALTER TABLE `produits_vendus`
  MODIFY `id_v` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `id_svc` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vente`
--
ALTER TABLE `vente`
  MODIFY `id_produit` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
