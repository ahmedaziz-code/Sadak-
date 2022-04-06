<?php

namespace App\Entity;

use App\Repository\DemandeRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity(repositoryClass=DemandeRepository::class)
 */
class Demande
{
    /**
     * @var int
     *
     * @ORM\Column(name="demandeId", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $demandeid;

    /**
     * @var string
     * @Assert\NotNull
     * @Assert\Type("string")
     * @Assert\Length(
     *      min = 2,
     *      max = 10,
     *      minMessage = "Le nom doit avoir au minimum {{ 2 }} charactères",
     *      maxMessage = "e nom doit avoir au maximum {{ 10 }} charactères"
     * )

     * @ORM\Column(name="Nom", type="string", length=255, nullable=false)
     */
    private $nom;

    /**
     * @var \DateTime
     * @Assert\NotBlank
     * @ORM\Column(name="Date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var string
     * @Assert\NotBlank
     * @ORM\Column(name="Description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string
     * @Assert\NotBlank
     * @ORM\Column(name="type", type="string", length=255, nullable=false)
     */
    private $type;

    /**
     * @ORM\ManyToOne(targetEntity=Category::class, inversedBy="demandes")
     */
    private $category;

    /**
     * @ORM\ManyToOne(targetEntity=Famille::class, inversedBy="demande")
     * @ORM\JoinColumn(name="idF", referencedColumnName="id_f")
     */
    private $famille;



    /**
     * @return int
     */
    public function getDemandeid()
    {
        return $this->demandeid;
    }

    /**
     * @param int $demandeid
     */
    public function setDemandeid($demandeid)
    {
        $this->demandeid = $demandeid;
    }

    /**
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * @param \DateTime $date
     */
    public function setDate($date)
    {
        $this->date = $date;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType($type)
    {
        $this->type = $type;
    }

    public function getCategory(): ?Category
    {
        return $this->category;
    }

    public function setCategory(?Category $category): self
    {
        $this->category = $category;

        return $this;
    }

    public function getFamille(): ?Famille
    {
        return $this->famille;
    }

    public function setFamille(?Famille $famille): self
    {
        $this->famille = $famille;

        return $this;
    }
}
