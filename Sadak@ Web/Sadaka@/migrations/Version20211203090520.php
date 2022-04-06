<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211203090520 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE famille (id_f INT AUTO_INCREMENT NOT NULL, pays VARCHAR(255) NOT NULL, etat_f VARCHAR(255) NOT NULL, ville_f VARCHAR(50) NOT NULL, nom_p VARCHAR(50) NOT NULL, prenom_p VARCHAR(50) NOT NULL, tele_p INT NOT NULL, d_naissance_p DATETIME NOT NULL, s_revenue_p VARCHAR(50) NOT NULL, nom_m VARCHAR(50) NOT NULL, prenom_m VARCHAR(50) NOT NULL, tele_m INT NOT NULL, d_naissance_m DATETIME NOT NULL, dateV DATETIME NOT NULL, remarque DOUBLE PRECISION NOT NULL, s_revenue_m VARCHAR(50) NOT NULL, r_brute_f DOUBLE PRECISION NOT NULL, nbr_enf INT NOT NULL, nbr_enf_malade INT NOT NULL, besoin_f VARCHAR(255) NOT NULL, verif_f VARCHAR(255) NOT NULL, PRIMARY KEY(id_f)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE news (event_id INT AUTO_INCREMENT NOT NULL, event_name VARCHAR(20) NOT NULL, event_date VARCHAR(20) NOT NULL, event_dateF VARCHAR(20) NOT NULL, event_content VARCHAR(500) NOT NULL, event_organiser VARCHAR(20) DEFAULT \'\'\'Mohammed\'\'\' NOT NULL, event_image VARCHAR(300) DEFAULT \'NULL\', event_participation TINYINT(1) NOT NULL, PRIMARY KEY(event_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE remarque_famille (idR INT AUTO_INCREMENT NOT NULL, remarque INT NOT NULL, dateVerif DATETIME NOT NULL, idF INT DEFAULT NULL, INDEX IDX_E9D60E86266963BB (idF), PRIMARY KEY(idR)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE service (id_svc INT AUTO_INCREMENT NOT NULL, nom_svc VARCHAR(20) NOT NULL, intv_dispo VARCHAR(50) NOT NULL, Profession VARCHAR(20) NOT NULL, adresse VARCHAR(100) NOT NULL, ville VARCHAR(10) NOT NULL, num_tel INT NOT NULL, zone_dispo VARCHAR(30) NOT NULL, nom_volontaire VARCHAR(20) NOT NULL, PRIMARY KEY(id_svc)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE utilisateur (id_u INT AUTO_INCREMENT NOT NULL, username VARCHAR(255) NOT NULL, nom_u VARCHAR(50) NOT NULL, prenom_u VARCHAR(50) NOT NULL, email_u VARCHAR(255) NOT NULL, mdp_u VARCHAR(50) NOT NULL, pays_u VARCHAR(255) NOT NULL, gouvernorat VARCHAR(255) NOT NULL, ville VARCHAR(255) NOT NULL, profession_u VARCHAR(255) NOT NULL, type_u VARCHAR(50) NOT NULL, PRIMARY KEY(id_u)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE remarque_famille ADD CONSTRAINT FK_E9D60E86266963BB FOREIGN KEY (idF) REFERENCES famille (id_f)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE remarque_famille DROP FOREIGN KEY FK_E9D60E86266963BB');
        $this->addSql('DROP TABLE famille');
        $this->addSql('DROP TABLE news');
        $this->addSql('DROP TABLE remarque_famille');
        $this->addSql('DROP TABLE service');
        $this->addSql('DROP TABLE utilisateur');
    }
}
