<?php

namespace App\Controller;

use App\Entity\Comment;
use App\Entity\Event;
use App\Entity\User;
use App\Form\CommentType;
use App\Form\EventFormType;
use App\Repository\CommentRepository;
use App\Repository\EventRepository;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Knp\Snappy\Pdf;
use Psr\Log\LoggerInterface;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Address;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;

class EventController extends BaseController
{
    /**
     * @Route("/", name="app_homepage")
     */
//    public function homepage(EventRepository $rep) {
//
//        $events=$rep->findAllPublishedOrderedByNewest();
//
//
//        return $this->render('event/homepage.html.twig',[
//            'events'=>$events,
//        ]);
//    }

    public function homepage(EventRepository $repository, Request $request, PaginatorInterface $paginator)
    {
        $q = $request->query->get('q');
        $queryBuilder = $repository->getWithSearchQueryBuilder($q);

        $pagination = $paginator->paginate(
            $queryBuilder, /* query NOT result */
            $request->query->getInt('page', 1)/*page number*/,
            6/*limit per page*/
        );

        return $this->render('event/homepage.html.twig', [
            'pagination' => $pagination,
        ]);
    }





    /**
     * @Route("/news/{slug}", name="event_show")
     */
    public function show(Event $event,EntityManagerInterface $em, Request $request)
    {

        $user=$this->getUser();
        $form = $this->createForm(CommentType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            /** @var Comment $comment */
            $comment= $form->getData();
            $comment->setOrganiserName($user->getFirstName());
            $comment->setEvent($event);
            $em->persist($comment);
            $em->flush();

            $this->addFlash("success","Event Created ! Berakallahou Fik");

        }


        return $this->render('event/show.html.twig', [
            'event'=>$event,'user'=>$user, 'commentForm' => $form->createView()

        ]);
    }

    /**
     * @Route("/news/{slug}/heart", name="event_toggle_heart", methods={"POST"})
     * @IsGranted("ROLE_USER")
     */

    public function toggleEventHeart(Event $event,LoggerInterface $logger,EntityManagerInterface $em)
    {
        $event->incrementHeartCount();
        $em->flush();

        $logger->info('Article is being hearted!');
        return new JsonResponse(['hearts' => $event->getHeartCount()]);
    }


    /**
     * @Route("/email/{id}", name="email")
     */
    public function sendEmail($id,MailerInterface $mailer,Pdf $pdf,EventRepository $repo)
    {

        $event=$repo->find($id);
        //Generation du pdf
        $pdf->generateFromHtml(
            $this->renderView(
                'email/EventPDF.html.twig',
                array(
                    'some' => $event
                )
            ),
            $event->getEventName() . ".pdf"
        );

        $email = (new TemplatedEmail())
            ->from(new Address('ahmed@esprit.tn', 'Sadaka@'))
            ->to(new Address($this->getUser()->getEmail()))
            ->subject('Verification des famille')
            ->htmlTemplate('email/emailEvent.html.twig')
            ->context([
                'event' => $event
            ])
            ->attachFromPath($event->getEventName() . '.pdf');


        $mailer->send($email);
        return  Response::create("Success");
    }



}