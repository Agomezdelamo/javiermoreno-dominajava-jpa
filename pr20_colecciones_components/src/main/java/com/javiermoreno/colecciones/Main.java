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

            Empleado emp = new Empleado(0, "Javi", null);
            emp.getDirecciones().add(new Direccion("Josep Antoni Bujons", "Martorell", "08760"));
            emp.getDirecciones().add(new Direccion("Rovellat", "Esplugues", "08950"));
            em.persist(emp);
            System.out.println("EMP: " + emp);
            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
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
                for (int idx = 0; idx < empleado.getDirecciones().size(); idx++) {
                    System.out.println(idx + "\t" + empleado.getDirecciones().get(idx) + " "
                            + empleado.getDirecciones().get(idx).getEmpleado().getId());
                }
            }

            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
