<?php

namespace App\Entity;

use App\Repository\FamilleRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\ORM\Mapping\OneToMany;
use App\Entity\RemarqueFamille;

/**
 * @ORM\Entity(repositoryClass=FamilleRepository::class)
 */
class Famille
{

  
    /**
     * @var int
     *
     * @ORM\Column(name="id_f", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idF;

    /**
     * @var string
     *
     * @ORM\Column(name="pays", type="string", length=255, nullable=false)
     */
    private $pays;

    /**
     * @var string
     *
     * @ORM\Column(name="etat_f", type="string", length=255, nullable=false)
     */
    private $etatF;

    /**
     * @var string
     *
     * @ORM\Column(name="ville_f", type="string", length=50, nullable=false)
     */
    private $villeF;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_p", type="string", length=50, nullable=false)
     */
    private $nomP;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom_p", type="string", length=50, nullable=false)
     */
    private $prenomP;

    /**
     * @var int
     *
     * @ORM\Column(name="tele_p", type="integer", nullable=false)
     */
    private $teleP;

    /**
     *
     * @ORM\Column(name="d_naissance_p", type="datetime", nullable=false)
     */
    private $dNaissanceP;

    /**
     * @var string
     *
     * @ORM\Column(name="s_revenue_p", type="string", length=50, nullable=false)
     */
    private $sRevenueP;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_m", type="string", length=50, nullable=false)
     */
    private $nomM;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom_m", type="string", length=50, nullable=false)
     */
    private $prenomM;

    /**
     * @var int
     *
     * @ORM\Column(name="tele_m", type="integer", nullable=false)
     */
    private $teleM;

    /**
     *
     * @ORM\Column(name="d_naissance_m", type="datetime", nullable=false)
     */
    private $dNaissanceM;

    /**
     * @ORM\Column(name="dateV", type="datetime", nullable=false)
     */
    private $dateV;

    /**
     * @ORM\Column(name="remarque", type="float", nullable=false)
     */
    private $remarque;

    /**
     * @var string
     *
     * @ORM\Column(name="s_revenue_m", type="string", length=50, nullable=false)
     */
    private $sRevenueM;

    /**
     * @var float
     *
     * @ORM\Column(name="r_brute_f", type="float", precision=10, scale=0, nullable=false)
     */
    private $rBruteF;

    /**
     * @var int
     *
     * @ORM\Column(name="nbr_enf", type="integer", nullable=false)
     */
    private $nbrEnf;

    /**
     * @var int
     *
     * @ORM\Column(name="nbr_enf_malade", type="integer", nullable=false)
     */
    private $nbrEnfMalade;

    /**
     * @var string
     *
     * @ORM\Column(name="besoin_f", type="string", length=255, nullable=false)
     */
    private $besoinF;

    /**
     * @var string
     *
     * @ORM\Column(name="verif_f", type="string", length=255, nullable=false)
     */
    private $verifF;

    /**
     * @ORM\OneToMany(targetEntity=RemarqueFamille::class, mappedBy="famille")
     */
    private $remarqueFs;

    /**
     * @ORM\OneToMany(targetEntity=Demande::class, mappedBy="famille")
     */
    private $demande;

    public function __construct()
    {
        $this->remarqueFs = new ArrayCollection();
        $this->demande = new ArrayCollection();
    }

    /**
     * @return int
     */
    public function getIdF(): ?int
    {
        return $this->idF;
    }

    /**
     * @param int $idF
     */
    public function setIdF(int $idF): void
    {
        $this->idF = $idF;
    }

    /**
     * @return string
     */
    public function getPays(): ?string
    {
        return $this->pays;
    }

    /**
     * @param string $pays
     */
    public function setPays(string $pays): void
    {
        $this->pays = $pays;
    }

    /**
     * @return string
     */
    public function getEtatF(): ?string
    {
        return $this->etatF;
    }

    /**
     * @param string $etatF
     */
    public function setEtatF(string $etatF): void
    {
        $this->etatF = $etatF;
    }

    /**
     * @return string
     */
    public function getVilleF(): ?string
    {
        return $this->villeF;
    }

    /**
     * @param string $villeF
     */
    public function setVilleF(string $villeF): void
    {
        $this->villeF = $villeF;
    }

    /**
     * @return string
     */
    public function getNomP(): ?string
    {
        return $this->nomP;
    }

    /**
     * @param string $nomP
     */
    public function setNomP(string $nomP): void
    {
        $this->nomP = $nomP;
    }

    /**
     * @return string
     */
    public function getPrenomP(): ?string
    {
        return $this->prenomP;
    }

    /**
     * @param string $prenomP
     */
    public function setPrenomP(string $prenomP): void
    {
        $this->prenomP = $prenomP;
    }

    /**
     * @return int
     */
    public function getTeleP(): ?int
    {
        return $this->teleP;
    }

    /**
     * @param int $teleP
     */
    public function setTeleP(int $teleP): void
    {
        $this->teleP = $teleP;
    }

    /**
     * @return mixed
     */
    public function getDNaissanceP(): ?\DateTimeInterface
    {
        return $this->dNaissanceP;
    }

