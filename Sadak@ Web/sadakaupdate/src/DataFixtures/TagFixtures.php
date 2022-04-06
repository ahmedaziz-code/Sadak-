<?php

namespace App\DataFixtures;

use App\Entity\Tag;
use Doctrine\ORM\EntityManagerInterface;

class TagFixtures extends BaseFixture
{
    protected function loadData(EntityManagerInterface $manager)
    {
//        $this->createMany(10, 'main_tags', function() {
//            $tag = new Tag();
//            $tag->setName($this->faker->realText(20));
//
//            return $tag;
//        });
        for ($i = 0; $i < 15; $i++) {
            $tag = new Tag();
            $tag->setName($this->faker->realText(20));
            $manager->persist($tag);
        }
        $manager->flush();
    }
}
