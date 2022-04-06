<?php

namespace App\Controller;

use App\Entity\Event;
use App\Entity\User;
use App\Form\EventFormType;
use App\Repository\EventRepository;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use Symfony\Component\HttpFoundation\File\UploadedFile;



class EventAdminController extends AbstractController
{
    /**
     * @Route ("/admin/event/new", name="admin_event_new")
     * @IsGranted ("ROLE_ADMIN_EVENT")
     */
    public function new (EntityManagerInterface $em, Request $request)
    {

        $form = $this->createForm(EventFormType::class);

        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            /** @var Event $event */
            $event = $form->getData();
            $directory="C:\Users\alaak\Desktop\Software-Connoisseur\Sadak@ Web\public\images";
        $file = $form['eventImage']->getData();
            $extension = $file->guessExtension();
            if (!$extension) {
                // extension cannot be guessed
                $extension = 'bin';
            }
            $newFile= rand(1, 99999).'.'.$extension;
            $file->move($directory, $newFile);

            $event->setEventImage($newFile);
            $em->persist($event);
            $em->flush();

            $this->addFlash("success","Event Created ! Berakallahou Fik");
            return $this->redirectToRoute('admin_event_list');
        }
        return $this->render('event_admin/new.html.twig', [
            'eventForm' => $form->createView()
        ]);
    }

    /**
     * @Route("/admin/event/{id}/edit",name="admin_event_edit")
     * @IsGranted ("MANAGE", subject="event")
     */
    public function edit(Event $event,Request $request,EntityManagerInterface $em)
    {
        $form = $this->createForm(EventFormType::class,$event);

        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            /** @var Event $event */
            $event = $form->getData();

            $event->setEventImage('asteroid.jpeg');
            $em->persist($event);
            $em->flush();

            $this->addFlash("success","Event Updated ! Berakallahou Fik");
            return $this->redirectToRoute('admin_event_edit',
            ['id'=>$event->getId(),]);
        }
        return $this->render('event_admin/edit.html.twig', [
            'eventForm' => $form->createView()
        ]);
    }

    /**
     * @Route("/admin/event/{id}",name="admin_event_supp")
     */
    public function supp($id,EventRepository $repo)
    {
        $n=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($n);
        $em->flush();
        return $this->redirectToRoute('admin_event_list' );
    }


    /**
     * @Route("/admin/event",name="admin_event_list")
     * @IsGranted("ROLE_ADMIN")
     */
    public function list(EventRepository $repository, Request $request, PaginatorInterface $paginator)
    {
        $q = $request->query->get('q');
        $queryBuilder = $repository->getWithSearchQueryBuilder($q);

        $pagination = $paginator->paginate(
            $queryBuilder, /* query NOT result */
            $request->query->getInt('page', 1)/*page number*/,
            10/*limit per page*/
        );

        return $this->render('event_admin/list.html.twig', [
            'events' => $pagination,
        ]);
    }

    /**
     * @Route("/admin/eventt",name="admin_event_listt")
     * @IsGranted("ROLE_ADMIN")
     */
    public function listAdmin(EventRepository $repository, Request $request, PaginatorInterface $paginator)
    {
        $q = $request->query->get('q');
        $queryBuilder = $repository->getWithSearchQueryBuilder($q);

        $pagination = $paginator->paginate(
            $queryBuilder, /* query NOT result */
            $request->query->getInt('page', 1)/*page number*/,
            10/*limit per page*/
        );

        return $this->render('backAdmin/back-office/events.html.twig', [
            'events' => $pagination,
        ]);
    }
}