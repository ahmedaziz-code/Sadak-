<?php

namespace App\Form;

use App\Entity\RemarqueFamille;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class RemarqueFamilleType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('remarque', ChoiceType::class, ["choices"=>["0"=>0, "1"=>1, "2"=>2, "3"=>3,"4"=>4, "5"=>5]])
            ->add('Ajouter Remarque', SubmitType::class, ['attr'=>['class'=>'buttonV']]);
        /*
            ->add('dateverif')
            ->add('famille')*/
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => RemarqueFamille::class,
        ]);
    }
}
