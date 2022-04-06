<?php

namespace App\Controller;

use App\Entity\Category;
use App\Entity\Demande;
use App\Form\Demande2Type;
use App\Repository\DemandeRepository;
use phpDocumentor\Reflection\Types\This;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class DemandeController2Controller extends AbstractController
{
    /**
     * @Route("/demande/controller2", name="demande_controller2")
     */
    public function index(): Response
    {
        return $this->render('demande_controller2/index.html.twig', [
            'controller_name' => 'DemandeController2Controller',
        ]);
    }

    /**
     * @return Response
     * @Route ("/Affiche",name="Affiche")
     */
    public function Affiche(){
        $repo=$this->getDoctrine()->getRepository(Demande::class);
        $Demande=$repo->findAll();
        return $this->render("/demande_controller2/Affiche.html.twig",
            ['Demande'=>$Demande]);
    }

    /**
     * @Route ("/supp/{id}",name="d")
     */
    public function Delete($id){
        $repo=$this->getDoctrine()->getRepository(Demande::class);
        $Demande=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($Demande);
        $em->flush();
        return $this->redirectToRoute('Affiche');
    }

    /**
     * @param Request $request
     * @return Response
     * @Route ("/ajout")
     */
    function add(Request $request){
        $Demande=new Demande();
    $form=$this->createForm(Demande2Type::class,$Demande);
    $form->add('Ajouter',SubmitType::class);
    $form->handleRequest($request);
    if($form->isSubmitted() && $form->isValid()){
        $em=$this->getDoctrine()->getManager();
        $em->persist($Demande);
        $em->flush();
        return $this->redirectToRoute('Affiche');
    }
    return $this->render('demande_controller2/ajout.html.twig',
        ['form'=>$form->createView()]);
    }
    /**
     * @Route ("/modifier/{id}",name="update")
     */
    function Update(DemandeRepository $repository,$id,Request $request){
        $Demande =$repository->find($id);
        $form=$this->createForm(Demande2Type::class,$Demande);
        $form->add('Modifier',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("Affiche");
        }
        return $this->render('demande_controller2/modifier.html.twig',
        [
            'form'=>$form->createView()
        ]);
    }

    /**
     * @return Response
     * @Route ("/importance",name="i")
     */
    public function importance(){
        $repo=$this->getDoctrine()->getRepository(Demande::class);

        $Demande=$repo->findAll();


        return $this->render("/demande_controller2/importance.html.twig",
            ['Demande'=>$Demande]);
    }
//    /**
//     * @return Request $request
//     * @Route("statistiques")
//     */
//    function statistiques(Demande $demande){
//
//
//        $id=$demande->getCategory()->getId();
//        return $this->redirectToRoute("/demande_controller2/importance.html.twig",
//            ['Demande'=>$demande , 'id'=>$id]);
//
//    }

    /**
     * @return Response
     * @Route("/diagramme",name="s")
     */
    function diagramme(){
        $repo=$this->getDoctrine()->getRepository(Demande::class);

        $Demande=$repo->findAll();
        return $this->render("/demande_controller2/diagramme.html.twig",
            ['Demande'=>$Demande]);
    }
}
