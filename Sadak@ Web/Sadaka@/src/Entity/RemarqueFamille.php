<?php

namespace App\Entity;

use App\Repository\RemarqueFamilleRepository;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\ORM\Mapping\JoinColumn;
use Doctrine\ORM\Mapping\ManyToOne;
use App\Entity\Famille;

/**
 * @ORM\Entity(repositoryClass=RemarqueFamilleRepository::class)
 */
class RemarqueFamille
{

    /**
     * @ORM\ManyToOne(targetEntity="Famille", inversedBy="remarqueFs")
     * @ORM\JoinColumn(name="idF", referencedColumnName="id_f")
     */
    private $famille;
    /**
     * @var int
     *
     * @ORM\Column(name="idR", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idr;

    /**
     * @var int
     *
     * @ORM\Column(name="remarque", type="integer", nullable=false)
     */
    private $remarque;

    /**
     *
     * @ORM\Column(name="dateVerif", type="datetime", nullable=false)
     */
    private $dateverif;

    /**
     * @return int
     */
    public function getIdr(): int
    {
        return $this->idr;
    }

    /**
     * @param int $idr
     */
    public function setIdr(int $idr): void
    {
        $this->idr = $idr;
    }

    /**
     * @return int
     */
    public function getRemarque(): ?int
    {
        return $this->remarque;
    }

    /**
     * @param int $remarque
     */
    public function setRemarque(int $remarque): void
    {
        $this->remarque = $remarque;
    }

    /**
     * @return mixed
     */
    public function getDateverif(): ?\DateTimeInterface
    {
        return $this->dateverif;
    }

    /**
     * @param mixed $dateverif
     */
    public function setDateverif(\DateTimeInterface $dateverif): self
    {
        $this->dateverif = $dateverif;
        return $this;
    }

    /**
     * @return mixed
     */
    public function getFamille()
    {
        return $this->famille;
    }

    /**
     * @param mixed $famille
     */
    public function setFamille($famille): void
    {
        $this->famille = $famille;
    }



}
