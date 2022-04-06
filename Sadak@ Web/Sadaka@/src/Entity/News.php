<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * News
 *
 * @ORM\Table(name="news")
 * @ORM\Entity
 */
class News
{
    /**
     * @var int
     *
     * @ORM\Column(name="event_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $eventId;

    /**
     * @var string
     *
     * @ORM\Column(name="event_name", type="string", length=20, nullable=false)
     */
    private $eventName;

    /**
     * @var string
     *
     * @ORM\Column(name="event_date", type="string", length=20, nullable=false)
     */
    private $eventDate;

    /**
     * @var string
     *
     * @ORM\Column(name="event_dateF", type="string", length=20, nullable=false)
     */
    private $eventDatef;

    /**
     * @var string
     *
     * @ORM\Column(name="event_content", type="string", length=500, nullable=false)
     */
    private $eventContent;

    /**
     * @var string
     *
     * @ORM\Column(name="event_organiser", type="string", length=20, nullable=false, options={"default"="'Mohammed'"})
     */
    private $eventOrganiser = '\'Mohammed\'';

    /**
     * @var string|null
     *
     * @ORM\Column(name="event_image", type="string", length=300, nullable=true, options={"default"="NULL"})
     */
    private $eventImage = 'NULL';

    /**
     * @var bool
     *
     * @ORM\Column(name="event_participation", type="boolean", nullable=false)
     */
    private $eventParticipation;


}
