<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211209220621 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE commande (commande_id INT AUTO_INCREMENT NOT NULL, id INT DEFAULT NULL, PrixTot DOUBLE PRECISION NOT NULL, date DATE DEFAULT NULL, INDEX id (id), PRIMARY KEY(commande_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE facture_produit (id_fac INT AUTO_INCREMENT NOT NULL, date_fac VARCHAR(10) NOT NULL, PRIMARY KEY(id_fac)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE livraison (livraison_id INT AUTO_INCREMENT NOT NULL, commande_id INT DEFAULT NULL, id INT DEFAULT NULL, livreur_id INT DEFAULT NULL, nom VARCHAR(50) NOT NULL, prenom VARCHAR(50) NOT NULL, adresse VARCHAR(50) NOT NULL, etat VARCHAR(50) DEFAULT \'en atttente\' NOT NULL, INDEX livreur_id (livreur_id), INDEX commande_id (commande_id), INDEX id (id), PRIMARY KEY(livraison_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE panier (id INT AUTO_INCREMENT NOT NULL, ref_produit INT DEFAULT NULL, commande_id INT DEFAULT NULL, quantite_produit INT NOT NULL, INDEX ref_produit (ref_produit), INDEX commande_id (commande_id, ref_produit), INDEX IDX_24CC0DF282EA2E54 (commande_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE panier_temp (id INT AUTO_INCREMENT NOT NULL, ref_produit INT DEFAULT NULL, quantite_produit INT NOT NULL, INDEX id (id), INDEX ref_produit (ref_produit), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE produit (ref_produit INT AUTO_INCREMENT NOT NULL, id INT DEFAULT NULL, nom_produit VARCHAR(20) NOT NULL, prix_produit DOUBLE PRECISION NOT NULL, date_expiration VARCHAR(50) NOT NULL, quantite_produit INT NOT NULL, categorie VARCHAR(20) NOT NULL, image VARCHAR(255) NOT NULL, INDEX id (id), PRIMARY KEY(ref_produit)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67DBF396750 FOREIGN KEY (id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE livraison ADD CONSTRAINT FK_A60C9F1F82EA2E54 FOREIGN KEY (commande_id) REFERENCES commande (commande_id)');
        $this->addSql('ALTER TABLE livraison ADD CONSTRAINT FK_A60C9F1FBF396750 FOREIGN KEY (id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE livraison ADD CONSTRAINT FK_A60C9F1FF8646701 FOREIGN KEY (livreur_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE panier ADD CONSTRAINT FK_24CC0DF2EDB1BFF7 FOREIGN KEY (ref_produit) REFERENCES produit (ref_produit)');
        $this->addSql('ALTER TABLE panier ADD CONSTRAINT FK_24CC0DF282EA2E54 FOREIGN KEY (commande_id) REFERENCES commande (commande_id)');
        $this->addSql('ALTER TABLE panier_temp ADD CONSTRAINT FK_FFB5A0B3EDB1BFF7 FOREIGN KEY (ref_produit) REFERENCES produit (ref_produit)');
        $this->addSql('ALTER TABLE panier_temp ADD CONSTRAINT FK_FFB5A0B3BF396750 FOREIGN KEY (id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE produit ADD CONSTRAINT FK_29A5EC27BF396750 FOREIGN KEY (id) REFERENCES user (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE livraison DROP FOREIGN KEY FK_A60C9F1F82EA2E54');
        $this->addSql('ALTER TABLE panier DROP FOREIGN KEY FK_24CC0DF282EA2E54');
        $this->addSql('ALTER TABLE panier DROP FOREIGN KEY FK_24CC0DF2EDB1BFF7');
        $this->addSql('ALTER TABLE panier_temp DROP FOREIGN KEY FK_FFB5A0B3EDB1BFF7');
        $this->addSql('DROP TABLE commande');
        $this->addSql('DROP TABLE facture_produit');
        $this->addSql('DROP TABLE livraison');
        $this->addSql('DROP TABLE panier');
        $this->addSql('DROP TABLE panier_temp');
        $this->addSql('DROP TABLE produit');
    }
}
