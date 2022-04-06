<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211207174402 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE event ADD category_event_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE event ADD CONSTRAINT FK_3BAE0AA7C68D6CF0 FOREIGN KEY (category_event_id) REFERENCES category_event (id)');
        $this->addSql('CREATE INDEX IDX_3BAE0AA7C68D6CF0 ON event (category_event_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE event DROP FOREIGN KEY FK_3BAE0AA7C68D6CF0');
        $this->addSql('DROP INDEX IDX_3BAE0AA7C68D6CF0 ON event');
        $this->addSql('ALTER TABLE event DROP category_event_id');
    }
}
