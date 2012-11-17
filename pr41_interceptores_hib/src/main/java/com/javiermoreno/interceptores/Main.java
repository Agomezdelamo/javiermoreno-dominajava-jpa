package com.javiermoreno.interceptores;

import java.lang.reflect.Field;
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
            Field field = factory.getClass().getDeclaredField("sessionFactory");
            field.setAccessible(true);
            org.hibernate.impl.SessionFactoryImpl sessionFactory =
                    (org.hibernate.impl.SessionFactoryImpl) field.get(factory);

            org.hibernate.event.LoadEventListener[] oldListeners =
                    sessionFactory.getEventListeners().getLoadEventListeners();
            org.hibernate.event.LoadEventListener[] newListeners =
                    new org.hibernate.event.LoadEventListener[oldListeners.length + 1];
            for (int idx = 0; idx < newListeners.length - 1; idx++) {
                newListeners[idx] = oldListeners[idx];
            }
            newListeners[newListeners.length - 1] = new EchoLoadListener();
            sessionFactory.getEventListeners().setLoadEventListeners(newListeners);


        } catch (Exception exc) {
            exc.printStackTrace();
            System.exit(0);
        }


        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Empleado alice = new Empleado(0, "Alice", "1234567890");
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

            Empleado emp = (Empleado) em.createQuery("from Empleado").getSingleResult();

            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
