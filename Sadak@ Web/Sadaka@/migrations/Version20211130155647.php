<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211130155647 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE remarque_famille (idR INT AUTO_INCREMENT NOT NULL, remarque INT NOT NULL, dateVerif DATETIME NOT NULL, idF INT DEFAULT NULL, INDEX IDX_E9D60E86266963BB (idF), PRIMARY KEY(idR)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE remarque_famille ADD CONSTRAINT FK_E9D60E86266963BB FOREIGN KEY (idF) REFERENCES famille (idF)');
        $this->addSql('DROP TABLE remarquefamille');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE remarquefamille (idR INT AUTO_INCREMENT NOT NULL, remarque INT NOT NULL, dateVerif DATETIME NOT NULL, PRIMARY KEY(idR)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('DROP TABLE remarque_famille');
    }
}
