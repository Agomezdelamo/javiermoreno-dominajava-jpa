package com.javiermoreno.listeners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.persistence.PersistenceException;

public class Main {

    public static void main(String[] args) {
        EntityManager em = null;
        EntityTransaction tx = null;

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Empleado alice =  new Empleado(0, "1234567890", "Alice");
            em.persist(alice);
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

            Empleado alice = em.find(Empleado.class, 1);
            // Ojo, prueba primero a modificar el nombre en el listener o bien cambia el nass
            alice.setNombre("Alice Wonderland"); 

            tx.commit();
        } catch (PersistenceException ex) {
            tx.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }


    }
}
