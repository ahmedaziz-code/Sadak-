<?php

namespace App\Repository;

use App\Entity\RemarqueFamille;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method RemarqueFamille|null find($id, $lockMode = null, $lockVersion = null)
 * @method RemarqueFamille|null findOneBy(array $criteria, array $orderBy = null)
 * @method RemarqueFamille[]    findAll()
 * @method RemarqueFamille[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class RemarqueFamilleRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, RemarqueFamille::class);
    }

    /**
     * @param $idF
     * @return RemarqueFamille[]
     */
    /*public function ListRemarqueByFamille($id): array
    {
        return $this->createQueryBuilder('r')
            ->join('r.famille','f')
            ->addSelect('f')
            ->where('f.idF=:id')
            ->setParameter('id',$id)
            ->getQuery()
            ->getResult();
    }*/
    public function ListRemarqueByFamille($id)
    {
        $em=$this->getEntityManager();
        $query=$em
            ->createQuery("SELECT r FROM APP\Entity\RemarqueFamille r JOIN r.famille f WHERE f.idF=:id")
            ->setParameter('id',$id);
        return $query->getResult();
    }

    // /**
    //  * @return RemarqueFamille[] Returns an array of RemarqueFamille objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('r.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?RemarqueFamille
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
