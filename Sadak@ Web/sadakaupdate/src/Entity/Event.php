<?php

namespace App\Entity;

use App\Repository\EventRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\Common\Collections\Criteria;
use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;
use Gedmo\Timestampable\Traits\TimestampableEntity;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Context\ExecutionContextInterface;


/**
 * @ORM\Entity(repositoryClass=EventRepository::class)
 */
class Event
{
    use TimestampableEntity;
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank (message="")
     */
    private $eventName;

    /**
     * @ORM\Column(type="datetime")
     */
    private $eventDate;

    /**
     * @ORM\Column(type="datetime")
     * @Assert\NotBlank()
     */
    private $eventDateF;

    /**
     * @ORM\Column(type="text")
     * @Assert\NotBlank
     */
    private $eventContent;

    /**
     * @ORM\Column(type="string", length=255)

     *
     */
    private $eventImage;

    /**
     * @ORM\Column(type="integer")
     * @Assert\GreaterThan(10)
     * @Assert\PositiveOrZero
     */
    private $eventParticipants;

    /**
     * @ORM\Column(type="datetime")
     */
    private $publishedAt;

    /**
     * @Gedmo\Slug(fields={"eventName","eventName","eventName"})
     * @ORM\Column(type="string", length=100,unique=true)

     */
    private $slug;



    /**
     * @ORM\Column(type="integer")
     */
    private $heartCount=0;

    /**
     * @ORM\OneToMany(targetEntity=Comment::class, mappedBy="event",fetch="EXTRA_LAZY")
     * @ORM\OrderBy({"createdAt" = "DESC"})
     */
    private $comments;

    /**
     * @ORM\ManyToOne(targetEntity=CategoryEvent::class, inversedBy="eventt")
     */
    private $categoryEvent;

    /**
     * @ORM\ManyToMany(targetEntity=Tag::class, inversedBy="events")
     */
    private $tags;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="events")
     * @ORM\JoinColumn(nullable=false)
     * @Assert\NotBlank()
     */
    private $eventOrganiser;





    public function __construct()
    {
        $this->comments = new ArrayCollection();
        $this->tags = new ArrayCollection();
    }



    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEventName(): ?string
    {
        return $this->eventName;
    }

    public function setEventName(string $eventName): self
    {
        $this->eventName = $eventName;

        return $this;
    }

    public function getEventDate(): ?\DateTimeInterface
    {
        return $this->eventDate;
    }

    public function setEventDate(\DateTimeInterface $eventDate): self
    {
        $this->eventDate = $eventDate;

        return $this;
    }

    public function getEventDateF(): ?\DateTimeInterface
    {
        return $this->eventDateF;
    }

    public function setEventDateF(\DateTimeInterface $eventDateF): self
    {
        $this->eventDateF = $eventDateF;

        return $this;
    }

    public function getEventContent(): ?string
    {
        return $this->eventContent;
    }

    public function setEventContent(string $eventContent): self
    {
        $this->eventContent = $eventContent;

        return $this;
    }

    public function getEventImage(): ?string
    {
        return $this->eventImage;
    }

    public function setEventImage(string $eventImage): self
    {
        $this->eventImage = $eventImage;

        return $this;
    }

    public function getEventParticipants(): ?int
    {
        return $this->eventParticipants;
    }

    public function setEventParticipants(int $eventParticipants): self
    {
        $this->eventParticipants = $eventParticipants;

        return $this;
    }

    public function getPublishedAt(): ?\DateTimeInterface
    {
        return $this->publishedAt;
    }

    public function setPublishedAt(\DateTimeInterface $publishedAt): self
    {
        $this->publishedAt = $publishedAt;

        return $this;
    }
    public function isPublished(): bool
    {
//        $date  = new \DateTime();
//        if ($date->diff($this->publishedAt)){
//            return true;
//        } else
//            return  false;
        return $this->publishedAt !== null;

    }

    public function getSlug(): ?string
    {
        return $this->slug;
    }

    public function setSlug(string $slug): self
    {
        $this->slug = $slug;

        return $this;
    }



    public function getHeartCount(): ?int
    {
        return $this->heartCount;
    }

    public function setHeartCount(int $heartCount): self
    {
        $this->heartCount = $heartCount;

        return $this;
    }
    public function incrementHeartCount(): self
    {
        $this->heartCount = $this->heartCount + 1;

        return $this;
    }
    public function getImagePath()
    {
        return 'images/'.$this->getEventImage();
    }

    /**
     * @return Collection|Comment[]
     */
    public function getComments(): Collection
    {
        return $this->comments;
    }

    /**
     * @return Collection|Comment[]
     */
    public function getNonDeletedComments(): Collection
    {
        $criteria = EventRepository::createNonDeletedCriteria();
        return $this->comments->matching($criteria);
    }

    public function addComment(Comment $comment): self
    {
        if (!$this->comments->contains($comment)) {
            $this->comments[] = $comment;
            $comment->setEvent($this);
        }

        return $this;
    }

    public function removeComment(Comment $comment): self
    {
        if ($this->comments->removeElement($comment)) {
            // set the owning side to null (unless already changed)
            if ($comment->getEvent() === $this) {
                $comment->setEvent(null);
            }
        }

        return $this;
    }

    public function getCategoryEvent(): ?CategoryEvent
    {
        return $this->categoryEvent;
    }

    public function setCategoryEvent(?CategoryEvent $categoryEvent): self
    {
        $this->categoryEvent = $categoryEvent;

        return $this;
    }

    /**
     * @return Collection|Tag[]
     */
    public function getTags(): Collection
    {
        return $this->tags;
    }

    public function addTag(Tag $tag): self
    {
        if (!$this->tags->contains($tag)) {
            $this->tags[] = $tag;
        }

        return $this;
    }

    public function removeTag(Tag $tag): self
    {
        $this->tags->removeElement($tag);

        return $this;
    }

    public function getEventOrganiser(): ?User
    {
        return $this->eventOrganiser;
    }

    public function setEventOrganiser(?User $eventOrganiser): self
    {
        $this->eventOrganiser = $eventOrganiser;

        return $this;
    }



    public function validate(ExecutionContextInterface $context, $payload)
    {
        if (stripos($this->getEventName(), 'Test') !== false) {
            $context->buildViolation('Um.. the Test kinda makes us nervous')
                ->atPath('eventName')
                ->addViolation();
        }
    }
}
