package com.javiermoreno.filtros;


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

            Empleado emp1 = new Empleado(0, "01-01234567-01", "Alice");
            emp1.setDelegacion("Barcelona");
            em.persist(emp1);
            
            Empleado emp2 = new Empleado(0, "02-01234567-01", "Bob");
            emp2.setDelegacion("Madrid");
            em.persist(emp2);
            
            Empleado emp3 = new Empleado(0, "03-01234567-01", "Charlie");
            emp3.setDelegacion("Barcelona");
            em.persist(emp3);
            
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

            org.hibernate.Session session = 
                    (org.hibernate.Session) em.getDelegate();
            org.hibernate.Filter filtro =
                    session.enableFilter(
                            "empleadosEnDelegacion");
            filtro.setParameter(
                            "delegacionActiva", "Barcelona");

            List<Empleado> empleados = 
                    em.createQuery("from Empleado)")
                      .getResultList();
            for (Empleado empleado : empleados) {
                    System.out.println(empleado);
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
