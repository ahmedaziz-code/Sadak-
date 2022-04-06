<?php

namespace App\DataFixtures;

use App\Entity\Comment;
use App\Entity\Event;
use App\Entity\User;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Common\DataFixtures\DependentFixtureInterface;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Persistence\ObjectManager;
use Faker\Factory;
use Faker\Generator;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class CommentFixture extends BaseFixture implements DependentFixtureInterface
{
    private static $articleTitles = [
        'Why Asteroids Taste Like Bacon',
        'Life on Planet Mercury: Tan, Relaxing and Fabulous',
        'Light Speed Travel: Fountain of Youth or Fallacy',
    ];

    private static $articleImages = [
        'asteroid.jpeg',
        'mercury.jpeg',
        'lightspeed.png',
    ];
    private $passwordEncoder;
    public function __construct(UserPasswordEncoderInterface $passwordEncoder)
    {
        $this->passwordEncoder = $passwordEncoder;
    }

    protected function loadData(EntityManagerInterface $manager)
    {


//        $this->createMany(100, 'main_comments',function () {
//            $comment=new Comment();
//            $comment->setContent(
//                $this->faker->boolean ? $this->faker->paragraph : $this->faker->sentences(2,true)
//            );
//            $comment->setOrganiserName(
//                $this->faker->name
//            );
//
//            $comment->setCreatedAt(
//                $this->faker->dateTimeBetween('-1 months','-1 seconds')
//            );
//
//            $comment->setIsDeleted(
//                $this->faker->boolean(20)
//            );
//
//            $comment->setEvent($this->getRandomReference('main_events'));
//        });


        $event=new Event();
        $event->setEventName($this->faker->randomElement(self::$articleTitles))
            ->setEventContent(<<<EOF
Spicy **jalapeno bacon** ipsum dolor amet veniam shank in dolore. Ham hock nisi landjaeger cow,
lorem proident [beef ribs](https://baconipsum.com/) aute enim veniam ut cillum pork chuck picanha. Dolore reprehenderit
labore minim pork belly spare ribs cupim short loin in. Elit exercitation eiusmod dolore cow
**turkey** shank eu pork belly meatball non cupim.
Laboris beef ribs fatback fugiat eiusmod jowl kielbasa alcatra dolore velit ea ball tip. Pariatur
laboris sunt venison, et laborum dolore minim non meatball. Shankle eu flank aliqua shoulder,
capicola biltong frankfurter boudin cupim officia. Exercitation fugiat consectetur ham. Adipisicing
picanha shank et filet mignon pork belly ut ullamco. Irure velit turducken ground round doner incididunt
occaecat lorem meatball prosciutto quis strip steak.
Meatball adipisicing ribeye bacon strip steak eu. Consectetur ham hock pork hamburger enim strip steak
mollit quis officia meatloaf tri-tip swine. Cow ut reprehenderit, buffalo incididunt in filet mignon
strip steak pork belly aliquip capicola officia. Labore deserunt esse chicken lorem shoulder tail consectetur
cow est ribeye adipisicing. Pig hamburger pork belly enim. Do porchetta minim capicola irure pancetta chuck
fugiat.
EOF
            );
        $user = new User();
        $user->setEmail("test@test.com");
        $user->setFirstName($this->faker->firstName);
        $user->setLastName($this->faker->lastName);
        $user->setVille($this->faker->city);
        $user->setProfession($this->faker->company);
        $user->setPassword($this->passwordEncoder->encodePassword(
            $user,
            'engagee'
        ));
        $manager->persist($user);
        // publish most articles
        if ($this->faker->boolean(100) ){
            $event->setPublishedAt($this->faker->dateTimeBetween('-100 days', '-1 days'));
        }
        $event->setEventOrganiser($user)
            ->setHeartCount($this->faker->numberBetween(5,100))
            ->setEventParticipants($this->faker->numberBetween(5,20))
            ->setEventImage($this->faker->randomElement(self::$articleImages))
            ->setEventDate($this->faker->dateTimeBetween('-100 days', '-1 days'))
            ->setEventDateF($this->faker->dateTimeBetween('-100 days', '-1 days'));
        $manager->persist($event);
        for ($i = 0; $i < 15; $i++) {

            $comment=new Comment();
            $comment->setContent(
                $this->faker->boolean ? $this->faker->paragraph : $this->faker->sentences(2,true)
            );
            $comment->setOrganiserName(
                $this->faker->name
            );

            $comment->setCreatedAt(
                $this->faker->dateTimeBetween('-1 months','-1 seconds')
            );

            $comment->setIsDeleted(
                $this->faker->boolean(20)
            );

            $comment->setEvent($event);
            
            $manager->persist($comment);
        }
        $manager->flush();
    }


    public function getDependencies()
    {
        return [EventFixtures::class];
    }
}
