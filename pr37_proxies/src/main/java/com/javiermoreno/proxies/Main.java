package com.javiermoreno.proxies;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.persistence.PersistenceException;
import org.hibernate.Hibernate;

public class Main {

    public static void main(String[] args) {
        EntityManager em = null;
        EntityTransaction tx = null;

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        Departamento depMark = new Departamento("Marketing");
        Empleado emp1 = new Empleado(0, "01-01234567-01", "Alice");
        emp1.setDepartamento(depMark);
        
        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.persist(depMark);
            em.persist(emp1);
            
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

            // No hay query: útil como parámetro jpql
            Empleado proxy = em.getReference(
                    Empleado.class, emp1.getId());
            
            // Fuerza query.
            System.out.println(proxy.getId());
            System.out.println(proxy.getNombre());
            
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

            Empleado proxyDetached = em.getReference(
                    Empleado.class, emp1.getId());
            
            System.out.println(Hibernate.isInitialized(proxyDetached));
            Hibernate.initialize(proxyDetached);

            System.out.println(emp1 == proxyDetached);            
            // False! por la clase: es un proxy!
            System.out.println(emp1.equals(proxyDetached));
            
            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
