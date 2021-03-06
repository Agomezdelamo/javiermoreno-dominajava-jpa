package com.javiermoreno.herencia;

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


            Empleado empleado = new Empleado(0, "00-0000000-01", "Alice");
            em.persist(empleado);
            Empleado supervisor = new Empleado(0, "00-0000000-02", "Bob");
            em.persist(supervisor);
            Proyecto[] proyectos = {
                new ProyectoConsultoriaNegocio(0, "DEMO 1", 100, empleado, AreaNegocioType.MARKETING),
                new ProyectoDesarrollo(0, "DEMO 2", 100, empleado, PlataformaSoftwareType.JAVA),
                new ProyectoConsultoriaNegocio(0, "DEMO 3", 100, empleado, AreaNegocioType.CONTABILIDAD),
                new ProyectoDesarrolloInterno(0, "DEMO 2", 100, empleado, PlataformaSoftwareType.JAVA,supervisor)};

            for (Proyecto proyecto : proyectos) {
                em.persist(proyecto);
            }

            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
            return;
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            List<Proyecto> proyectos = em.createQuery("from Proyecto").getResultList();
            for (Proyecto proyecto : proyectos) {
                System.out.println(proyecto);
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