    /**
     * @param mixed $dNaissanceP
     */
    public function setDNaissanceP(\DateTimeInterface $dNaissanceP): self
    {
        $this->dNaissanceP = $dNaissanceP;
        return $this;
    }

    /**
     * @return string
     */
    public function getSRevenueP(): ?string
    {
        return $this->sRevenueP;
    }

    /**
     * @param string $sRevenueP
     */
    public function setSRevenueP(string $sRevenueP): void
    {
        $this->sRevenueP = $sRevenueP;
    }

    /**
     * @return string
     */
    public function getNomM(): ?string
    {
        return $this->nomM;
    }

    /**
     * @param string $nomM
     */
    public function setNomM(string $nomM): void
    {
        $this->nomM = $nomM;
    }

    /**
     * @return string
     */
    public function getPrenomM(): ?string
    {
        return $this->prenomM;
    }

    /**
     * @param string $prenomM
     */
    public function setPrenomM(string $prenomM): void
    {
        $this->prenomM = $prenomM;
    }

    /**
     * @return int
     */
    public function getTeleM(): ?int
    {
        return $this->teleM;
    }

    /**
     * @param int $teleM
     */
    public function setTeleM(int $teleM): void
    {
        $this->teleM = $teleM;
    }

    /**
     * @return mixed
     */
    public function getDNaissanceM(): ?\DateTimeInterface
    {
        return $this->dNaissanceM;
    }

    /**
     * @param mixed $dNaissanceM
     */
    public function setDNaissanceM(\DateTimeInterface $dNaissanceM): self
    {
        $this->dNaissanceM = $dNaissanceM;
        return $this;
    }

    /**
     * @return string
     */
    public function getSRevenueM(): ?string
    {
        return $this->sRevenueM;
    }

    /**
     * @param string $sRevenueM
     */
    public function setSRevenueM(string $sRevenueM): void
    {
        $this->sRevenueM = $sRevenueM;
    }

    /**
     * @return float
     */
    public function getRBruteF(): ?float
    {
        return $this->rBruteF;
    }

    /**
     * @param float $rBruteF
     */
    public function setRBruteF(float $rBruteF): void
    {
        $this->rBruteF = $rBruteF;
    }

    /**
     * @return int
     */
    public function getNbrEnf(): ?int
    {
        return $this->nbrEnf;
    }

    /**
     * @param int $nbrEnf
     */
    public function setNbrEnf(int $nbrEnf): void
    {
        $this->nbrEnf = $nbrEnf;
    }

    /**
     * @return int
     */
    public function getNbrEnfMalade(): ?int
    {
        return $this->nbrEnfMalade;
    }

    /**
     * @param int $nbrEnfMalade
     */
    public function setNbrEnfMalade(int $nbrEnfMalade): void
    {
        $this->nbrEnfMalade = $nbrEnfMalade;
    }

    /**
     * @return string
     */
    public function getBesoinF(): ?string
    {
        return $this->besoinF;
    }

    /**
     * @param string $besoinF
     */
    public function setBesoinF(string $besoinF): void
    {
        $this->besoinF = $besoinF;
    }

    /**
     * @return string
     */
    public function getVerifF(): ?string
    {
        return $this->verifF;
    }

    /**
     * @param string $verifF
     */
    public function setVerifF(string $verifF): void
    {
        $this->verifF = $verifF;
    }



    /**
     * @return mixed
     */
    public function getDateV(): ?\DateTimeInterface
    {
        return $this->dateV;
    }

    /**
     * @param mixed $dateV
     */
    public function setDateV(\DateTimeInterface $dateV): self
    {
        $this->dateV = $dateV;
        return $this;
    }

    /**
     * @return mixed
     */
    public function getRemarque()
    {
        return $this->remarque;
    }

    /**
     * @param mixed $remarque
     */
    public function setRemarque($remarque): void
    {
        $this->remarque = $remarque;
    }

    /**
     * @return Collection|RemarqueFamille[]
     */
    public function getRemarqueFs(): Collection
    {
        return $this->remarqueFs;
    }

    public function addRemarqueF(RemarqueFamille $remarqueF): self
    {
        if (!$this->remarqueFs->contains($remarqueF)) {
            $this->remarqueFs[] = $remarqueF;
            $remarqueF->setFamille($this);
        }

        return $this;
    }

    public function removeRemarqueF(RemarqueFamille $remarqueF): self
    {
        if ($this->remarqueFs->removeElement($remarqueF)) {
            // set the owning side to null (unless already changed)
            if ($remarqueF->getFamille() === $this) {
                $remarqueF->setFamille(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Demande[]
     */
    public function getDemande(): Collection
    {
        return $this->demande;
    }

    public function addDemande(Demande $demande): self
    {
        if (!$this->demande->contains($demande)) {
            $this->demande[] = $demande;
            $demande->setFamille($this);
        }

        return $this;
    }

    public function removeDemande(Demande $demande): self
    {
        if ($this->demande->removeElement($demande)) {
            // set the owning side to null (unless already changed)
            if ($demande->getFamille() === $this) {
                $demande->setFamille(null);
            }
        }

        return $this;
    }


}
