package com.javiermoreno.batch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Proyecto[] proyectos = {
                new Proyecto("RX00", 100),
                new Proyecto("RX02", 110),
                new Proyecto("RX03", 150),
                new Proyecto("IU0AA", 80),
                new Proyecto("IU0AB", 180),
                new Proyecto("IU0AC", 50)};
            for (Proyecto proyecto : proyectos) {
                em.persist(proyecto);
            }

            tx.commit();
        } catch (PersistenceException ex) {
            tx.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            String jpql =
                    "update Proyecto p "
                    + "set p.horasEstimadas = "
                    + "  cast((p.horasEstimadas * (:factor/100)) as int)";
            int entidadesAfectadas = em
                    .createQuery(jpql)
                    .setParameter("factor", 200).executeUpdate();

            System.out.println(entidadesAfectadas);

            tx.commit();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
