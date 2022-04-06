<?php


namespace App\Controller\Admin;

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
 * @package App\Controller
 * @Route("admin/service")
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
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("/", name="admin_index_service")
     */
    public function index(Request $request)
    {
        if ($this->getUser()) {
            $currentUser = $this->getUser();
        }else{
            $currentUser = 1 ;
        }
        $query = $this->serviceRepository->getAllServices();
        $services  = $this->paginator->paginate($query, $request->query->get('page', 1), 10);

        return $this->render('backAdmin/back-office/index.html.twig/service/index.html.twig', [
            'services' => $services,
        ]);

    }

    /**
     * @param Request $request
     * @param Service $service
     * @Route("/edit/{service}", name="admin_edit_service")
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
                    $this->addFlash('error', 'Date debut est dépasser la date fin , pas logique');
                    return $this->redirectToRoute('admin_index_service');
                }
                $this->em->persist($service);
                $this->em->flush();
                $this->addFlash('success', 'service a été modifié avec succès');
                return $this->redirectToRoute('admin_index_service');
            }
        } catch (\Exception   $e) {
            $this->addFlash('error', $e->getCode() . ':' . $e->getMessage() . '' . $e->getFile() . '' . $e->getLine());
            return $this->redirectToRoute('admin_index_service');

        }
        return $this->render('backAdmin/back-office/edit.html.twig/service/edit.html.twig', [
            'form' => $form->createView(),
            'service' =>$service
        ]);
    }


    /**
     * @param Request $request
     * @param Service $service
     * @Route("/delete/{service}", name="admin_delete_service")
     */
    public function delete(Service $service)
    {
        try {
            if ($service) {
                $this->em->remove($service);
                $this->em->flush();
                $this->addFlash('success', 'service a été supprimé avec succès');

                return $this->redirectToRoute('admin_index_service');

            }
        } catch (\Exception   $e) {
            $this->addFlash('error', $e->getCode() . ':' . $e->getMessage() . '' . $e->getFile() . '' . $e->getLine());
            return $this->redirectToRoute('admin_index_service');

        }

    }

    /**
     * @param Request $request
     * @param Service $service
     * @Route("/detail/{service}", name="admin_detail_service")
     */
    public function detail(Service $service)
    {
        try {
            if ($service) {
                return $this->render('backAdmin/back-office/detail.html.twig/service/detail.html.twig', [
                    'service' => $service,
                ]);
            }
        } catch (\Exception   $e) {
            $this->addFlash('error', $e->getCode() . ':' . $e->getMessage() . '' . $e->getFile() . '' . $e->getLine());
            return $this->redirectToRoute('admin_index_service');

        }

    }
}