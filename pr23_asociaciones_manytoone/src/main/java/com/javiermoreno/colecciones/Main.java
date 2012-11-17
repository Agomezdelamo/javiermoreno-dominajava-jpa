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

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Departamento dept = new Departamento(0, "Marketing");
            Empleado emp1 = new Empleado(0, "Alice", "01-01234567-01");
            emp1.setDepartamento(dept);
            Empleado emp2 = new Empleado(0, "Bob", "02-01234567-02");
            emp2.setDepartamento(dept);

            em.persist(dept);
            em.persist(emp1);
            em.persist(emp2);

            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            List<Empleado> empleados = em.createQuery("from Empleado").getResultList();
            for (Empleado emp : empleados) {
                System.out.println(emp + "\r\n\t" + emp.getDepartamento());
            }

            List<Departamento> departamentos = 
                    em.createQuery("from Departamento").getResultList();
            for (Departamento dep : departamentos) {
                System.out.println(dep);
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
