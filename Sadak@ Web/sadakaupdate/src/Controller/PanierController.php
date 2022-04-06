<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Entity\Livraison;
use App\Entity\Panier;
use App\Entity\PanierTemp;
use App\Entity\Produit;
use App\Form\PanierType;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/panier")
 */
class PanierController extends AbstractController
{
    /**
     * @Route("/", name="panier_index", methods={"GET"})
     */
    public function index(): Response
    {
        $paniers = $this->getDoctrine()
            ->getRepository(Panier::class)
            ->findAll();

        return $this->render('panier/index.html.twig', [
            'paniers' => $paniers,
        ]);
    }

    /**
     * @Route("/new", name="panier_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {

        $panier = new Panier();
        $form = $this->createForm(PanierType::class, $panier);
        $form->handleRequest($request);
        $panierTemps = $this->getDoctrine()
            ->getRepository(PanierTemp::class)
            ->findBy(['user'=>'1']);
        $livraisons = $this->getDoctrine()
            ->getRepository(Livraison::class)
            ->findliv();
        $prods= $this->getDoctrine()
            ->getRepository(Produit::class)
            ->findAll();
        $prix = 0;
        foreach ($panierTemps as $p){
            $prix = $prix + ($p->getQuantiteProduit()*$p->getRefProduit()->getPrixProduit());

        }
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $commande = $this->getDoctrine()->getRepository(Commande::class)
                ->findnvc();


            foreach ($panierTemps as $panierTemp) {
                foreach ($prods as $prod){
                    if($prod->getnomProduit()==$panierTemp->getRefProduit()->getnomProduit())
                    {
                        $prod->setquantiteProduit($prod->getquantiteProduit() - $panierTemp->getquantiteProduit());
                    }}}
            foreach ($panierTemps as $panierTemp){
                $panier = new Panier();
                $panier->setCommande($commande);
                $panier->setRefProduit($panierTemp->getRefproduit());
                $panier->setQuantiteProduit($panierTemp->getQuantiteProduit());

                    $em->persist($panier);
                    $em->flush();

            }
            $panierTemps = $this->getDoctrine()->getRepository(PanierTemp::class)
                ->deletepant();

            return $this->redirectToRoute('panier_pdf');
        }

        return $this->render('panier/new.html.twig', [
            'panier' => $panier,
            'livraisons' => $livraisons,
            'panier_temps' => $panierTemps,
            'prix' => $prix,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/pdf", name="panier_pdf")
     */
    public function facture()
    {
        return $this->render('panier/pdf.html.twig');
    }

    /**
     * @Route("/{id}", name="panier_show", methods={"GET"})
     */
    public function show(Panier $panier): Response
    {
        return $this->render('panier/show.html.twig', [
            'panier' => $panier,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="panier_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Panier $panier): Response
    {
        $form = $this->createForm(PanierType::class, $panier);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('panier_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('panier/edit.html.twig', [
            'panier' => $panier,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="panier_delete", methods={"POST"})
     */
    public function delete(Request $request, Panier $panier): Response
    {
            if ($this->isCsrfTokenValid('delete'.$panier->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($panier);
            $entityManager->flush();
        }

        return $this->redirectToRoute('panier_index', [], Response::HTTP_SEE_OTHER);
    }




    /**
     * @Route("/facture/pdfnav", name="panier_pdfnav", methods={"GET"})
     */
    public function pdfnav()
    {

        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
//        $commandes = $this->getDoctrine()->getRepository(Commande::class)
//            ->findnvc();
//        $commande = $this->getDoctrine()->getRepository(Commande::class)
//            ->findnvc()->getCommandeId();
//        $usernom = $this->getDoctrine()->getRepository(Commande::class)
//            ->findnvc()->getUser()->getNomU();
//        $userprenom = $this->getDoctrine()->getRepository(Commande::class)
//            ->findnvc()->getUser()->getPrenomU();
//        $frais = 7;
//        $prix = $commandes->getPrixtot()+$frais;
//        $text = date('d/m/Y à H:i:s ');
//        $livraison = $this->getDoctrine()->getRepository(Livraison::class)
//            ->findlivr();
//        $adresse = $livraison->getAdresse();
//        $panier = $this->getDoctrine()->getRepository(Panier::class)
//            ->findpan($commande);


        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

//        // Retrieve the HTML generated in our twig file
//        $html = $this->renderView('panier/facture.html.twig', [
//            'title' => "Facture",
//            'prix' => $prix,
//            'usernom' => $usernom,
//            'userprenom' => $userprenom,
//            'date' => $text,
//            'adresse' => $adresse,
//            'panier' => $panier,
//            'frais' => $frais,
//            'commande' => $commande
//        ]);
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('panier/facture.html.twig', [
            'title' => "Facture",
            'prix' => "Facture",
            'usernom' =>"Facture",
            'userprenom' => "Facture",
            'date' => "Facture",
            'adresse' => "Facture",
            'panier' => "Facture",
            'frais' => "Facture",
            'commande' => "Facture"
        ]);


        $dompdf->loadHtml($html);

        $dompdf->setPaper('A4', 'portrait');

        $dompdf->render();

        $dompdf->stream("Facture.pdf", [
            "Attachment" => false
        ]);
    }

    /**
     * @Route("/facture/pdfgen", name="panier_pdfgen", methods={"GET"})
     */
    public function pdfgen()
    {
         // $this->denyAccessUnlessGranted('IS_AUTHENTICATED_FULLY');
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
//        $commandes = $this->getDoctrine()->getRepository(Commande::class)
//            ->findnvc();
//        $commande = $this->getDoctrine()->getRepository(Commande::class)
//            ->findnvc()->getCommandeId();
//        $usernom = $this->getDoctrine()->getRepository(Commande::class)
//            ->findnvc()->getUser()->getNomU();
//        $userprenom = $this->getDoctrine()->getRepository(Commande::class)
//            ->findnvc()->getUser()->getPrenomU();
//        $frais = 7;
//        $prix = $commandes->getPrixtot()+$frais;
//        $text = date('d/m/Y à H:i:s ');
//        $livraison = $this->getDoctrine()->getRepository(Livraison::class)
//            ->findlivr();
//        $adresse = $livraison->getAdresse();
//        $panier = $this->getDoctrine()->getRepository(Panier::class)
//            ->findpan($commande);


        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
//        $html = $this->renderView('panier/facture.html.twig', [
//            'title' => "Facture",
//            'prix' => $prix,
//            'usernom' => $usernom,
//            'userprenom' => $userprenom,
//            'date' => $text,
//            'adresse' => $adresse,
//            'panier' => $panier,
//            'frais' => $frais,
//            'commande' => $commande
//        ]);
        $html = $this->renderView('panier/facture.html.twig', [
            'title' => "Facture",
            'prix' => "Facture",
            'usernom' =>"Facture",
            'userprenom' => "Facture",
            'date' => "Facture",
            'adresse' => "Facture",
            'panier' => "Facture",
            'frais' => "Facture",
            'commande' => "Facture"
        ]);

        $dompdf->loadHtml($html);

        $dompdf->setPaper('A4', 'portrait');

        $dompdf->render();

        $dompdf->stream("Facture.pdf", [
            "Attachment" => true
        ]);
    }
}
