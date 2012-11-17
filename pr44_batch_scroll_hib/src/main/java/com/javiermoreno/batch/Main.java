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

            Empleado[] empleados = {
                new Empleado("1234567890", "Alice", "Wonderland", false),
                new Empleado("2234567890", "Bob", "Wonderland", true),
                new Empleado("3234567890", "Charlie", "Wonderland", false),
                new Empleado("4234567890", "Dave", "Wonderland", true),
                new Empleado("5234567890", "Eveline", "Wonderland", false),
                new Empleado("6234567890", "Francis", "Wonderland", true)
            };
            for (Empleado empleado : empleados) {
                em.persist(empleado);
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

            org.hibernate.Session session =
                    (org.hibernate.Session) em.getDelegate();
            org.hibernate.ScrollableResults scroll =
                    session.createQuery("from Empleado").scroll();
            int fila = 0;
            while (scroll.next() == true) {
                Empleado empleado = (Empleado) scroll.get(0);
                String nombre = empleado.getNombre();
                empleado.setNombre(nombre.toUpperCase());
                session.flush();
                session.clear();
                fila = fila + 1;
            }


            tx.commit();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        org.hibernate.SessionFactory sessionFactory = null;
        try {
            em = factory.createEntityManager();
            org.hibernate.Session sesion =
                    (org.hibernate.Session) em.getDelegate();
            sessionFactory = sesion.getSessionFactory();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        org.hibernate.stat.Statistics estadisticas =
                sessionFactory.getStatistics();
        System.out.println(estadisticas);
    }
}
