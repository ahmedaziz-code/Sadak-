<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Entity\User;

/**
 * FactureProduit
 *
 * @ORM\Table(name="facture_produit")
 * @ORM\Entity
 */
class FactureProduit
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_fac", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idFac;

    /**
     * @var string
     *
     * @ORM\Column(name="date_fac", type="string", length=10, nullable=false)
     */
    private $dateFac;

    public function getIdFac(): ?int
    {
        return $this->idFac;
    }

    public function getDateFac(): ?string
    {
        return $this->dateFac;
    }

    public function setDateFac(string $dateFac): self
    {
        $this->dateFac = $dateFac;

        return $this;
    }


}
