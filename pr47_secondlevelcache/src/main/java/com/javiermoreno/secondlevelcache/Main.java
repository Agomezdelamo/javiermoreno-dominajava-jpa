package com.javiermoreno.secondlevelcache;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class Main {

    public static void main(String[] args) {
        //BasicConfigurator.configure();

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        EntityManager em = null;
        EntityTransaction tx = null;

        Empleado bobDetached = null;
        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Empleado bob = new Empleado("Bob", "2345678901");

            em.persist(bob);

            bobDetached = bob;

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

            Empleado bob =
                    em.find(Empleado.class,
                    bobDetached.getId());
            System.out.println("BOB: " + bob);
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

            Empleado bob =
                    em.find(Empleado.class,
                    bobDetached.getId());
            System.out.println("BOB: " + bob);
            tx.commit();
        } catch (PersistenceException ex) {
            tx.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }

        System.exit(0);
    }
}
