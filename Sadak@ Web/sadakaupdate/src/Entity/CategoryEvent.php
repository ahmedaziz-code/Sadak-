<?php

namespace App\Entity;

use App\Repository\CategoryEventRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CategoryEventRepository::class)
 */
class CategoryEvent
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $type;

    /**
     * @ORM\OneToMany(targetEntity=Event::class, mappedBy="categoryEvent")
     */
    private $eventt;



    public function __construct()
    {
        $this->event = new ArrayCollection();
        $this->eventt = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    /**
     * @return Collection|Event[]
     */
    public function getEventt(): Collection
    {
        return $this->eventt;
    }

    public function addEventt(Event $eventt): self
    {
        if (!$this->eventt->contains($eventt)) {
            $this->eventt[] = $eventt;
            $eventt->setCategoryEvent($this);
        }

        return $this;
    }

    public function removeEventt(Event $eventt): self
    {
        if ($this->eventt->removeElement($eventt)) {
            // set the owning side to null (unless already changed)
            if ($eventt->getCategoryEvent() === $this) {
                $eventt->setCategoryEvent(null);
            }
        }

        return $this;
    }
    public function __toString()
    {
        return $this->getType();
    }


}
