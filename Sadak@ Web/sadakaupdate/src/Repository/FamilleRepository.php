<?php

namespace App\Repository;

use App\Entity\Famille;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\QueryBuilder;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Famille|null find($id, $lockMode = null, $lockVersion = null)
 * @method Famille|null findOneBy(array $criteria, array $orderBy = null)
 * @method Famille[]    findAll()
 * @method Famille[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class FamilleRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Famille::class);
    }
    /**
     * @param string|null $term
     */
    public function getWithSearchQueryBuilder(?string $term): QueryBuilder
    {
        $qb = $this->createQueryBuilder('c')
            ->innerJoin('c.remarqueFs', 'a')
            ->addSelect('a');

        if ($term) {
            $qb->andWhere('c.dNaissanceM LIKE :term OR c.content LIKE :term OR c.eventName LIKE :term OR c.createdAt LIKE :term ')
                ->setParameter('term', '%' . $term . '%')
            ;
        }

        return $qb
            ->orderBy('c.dNaissanceM', 'DESC')
            ;
    }
    /**
     * @param $etat
     * @return Famille[]
     */
    public function ListFamilleByEtat(): array
    {
        return $this->createQueryBuilder('f')
            ->where('f.verifF LIKE :etat2')
            ->setParameters(['etat2'=>'verifiee'])
            ->getQuery()
            ->getResult();
    }

    /**
     * @return Famille[]
     */
    public function listFamilleByDateAndRemarqueAndNbrEnf(): array
    {
        $em = $this->getEntityManager();
        $query = $em->createQuery("SELECT f FROM \APP\Entity\Famille f WHERE f.verifF LIKE 'ver%' 
         ORDER BY f.dateV ASC,
         f.remarque DESC, f.nbrEnf DESC, f.nbrEnfMalade DESC ");
        return $query->getResult();
    }

    /**
     * @return Famille[]
     */
    public function listFamilleByEtat2(): array
    {
        $em = $this->getEntityManager();
        $query = $em->createQuery("SELECT f FROM \APP\Entity\Famille f WHERE f.verifF LIKE 'en%'");
        return $query->getResult();
    }

    // /**
    //  * @return Famille[] Returns an array of Famille objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('f')
            ->andWhere('f.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('f.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Famille
    {
        return $this->createQueryBuilder('f')
            ->andWhere('f.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
