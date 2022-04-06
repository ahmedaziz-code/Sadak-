<?php


namespace App\Controller\Service\front_office;


use App\Repository\ServiceRepository;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class ServiceController extends AbstractController
{


    private $em;
    private $serviceRepository;

    /**
     * ServiceController constructor.
     * @param EntityManagerInterface $em
     * @param ServiceRepository $serviceRepository
     */
    public function __construct(EntityManagerInterface $em,
                                ServiceRepository $serviceRepository)
    {
        $this->em = $em;
        $this->serviceRepository = $serviceRepository;
    }

    /**
     * @param Request $request
     * @return string
     * @Route("/list_services_public", name="list_services_public")
     */
    public function listService(Request $request)
    {
        $services =$this->serviceRepository->findAll();
        return $this->render('service/front_ofiice/listService.html.twig', array(
            'services' =>  $services
        ));
    }

    /**
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("/dashboard", name="dashboard_public")
     */
    public function dashboardPublic() {
        return $this->render('service/front_ofiice/base.html.twig');
    }

}