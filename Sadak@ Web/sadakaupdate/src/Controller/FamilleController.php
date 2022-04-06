<?php

namespace App\Controller;


use App\Entity\Demande;
use App\Entity\Famille;
use App\Entity\RemarqueFamille;
use App\Entity\User;
use App\Form\Demande2Type;
use App\Form\FamilleType;
use App\Form\RemarqueFamilleType;
use App\Repository\FamilleRepository;
use App\Repository\RemarqueFamilleRepository;
use App\Repository\UserRepository;
use Knp\Component\Pager\PaginatorInterface;
use Knp\Snappy\Pdf;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Address;
use Symfony\Component\Routing\Annotation\Route;
//use Symfony\Component\Validator\Constraint\email;
use Symfony\Component\Mime\Email;
use Twig\Environment;
use Symfony\Component\Validator\Constraints\DateTime;

class FamilleController extends AbstractController
{

    /**
     * @Route("/famille", name="famille")
     */
    public function index(): Response
    {
        return $this->render('famille/famille.html.twig', [
            'controller_name' => 'FamilleController',
        ]);
    }

    /**
     * @Route("/afficheF", name="afficheF")
     */

    public function afficheF(FamilleRepository $repository, Request $request, PaginatorInterface $paginator)
    {
        $q = $request->query->get('q');
        $queryBuilder = $repository->getWithSearchQueryBuilder($q);

        $pagination = $paginator->paginate(
            $queryBuilder, /* query NOT result */
            $request->query->getInt('page', 1)/*page number*/,
            4/*limit per page*/
        );

        return $this->render('famille/afficheF.html.twig', [
            'familles' => $pagination,
        ]);
    }

    /**
     * @Route("/afficheAF", name="afficheAF")
     */

    public function afficheAdminF(FamilleRepository $repository, Request $request, PaginatorInterface $paginator)
    {
        $q = $request->query->get('q');
        $queryBuilder = $repository->getWithSearchQueryBuilder($q);

        $pagination = $paginator->paginate(
            $queryBuilder, /* query NOT result */
            $request->query->getInt('page', 1)/*page number*/,
            4/*limit per page*/
        );

        return $this->render('backAdmin/back-office/familles.html.twig', [
            'familles' => $pagination,
        ]);
    }

