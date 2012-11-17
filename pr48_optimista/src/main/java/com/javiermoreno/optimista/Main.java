package com.javiermoreno.optimista;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) throws Exception {
        // BasicConfigurator.configure();

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        EntityManager em0 = factory.createEntityManager();
        EntityTransaction tx0 = em0.getTransaction();
        tx0.begin();
        Empleado emp0 = new Empleado(0, "Alice", "01/234567890/01");
        em0.persist(emp0);
        tx0.commit();
        em0.close();

        EntityManager em1 = factory.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Empleado empleado1 = em1.find(Empleado.class, emp0.getId());
        empleado1.setNombre("Alice alice");

        EntityManager em2 = factory.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();
        tx2.begin();

        Empleado empleado2 = em2.find(Empleado.class, emp0.getId());
        empleado2.setNombre("Alice a. Alice");

        tx1.commit();
        em1.close();

        try {
            tx2.commit();
        } catch (Exception e) {
            System.out.println("Error por versión: " + e.getCause());
            if (tx2.isActive() == true) {
                tx2.rollback();
            }
        } finally {
            em2.close();
        }

        EntityManager em3 = factory.createEntityManager();
        Empleado empleado3 = em3.find(Empleado.class, emp0.getId());
        System.out.println("El empleado se llama: " + empleado3.getNombre());
        em3.close();


    }
}
