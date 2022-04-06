<?php

namespace App\Form;

use App\Entity\Event;
use App\Entity\User;
use App\Repository\UserRepository;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ButtonType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
class EventFormType extends AbstractType
{

    private $userRepository;

    public function __construct(UserRepository $userRepository)
    {

        $this->userRepository = $userRepository;
    }

    public function buildForm(FormBuilderInterface $builder, array $options) :void
    {
        $builder
            ->add('eventName',TextType::class,
            ['help'=>'Choose Smth catchy',
                'required' => false])
            ->add('eventDate',DateType::class,[
                'widget'=>'single_text',
                'html5'=>true,
            ])
            ->add('eventDateF',DateType::class,[
                'widget'=>'single_text',
                'html5'=>true,
            ])
            ->add('eventContent',TextareaType::class)
//            ->add('eventOrganiser',TextType::class)
            ->add('eventImage',FileType::class,[
                "mapped"=>false,
                "label"=> "Upload a pic"
            ])
            ->add('eventParticipants')
            ->add('publishedAt', null, [
                'widget' => 'single_text'
            ])
            ->add('eventOrganiser',UserSelectTextType::class)
            ->add('categoryEvent')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Event::class
        ]);
    }


}
