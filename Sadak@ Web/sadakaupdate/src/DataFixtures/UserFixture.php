<?php

namespace App\DataFixtures;

use App\Entity\User;
use Doctrine\ORM\EntityManagerInterface;

use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
class UserFixture extends BaseFixture
{
    private $passwordEncoder;
    public function __construct(UserPasswordEncoderInterface $passwordEncoder)
    {
        $this->passwordEncoder = $passwordEncoder;
    }


    public function loadData(EntityManagerInterface $manager)
    {
//        $this->createMany(10, 'main_users', function($i) {
//            $user = new User();
//            $user->setEmail(sprintf('sadak@%d@example.com', $i));
//            $user->setFirstName($this->faker->firstName);
//            $user->setLastName($this->faker->lastName);
//            $user->setVille($this->faker->city);
//            $user->setProfession($this->faker->company);
//            $user->setPassword($this->passwordEncoder->encodePassword(
//                $user,
//                'engage'
//            ));
//            return $user;
//        });
        $user1 = new User();
        $user1->setEmail('sadak@admin');
        $user1->setFirstName($this->faker->firstName);
        $user1->setLastName($this->faker->lastName);
        $user1->setVille($this->faker->city);
        $user1->setProfession($this->faker->company);
        $user1->setPassword($this->passwordEncoder->encodePassword(
            $user1,
            '915'
        ));
        $user1->setRoles(["ROLE_ADMIN"]);
        $manager->persist($user1);

        for ($i = 0; $i < 15; $i++) {
            $user = new User();
            $user->setEmail(sprintf('sadak%d@example.com', $i));
            $user->setFirstName($this->faker->firstName);
            $user->setLastName($this->faker->lastName);
            $user->setVille($this->faker->city);
            $user->setProfession($this->faker->company);
            $user->setPassword($this->passwordEncoder->encodePassword(
                $user,
                'engage'
            ));
            $manager->persist($user);
        }
        $manager->flush();

    }
}
