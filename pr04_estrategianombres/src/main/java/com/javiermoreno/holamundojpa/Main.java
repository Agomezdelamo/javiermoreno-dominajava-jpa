package com.javiermoreno.holamundojpa;

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

            Empleado jefe = new Empleado(0, "Thor", null);
            Empleado emp = new Empleado(0, "Javi", jefe);

            //int idJefe = (Integer) session.save(jefe);
            em.persist(emp);

            System.out.println("JEFE: " + jefe);
            System.out.println("EMP: " + emp);
            tx.commit();
        } catch (PersistenceException e) {
            tx.rollback();
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
            }
            tx.commit();
        } catch (PersistenceException e) {
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
