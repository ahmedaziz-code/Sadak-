<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class RemarqueFamilleController extends AbstractController
{
    /**
     * @Route("/remarque/famille", name="remarque_famille")
     */
    public function index(): Response
    {
        return $this->render('remarque_famille/index.html.twig', [
            'controller_name' => 'RemarqueFamilleController',
        ]);
    }
}
