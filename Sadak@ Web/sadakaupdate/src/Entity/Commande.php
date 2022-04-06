<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Entity\User;

/**
 * Commande
 *
 * @ORM\Table(name="commande", indexes={@ORM\Index(name="id", columns={"id"})})
 *  @ORM\Entity(repositoryClass="App\Repository\CommandeRepository")
 */
class Commande
{
    /**
     * @var int
     *
     * @ORM\Column(name="commande_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $commandeId;

    /**
     * @var float
     *
     * @ORM\Column(name="PrixTot", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixtot;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date", type="date", nullable=true)
     */
    private $date;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id", referencedColumnName="id")
     * })
     */
    private $user;

    public function getCommandeId(): ?int
    {
        return $this->commandeId;
    }

    public function getPrixtot(): ?float
    {
        return $this->prixtot;
    }

    public function setPrixtot(float $prixtot): self
    {
        $this->prixtot = $prixtot;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(?\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getUser(): ?User
    {
        return $this->getUser();
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }


}
