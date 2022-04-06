<?php


namespace App\Controller\Service\back_office;

use App\Entity\Service;
use App\Form\ServiceType;
use App\Repository\ServiceRepository;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Validator\Validator\ValidatorInterface;

/**
 * Class ServiceController
 * @package App\Controller\Service\back_office
 * @Route("volontaire/service")
 */
class ServiceController extends AbstractController
{

    private $em;
    private $serviceRepository;
    private $paginator;

    /**
     * ServiceController constructor.
     * @param EntityManagerInterface $em
     * @param ServiceRepository $serviceRepository
     */
    public function __construct(EntityManagerInterface $em,
                                PaginatorInterface $paginator,
                                ServiceRepository $serviceRepository)
    {
        $this->em = $em;
        $this->serviceRepository = $serviceRepository;
        $this->paginator = $paginator;
    }

    /**
     * @param Request $request
     * @Route("/add", name="add_service")
     */
    public function add(Request $request)
    {
        $service = new Service();
//        if ($this->getUser()) {
//            $currentUser = $this->getUser();
//        }else{
//            $currentUser = 1 ;
//        }

        $form = $this->createForm(ServiceType::class, $service);
        $form->handleRequest($request);
        try {
            if ($form->isSubmitted() && $form->isValid()) {
                $dateDebut = $form->get('dateDebut')->getData();
                $dateFin = $form->get('dateFin')->getData();
                if ($dateDebut > $dateFin) {
                    $this->addFlash('error', 'Date debut est dépasser la date fin');
                    return $this->redirectToRoute('index_service');
                }
//                $service->setAddedBy($currentUser);
                //save
                $this->em->persist($service);
                $this->em->flush();
                $this->addFlash('success', 'service a été ajouté avec succès');
                return $this->redirectToRoute('index_service');
            }
        } catch (\Exception   $e) {
            $this->addFlash('error', $e->getCode() . ':' . $e->getMessage() . '' . $e->getFile() . '' . $e->getLine());
            return $this->redirectToRoute('index_service');

        }
        return $this->render('service/back_office/add.html.twig', [
            'form' => $form->createView(),
        ]);

    }


    /**
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("/", name="index_service")
     */
    public function index(Request $request)
    {
        //check if current user exist
        if ($this->getUser()) {
            $currentUser = $this->getUser();
        }else{
            $currentUser = 1 ;
        }
        //find , findby , findOneBy , findAll
        $query = $this->serviceRepository->findAll();
        $services  = $this->paginator->paginate($query, $request->query->get('page', 1), 3);

        return $this->render('service/back_office/index.html.twig', [
            'services' => $services,
        ]);

    }

    /**
     * @param Request $request
     * @param Service $service
     * @Route("/edit/{service}", name="edit_service")
     */
    public function edit(Request $request, Service $service)
    {

        $form = $this->createForm(ServiceType::class, $service);
        $form->handleRequest($request);
        try {
            if ($form->isSubmitted() && $form->isValid()) {
                $dateDebut = $form->get('dateDebut')->getData();
                $dateFin = $form->get('dateFin')->getData();
                if ($dateDebut > $dateFin) {
                    $this->addFlash('error', 'Date debut est dépasser la date fin');
                    return $this->redirectToRoute('index_service');
                }
                $this->em->persist($service);
                $this->em->flush();
                $this->addFlash('success', 'service a été modifié avec succès');
                return $this->redirectToRoute('index_service');
            }
        } catch (\Exception   $e) {
            $this->addFlash('error', $e->getCode() . ':' . $e->getMessage() . '' . $e->getFile() . '' . $e->getLine());
            return $this->redirectToRoute('index_service');

        }
        return $this->render('service/back_office/edit.html.twig', [
            'form' => $form->createView(),
            'service' =>$service

        ]);
    }


    /**
     * @param Request $request
     * @param Service $service
     * @Route("/delete/{service}", name="delete_service")
     */
    public function delete(Service $service)
    {
        try {
            // if service existe
            if ($service)
            {
                //delete
                $this->em->remove($service);
                $this->em->flush();
                $this->addFlash('success', 'service a été supprimé avec succès');

                return $this->redirectToRoute('index_service');

            }
        } catch (\Exception   $e) {
            $this->addFlash('error', $e->getCode() . ':' . $e->getMessage() . '' . $e->getFile() . '' . $e->getLine());
            return $this->redirectToRoute('index_service');

        }

    }

    /**
     * @param Request $request
     * @param Service $service
     * @Route("/detail/{service}", name="detail_service")
     */
    public function detail(Service $service)
    {
        try {
            if ($service) {
                return $this->render('service/back_office/detail.html.twig', [
                    'service' => $service,
                ]);
            }
        } catch (\Exception   $e) {
            $this->addFlash('error', $e->getCode() . ':' . $e->getMessage() . '' . $e->getFile() . '' . $e->getLine());
            return $this->redirectToRoute('index_service');

        }

    }
}