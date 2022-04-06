<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Service
 *
 * @ORM\Table(name="service")
 * @ORM\Entity
 */
class Service
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_svc", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idSvc;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_svc", type="string", length=20, nullable=false)
     */
    private $nomSvc;

    /**
     * @var string
     *
     * @ORM\Column(name="intv_dispo", type="string", length=50, nullable=false)
     */
    private $intvDispo;

    /**
     * @var string
     *
     * @ORM\Column(name="Profession", type="string", length=20, nullable=false)
     */
    private $profession;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=100, nullable=false)
     */
    private $adresse;

    /**
     * @var string
     *
     * @ORM\Column(name="ville", type="string", length=10, nullable=false)
     */
    private $ville;

    /**
     * @var int
     *
     * @ORM\Column(name="num_tel", type="integer", nullable=false)
     */
    private $numTel;

    /**
     * @var string
     *
     * @ORM\Column(name="zone_dispo", type="string", length=30, nullable=false)
     */
    private $zoneDispo;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_volontaire", type="string", length=20, nullable=false)
     */
    private $nomVolontaire;


}
