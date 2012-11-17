package com.javiermoreno.valores;

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

            Empleado emp = new Empleado(0, "01/01234567/01", "Javi");
            Direccion dir = new Direccion("Josep Antoni Bujons", "Martorell", "08760");
            emp.setDireccion(dir);
            em.persist(emp);
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
                System.out.println(empleado.getDireccion());
                System.out.println(empleado.getDireccion().getEmpleado());
            }
            tx.commit();
        } catch (PersistenceException e) {
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
