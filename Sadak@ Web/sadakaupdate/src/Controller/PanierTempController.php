<?php

namespace App\Controller;

use App\Entity\PanierTemp;
use App\Form\PanierTempType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/paniertemp")
 */
class PanierTempController extends AbstractController
{
    /**
     * @Route("/", name="panier_temp_index", methods={"GET"})
     */
    public function index(): Response
    {
        $panierTemps = $this->getDoctrine()
            ->getRepository(PanierTemp::class)
            ->findBy(['user'=>1]);
        $prix = 0;
        foreach ($panierTemps as $p){
            $prix = $prix + ($p->getquantiteProduit()*$p->getrefProduit()->getprixProduit());
        }

        return $this->render('panier_temp/index.html.twig', [
            'panier_temps' => $panierTemps,
            'prix' => $prix
        ]);
    }

    /**
     * @Route("/new/{refProduit}", name="panier_temp_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $panierTemp = new PanierTemp();

        $form = $this->createForm(PanierTempType::class, $panierTemp);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();

            $u = $entityManager->createQuery("SELECT o FROM App\Entity\User o WHERE o.id = 1");
            $user = $u->getOneOrNullResult();
            $panierTemp->setUser($user);

            $query = $entityManager->createQuery("SELECT o FROM App\Entity\Produit o WHERE o.refProduit = :refProduit");
            $query->setParameter('refProduit',$request->attributes->get('refProduit'));
            $produit = $query->getOneOrNullResult();
            $panierTemp->setRefProduit($produit);

            $p = $this->getDoctrine()->getRepository(PanierTemp::class)
                    ->findByExampleField($request->attributes->get('refProduit'));
            if($p){
                $this->addFlash('red', 'Element existe! Veuillez modifier la quantité');
                return $this->redirectToRoute('panier_temp_new',['refProduit'=>$request->attributes->get('refProduit')]);

            }elseif($panierTemp->getQuantiteProduit()>$panierTemp->getRefProduit()->getQuantiteProduit()){
                $this->getDoctrine()->getManager()->flush();
                $this->addFlash('red', 'Stock Insuffisant! Veuillez saisir la bonne quantité');

                return $this->redirectToRoute('panier_temp_new',['refProduit'=>$request->attributes->get('refProduit')]);
            }else{
                $this->addFlash('green', 'Element ajouté avec succès!');
                $entityManager->persist($panierTemp);
                $entityManager->flush();
                return $this->redirectToRoute('produit_index');
            }


 }
        return $this->render('panier_temp/new.html.twig', [
            'panier_temp' => $panierTemp,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/{id}", name="panier_temp_show", methods={"GET"})
     */
    public function show(PanierTemp $panierTemp): Response
    {
        return $this->render('panier_temp/show.html.twig', [
            'panier_temp' => $panierTemp,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="panier_temp_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, PanierTemp $panierTemp): Response
    {
        $form = $this->createForm(PanierTempType::class, $panierTemp);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('panier_temp_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('panier_temp/edit.html.twig', [
            'panier_temp' => $panierTemp,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="panier_temp_delete", methods={"POST"})
     */
    public function delete(Request $request, PanierTemp $panierTemp): Response
    {
        if ($this->isCsrfTokenValid('delete'.$panierTemp->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($panierTemp);
            $entityManager->flush();
        }

        return $this->redirectToRoute('panier_temp_index', [], Response::HTTP_SEE_OTHER);
    }
}