    /**
     * @Route("/addFamille", name="addFamille")
     */
    public function addFamille(Request $request)
    {
        //$repo=$this->getDoctrine()->getRepository(CategorieFamilleRepository::class);
        //$cat=$repo->findall();
        $famille = new Famille();
        $remarque = new RemarqueFamille();
        $remarque->setRemarque(0);
        $remarque->setFamille($famille);
        $object = new \DateTime();
        $remarque->setDateverif($object);
        $famille->setVerifF('non verifiee');
        $date = new \DateTime();
        $famille->setDateV($date);
        $famille->setRemarque(0);
        $form = $this->createForm(FamilleType::class, $famille);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($famille);
            $em->persist($remarque);
            $em->flush();
            return $this->redirectToRoute('afficheF');
        }
        return $this->render('famille/addFamille.html.twig', ["f" => $form->createView()]);
    }

    /**
     * @Route("/Supp/{id}",name="sup")
     */
    function suppFamille($id, FamilleRepository $repo)
    {
        $f = $repo->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($f);
        $em->flush();
        return $this->redirectToRoute('afficheF');
    }

    /**
     * @Route ("modif/{id}", name="mod")
     */
    function modFamille($id, FamilleRepository $repo, Request $request)
    {
        $f = $repo->find($id);
        $f->setVerifF('non verifiee');
        $form = $this->createForm(FamilleType::class, $f);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('afficheF');
        }

        return $this->render('famille/addFamille.html.twig', ["f" => $form->createView()]);

    }

    /**
     * @Route ("verif/{id}", name="verif")
     *
     */
    function verifFamille(MailerInterface $mailer, $id, FamilleRepository $repoF, UserRepository $repoU, Pdf $pdf)
    {
        $f = $repoF->find($id);
        $g = $f->getEtatF();
        $v = $f->getVilleF();

        $u = $repoU->findAllByAdress($g, $v);



        if (strcasecmp($f->getVerifF(), 'non verifiee') == 0 && count($u) != 0) {
            $f->setVerifF('en cours de verification');
            $em = $this->getDoctrine()->getManager();
            $em->flush();

            //Generation du pdf
            $pdf->generateFromHtml(
                $this->renderView(
                    'email/famillePDF.html.twig',
                    array(
                        'some' => $f
                    )
                ),
                $f->getNomP() . ".pdf"
            );
            //envoi du pdf pour chaque utilisateur de type volontaire
            foreach ($u as $v) {

                foreach ($v->getRoles() as $role) {
                    if ($role == "ROLE_VOLUNTEER"){
                        $email = (new TemplatedEmail())
                            ->from(new Address('ahmed@esprit.tn', 'Sadaka@'))
                            ->to(new Address($v->getEmail(), $v->getFirstName()))
                            ->subject('Verification des famille')
                            ->htmlTemplate('email/email.html.twig')
                            ->context([
                                'famille' => $f
                            ])
                            ->attachFromPath($f->getNomP() . '.pdf');


                        $mailer->send($email);
                    }
                }

            }

            return $this->redirectToRoute('afficheF');
        }
        return $this->redirectToRoute('afficheF');

    }

    /**
     * @Route ("/RemarqueFamille", name="RemarqueFamille1")
     */
    public function afficherRemarqueFamille(RemarqueFamilleRepository $repoRF, FamilleRepository $repoF)
    {
        $f = $repoF->ListFamilleByEtat();


        foreach ($f as $fa) {
            $date = [];
            $famille = [];
            $dateMin = new \DateTime();
            $dateMax = new \DateTime();
            $dateV = 0;
            $r = $repoRF->ListRemarqueByFamille($fa->getIdF());
            //$r = new RemarqueFamille();
              $j = 0; $remarque = 0;  ;

            foreach ($r as $re) {
                $remarque += $re->getRemarque();
                $d=$re->getDateverif();
                $date [] = $d;
                $j++;
            }
            if (count($date)==1){
                $dateMin = $date[0];
                $dateMax = $date[0];
            }
            /*else{

                foreach($date as $dd){
                    if($dd > $dateMax){
                        $dateMax = $dd;
                    }
                }
                foreach($date as $dd){
                    if($dd < $dateMax){
                        $dateMin = $dd;
                    }
                }


            }*/
            else{
                $dateMin = $date[0];
                $dateMax = $date[count($date)-1];
            }
            $tab [] = $dateMin;
            $intervaleDate = $dateMax->diff($dateMin);
            $dateV = intval(($intervaleDate->days)/2);
            if(count($r)==0){
                $remarque = $remarque/($j+1);
            }
            else{
                $remarque = $remarque/($j);
            }

            $fa->setRemarque($remarque);
            $duration = $dateV.' day';
            date_add($dateMin,date_interval_create_from_date_string($duration));


            $fa->setDateV($dateMin);
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            $famille [] = $fa;
        }
        $treeF = $repoF->listFamilleByDateAndRemarqueAndNbrEnf();
        $Encours = $repoF->listFamilleByEtat2();

        /*$intervaleDate = $dateMax->diff($dateMin);
        $dateV = intval($intervaleDate->days/2);
        $duration = $dateV.' day';
        date_add($dateMin,date_interval_create_from_date_string($duration));*/

        return $this->render('famille/RemFamille.html.twig' , ['famille'=>$treeF, 'familleEn'=>$Encours]);
    }

    /**
     * @Route ("/AjouterR/{id}", name="AjoutR")
     */
    public function ajouterRemarque($id, FamilleRepository $repoF, Request $request){
        $f=$repoF->find($id);
        $f->setVerifF('verifiee');
        $em = $this->getDoctrine()->getManager();
        $em->flush();
        $r = new RemarqueFamille();
        $form = $this->createForm(RemarqueFamilleType::class, $r);
        $form->handleRequest($request);
        $Object = new \DateTime();
        //$DateAndTime = $Object->format("Y-m-d h:i:s a");
        $r->setDateverif($Object);
        $r->setFamille($f);
        if($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->persist($r);
            $em->flush();
            return $this->redirectToRoute("RemarqueFamille1");
        }
        return $this->render("/remarque_famille/remarque.html.twig", ["r"=>$form->createView()]);
    }

    /**
     * @Route("/AjouterD/{id}", name="AjoutD")
     */
    public function ajouterDemande($id, FamilleRepository $repo, Request  $request){
        $f = $repo->find($id);
        $Demande=new Demande();
        $Demande->setFamille($f);
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
}
