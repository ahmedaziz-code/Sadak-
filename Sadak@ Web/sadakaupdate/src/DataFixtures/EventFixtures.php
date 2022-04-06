<?php

namespace App\DataFixtures;

use App\Entity\Comment;
use App\Entity\Event;
use App\Entity\Tag;
use App\Entity\User;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Common\DataFixtures\DependentFixtureInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class EventFixtures extends BaseFixture implements  DependentFixtureInterface

{
    private $passwordEncoder;
    public function __construct(UserPasswordEncoderInterface $passwordEncoder)
    {
        $this->passwordEncoder = $passwordEncoder;
    }

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


    public function loadData(EntityManagerInterface $manager)
    {
//        $this->createMany(10, 'main_events',function ($count) use($manager) {
//            $event=new Event();
//            $event->setEventName($this->faker->randomElement(self::$articleTitles))
//                ->setEventContent(<<<EOF
//Spicy **jalapeno bacon** ipsum dolor amet veniam shank in dolore. Ham hock nisi landjaeger cow,
//lorem proident [beef ribs](https://baconipsum.com/) aute enim veniam ut cillum pork chuck picanha. Dolore reprehenderit
//labore minim pork belly spare ribs cupim short loin in. Elit exercitation eiusmod dolore cow
//**turkey** shank eu pork belly meatball non cupim.
//Laboris beef ribs fatback fugiat eiusmod jowl kielbasa alcatra dolore velit ea ball tip. Pariatur
//laboris sunt venison, et laborum dolore minim non meatball. Shankle eu flank aliqua shoulder,
//capicola biltong frankfurter boudin cupim officia. Exercitation fugiat consectetur ham. Adipisicing
//picanha shank et filet mignon pork belly ut ullamco. Irure velit turducken ground round doner incididunt
//occaecat lorem meatball prosciutto quis strip steak.
//Meatball adipisicing ribeye bacon strip steak eu. Consectetur ham hock pork hamburger enim strip steak
//mollit quis officia meatloaf tri-tip swine. Cow ut reprehenderit, buffalo incididunt in filet mignon
//strip steak pork belly aliquip capicola officia. Labore deserunt esse chicken lorem shoulder tail consectetur
//cow est ribeye adipisicing. Pig hamburger pork belly enim. Do porchetta minim capicola irure pancetta chuck
//fugiat.
//EOF
//                );
//
//
//            // publish most articles
//        if ($this->faker->boolean(100) ){
//            $event->setPublishedAt($this->faker->dateTimeBetween('-100 days', '-1 days'));
//        }
//            $event->setEventOrganiser($this->faker->randomElement(self::$articleImages))
//                ->setHeartCount($this->faker->numberBetween(5,100))
//                ->setEventParticipants($this->faker->numberBetween(5,20))
//                ->setEventImage($this->faker->randomElement(self::$articleImages))
//                ->setEventDate($this->faker->dateTimeBetween('-100 days', '-1 days'))
//                ->setEventDateF($this->faker->dateTimeBetween('-100 days', '-1 days'));
//
//
//            $tags = $this->getRandomReferences('main_tags', $this->faker->numberBetween(0, 5));
//            foreach ($tags as $tag) {
//                $event->addTag($tag);
//            }
//
//            return $event;
//        });
        $user = new User();
        $user->setEmail('alaakanzari@gmail.com');
        $user->setFirstName($this->faker->firstName);
        $user->setLastName($this->faker->lastName);
        $user->setVille($this->faker->city);
        $user->setProfession($this->faker->company);
        $user->setPassword($this->passwordEncoder->encodePassword(
            $user,
            '915'
        ));
        $manager->persist($user);
        for ($i = 0; $i < 15; $i++) {
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
            $tags=[];

                $tag = new Tag();
                $tag->setName($this->faker->realText(20));
                $manager->persist($tag);



                $event->addTag($tag);

            $manager->persist($event);

        }

        $manager->flush();
    }

    public function getDependencies()
    {
        return [
            UserFixture::class,
            TagFixtures::class,
        ];
    }

}
