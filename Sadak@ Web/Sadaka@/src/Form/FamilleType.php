<?php

namespace App\Form;

use App\Entity\Famille;
use Symfony\Component\Form\Extension\Core\Type\ButtonType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Symfony\Component\Intl\Countries;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\BirthdayType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;


use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class FamilleType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {

        $builder
            ->add('pays', ChoiceType::class, [
                'choices'=>[
                    'Tunisie'=>'Tunisie'
                ],
                'label'=>'Pays'
            ])
            ->add('etatF' , ChoiceType::class, [
                'choices'=>[
                    'Ariana'=>'Ariana','Manouba'=>'Manouba','Tunis'=>'Tunis','Bizerte'=>'Bizerte','Nabeul'=>'Nabeul'
                ],
                'label'=>'Gouvernorat'
            ])
            ->add('villeF', ChoiceType::class,[
                'label'=>'Ville',
                'choices'=>['La Soukra'=>'La Soukra','Ariana Soghra'=>'Ariana Soghra',
                    'Al Ghazela'=>'Al Ghazela', 'Bardo'=>'Bardo']
            ])
            ->add('nomP', TextType::class,[
                'label'=>'Nom du Père',

            ])
            ->add('prenomP', textType::class,[
                'label'=>'Prénom du Père'
            ])
            ->add('teleP', NumberType::class)
            ->add('dNaissanceP', DateType::class, ['widget'=>'single_text', 'html5'=>'true'])
            ->add('sRevenueP', ChoiceType::class, ['choices'=>['Personne Active'=>'Personne Active',
                'Personne Retraité'=>'Personne Retraite', 'Personne Sans Travaille'=>'Personne Sans Travaille']])
            ->add('nomM')
            ->add('prenomM')
            ->add('teleM', NumberType::class)
            ->add('dNaissanceM', DateType::class, ['widget'=>'single_text', 'html5'=>'true'])
            ->add('sRevenueM', ChoiceType::class, ['choices'=>['Personne Active'=>'Personne Active',
                'Personne Retraité'=>'Personne Retraite', 'Personne Sans Travaille'=>'Personne Sans Travaille']])
            ->add('rBruteF')
            ->add('nbrEnf')
            ->add('nbrEnfMalade')
            ->add('besoinF', ChoiceType::class, ['choices'=>['Nourriture'=>'Nourriture', 'Medicale'=>'Medicale']])

            ->add('save', SubmitType::class,[
                'attr'=>['class' => 'buttonV']
            ])


        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Famille::class,
        ]);
    }
}
