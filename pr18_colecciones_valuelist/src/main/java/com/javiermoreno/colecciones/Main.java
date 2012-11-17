package com.javiermoreno.colecciones;

import java.util.List;

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

        // Primer test
        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Empleado emp = new Empleado(0, "00-00000000-01", "Alice");

            emp.getEmails().add("alice@javier-moreno.com");
            emp.getEmails().add("alice@gmail.com");
            emp.getEmails().add("alice@hotmail.com");

            em.persist(emp);
            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
            return;
        } finally {
            em.close();
        }

        // Segundo test

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            List<Empleado> empleados = em.createQuery("from Empleado e order by e.nombre asc").getResultList();
            for (Empleado empleado : empleados) {
                System.out.println(empleado);
                for (String email : empleado.getEmails()) {
                    System.out.println(email);
                }
                System.out.println();
            }
            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
            return;
        } finally {
            em.close();
        }

    }
}
