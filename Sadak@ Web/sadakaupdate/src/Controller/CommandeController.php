<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Entity\PanierTemp;
use App\Form\CommandeType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/commande")
 */
class CommandeController extends AbstractController
{
    /**
     * @Route("/", name="commande_index", methods={"GET"})
     */
    public function index(): Response
    {
        $commandes = $this->getDoctrine()
            ->getRepository(Commande::class)
            ->findAll();

        return $this->render('commande/index.html.twig', [
            'commandes' => $commandes,
        ]);
    }

    /**
     * @Route("/new", name="commande_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {

        $commande = new Commande();

        $form = $this->createForm(CommandeType::class, $commande);
        $form->handleRequest($request);

        $panierTemps = $this->getDoctrine()
            ->getRepository(PanierTemp::class)
            ->findBy(['user'=>1]);
        $prix = 0;
        foreach ($panierTemps as $p){
            $prix = $prix + ($p->getQuantiteProduit()*$p->getRefProduit()->getPrixProduit());
        }
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            if(!$panierTemps){
                $this->addFlash('red', 'Panier Vide !');
                return $this->redirectToRoute('produit_index');
            }else{
                $commande->setDate(new \DateTime());
                $commande->setPrixtot($prix);
                $query = $entityManager->createQuery("SELECT u FROM App\Entity\User u WHERE u.id = 1");
                $user = $query->getOneOrNullResult();
                $commande->setUser($user);
                $entityManager->persist($commande);
                $entityManager->flush();

                return $this->redirectToRoute('livraison_new');
            }}

        return $this->render('commande/new.html.twig', [
            'commande' => $commande,
            'panier_temps' => $panierTemps,
            'prix' => $prix,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{commandeId}", name="commande_show", methods={"GET"})
     */
    public function show(Commande $commande): Response
    {
        return $this->render('commande/show.html.twig', [
            'commande' => $commande,
        ]);
    }

    /**
     * @Route("/{commandeId}/edit", name="commande_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Commande $commande): Response
    {
        $form = $this->createForm(CommandeType::class, $commande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('commande_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('commande/edit.html.twig', [
            'commande' => $commande,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{commandeId}", name="commande_delete", methods={"POST"})
     */
    public function delete(Request $request, Commande $commande): Response
    {
        if ($this->isCsrfTokenValid('delete'.$commande->getCommandeId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($commande);
            $entityManager->flush();
        }

        return $this->redirectToRoute('commande_index', [], Response::HTTP_SEE_OTHER);
    }
}
