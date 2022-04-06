<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211209121624 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE remarque_famille DROP FOREIGN KEY FK_E9D60E86266963BB');
        $this->addSql('DROP INDEX IDX_E9D60E86266963BB ON remarque_famille');
        $this->addSql('ALTER TABLE remarque_famille DROP idF');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE remarque_famille ADD idF INT DEFAULT NULL');
        $this->addSql('ALTER TABLE remarque_famille ADD CONSTRAINT FK_E9D60E86266963BB FOREIGN KEY (idF) REFERENCES famille (id_f)');
        $this->addSql('CREATE INDEX IDX_E9D60E86266963BB ON remarque_famille (idF)');
    }
}
