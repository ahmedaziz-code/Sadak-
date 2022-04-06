<?php

namespace App\Entity;

use App\Repository\ServiceRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;



/**
 * @ORM\Entity(repositoryClass=ServiceRepository::class)
 * @UniqueEntity("name")
 */
class Service
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Assert\NotBlank
     * @Assert\Type("string")
     */
    private $name;

    /**
     * @ORM\Column(type="text", nullable=true)
     * @Assert\NotBlank
     * @Assert\Type("string")
     * @Assert\Length(min=4)
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Assert\NotBlank
     * @Assert\Regex(
     *     pattern     = "/^[0-9]+$/i",
     *     htmlPattern = "[0-9]+"
     * )
     * @Assert\Length(
     *      min = 8,
     *      max = 8,
     *      minMessage = "Your first name must be at least {{ limit }} characters long",
     *      maxMessage = "Your first name cannot be longer than {{ limit }} characters"
     * )
     */
    private $numVert;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Assert\NotBlank
     * @Assert\Type("string")
     * @Assert\Length(min=4)
     */
    private $zoneDispo;

    /**
     * @ORM\ManyToOne(targetEntity=Type::class, inversedBy="services")
     * @Assert\NotBlank
     */
    private $type;

    /**
     * @ORM\Column(type="datetime", nullable=true)
     */
    private $createdAt;

    /**
     * @ORM\Column(type="boolean", nullable=true)
     */
    private $status;

    /**
     * @ORM\Column(type="datetime", nullable=true)
     * @Assert\Date
     */
    private $dateDebut;

    /**
     * @ORM\Column(type="datetime", nullable=true)
     * @Assert\Date
     */
    private $dateFin;

//    /**
//     * @ORM\ManyToOne(targetEntity=Utilisateur::class, inversedBy="services")
//     */
//    private $addedBy;

    public function __construct()
    {
        $this->createdAt = new \DateTime('now');
        $this->status = true;
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(?string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getNumVert(): ?string
    {
        return $this->numVert;
    }

    public function setNumVert(?string $numVert): self
    {
        $this->numVert = $numVert;

        return $this;
    }

    public function getZoneDispo(): ?string
    {
        return $this->zoneDispo;
    }

    public function setZoneDispo(?string $zoneDispo): self
    {
        $this->zoneDispo = $zoneDispo;

        return $this;
    }

    public function getType(): ?Type
    {
        return $this->type;
    }

    public function setType(?Type $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getCreatedAt(): ?\DateTimeInterface
    {
        return $this->createdAt;
    }

    public function setCreatedAt(?\DateTimeInterface $createdAt): self
    {
        $this->createdAt = $createdAt;

        return $this;
    }

    public function getStatus(): ?bool
    {
        return $this->status;
    }

    public function setStatus(?bool $status): self
    {
        $this->status = $status;

        return $this;
    }

//    public function getAddedBy(): ?Utilisateur
//    {
//        return $this->addedBy;
//    }
//
//    public function setAddedBy(?Utilisateur $addedBy): self
//    {
//        $this->addedBy = $addedBy;
//
//        return $this;
//    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->dateDebut;
    }

    public function setDateDebut(?\DateTimeInterface $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->dateFin;
    }

    public function setDateFin(?\DateTimeInterface $dateFin): self
    {
        $this->dateFin = $dateFin;

        return $this;
    }
}
