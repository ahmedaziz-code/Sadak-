<?php

namespace App\Repository;

use App\Entity\Service;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Service|null find($id, $lockMode = null, $lockVersion = null)
 * @method Service|null findOneBy(array $criteria, array $orderBy = null)
 * @method Service[]    findAll()
 * @method Service[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ServiceRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Service::class);
    }

    public function getAllServices() {
        return $this->createQueryBuilder('s')
            ->select('s', 't')
            ->leftJoin('s.type', 't')
            ->getQuery()
            ->getResult()
            ;
    }

    public function getAllMeServices($currentUser) {
        return $this->createQueryBuilder('s')
            ->select('s', 't')
            ->leftJoin('s.type', 't')
            ->where('s.addedBy = :currentUser')
            ->setParameter('currentUser', $currentUser)
            ->getQuery()
            ->getResult()
            ;
    }


    public function getServiceByCritere($key) {
        return $this->createQueryBuilder('s')
            ->select('s', 't')
            ->leftJoin('s.type', 't')
            ->where('s.name LIKE :name')
            ->orWhere('s.description LIKE :description')
            ->orWhere('s.numVert LIKE :num')
            ->orWhere('s.zoneDispo LIKE :zoneDispo')
            ->setParameters(array(
                'name' => "%" .$key. "%",
                'description' => "%" .$key. "%",
//                'num' => "%" .$key. "%",
                'zoneDispo' => "%" .$key. "%",
            ))
            ->getQuery()
            ->getResult()
            ;
    }
    // /**
    //  * @return Service[] Returns an array of Service objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('s.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Service
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
