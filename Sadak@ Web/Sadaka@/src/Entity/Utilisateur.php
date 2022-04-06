<?php

namespace App\Entity;

use App\Repository\UtilisateurRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=UtilisateurRepository::class)
 */
class Utilisateur
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_u", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idU;

    /**
     * @var string
     *
     * @ORM\Column(name="username", type="string", length=255, nullable=false)
     */
    private $username;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_u", type="string", length=50, nullable=false)
     */
    private $nomU;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom_u", type="string", length=50, nullable=false)
     */
    private $prenomU;

    /**
     * @var string
     *
     * @ORM\Column(name="email_u", type="string", length=255, nullable=false)
     */
    private $emailU;

    /**
     * @var string
     *
     * @ORM\Column(name="mdp_u", type="string", length=50, nullable=false)
     */
    private $mdpU;

    /**
     * @var string
     *
     * @ORM\Column(name="pays_u", type="string", length=255, nullable=false)
     */
    private $paysU;

    /**
     * @var string
     *
     * @ORM\Column(name="gouvernorat", type="string", length=255, nullable=false)
     */
    private $gouvernorat;

    /**
     * @var string
     *
     * @ORM\Column(name="ville", type="string", length=255, nullable=false)
     */
    private $ville;

    /**
     * @var string
     *
     * @ORM\Column(name="profession_u", type="string", length=255, nullable=false)
     */
    private $professionU;

    /**
     * @var string
     *
     * @ORM\Column(name="type_u", type="string", length=50, nullable=false)
     */
    private $typeU;

    /**
     * @return int
     */
    public function getIdU(): int
    {
        return $this->idU;
    }

    /**
     * @param int $idU
     */
    public function setIdU(int $idU): void
    {
        $this->idU = $idU;
    }

    /**
     * @return string
     */
    public function getUsername(): string
    {
        return $this->username;
    }

    /**
     * @param string $username
     */
    public function setUsername(string $username): void
    {
        $this->username = $username;
    }

    /**
     * @return string
     */
    public function getNomU(): string
    {
        return $this->nomU;
    }

    /**
     * @param string $nomU
     */
    public function setNomU(string $nomU): void
    {
        $this->nomU = $nomU;
    }

    /**
     * @return string
     */
    public function getPrenomU(): string
    {
        return $this->prenomU;
    }

    /**
     * @param string $prenomU
     */
    public function setPrenomU(string $prenomU): void
    {
        $this->prenomU = $prenomU;
    }

    /**
     * @return string
     */
    public function getEmailU(): string
    {
        return $this->emailU;
    }

    /**
     * @param string $emailU
     */
    public function setEmailU(string $emailU): void
    {
        $this->emailU = $emailU;
    }

    /**
     * @return string
     */
    public function getMdpU(): string
    {
        return $this->mdpU;
    }

    /**
     * @param string $mdpU
     */
    public function setMdpU(string $mdpU): void
    {
        $this->mdpU = $mdpU;
    }

    /**
     * @return string
     */
    public function getPaysU(): string
    {
        return $this->paysU;
    }

    /**
     * @param string $paysU
     */
    public function setPaysU(string $paysU): void
    {
        $this->paysU = $paysU;
    }

    /**
     * @return string
     */
    public function getGouvernorat(): string
    {
        return $this->gouvernorat;
    }

    /**
     * @param string $gouvernorat
     */
    public function setGouvernorat(string $gouvernorat): void
    {
        $this->gouvernorat = $gouvernorat;
    }

    /**
     * @return string
     */
    public function getVille(): string
    {
        return $this->ville;
    }

    /**
     * @param string $ville
     */
    public function setVille(string $ville): void
    {
        $this->ville = $ville;
    }

    /**
     * @return string
     */
    public function getProfessionU(): string
    {
        return $this->professionU;
    }

    /**
     * @param string $professionU
     */
    public function setProfessionU(string $professionU): void
    {
        $this->professionU = $professionU;
    }

    /**
     * @return string
     */
    public function getTypeU(): string
    {
        return $this->typeU;
    }

    /**
     * @param string $typeU
     */
    public function setTypeU(string $typeU): void
    {
        $this->typeU = $typeU;
    }


}
