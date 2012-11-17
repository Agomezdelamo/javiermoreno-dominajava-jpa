package com.javiermoreno.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

            Departamento depMarketing = new Departamento("Marketing");
            em.persist(depMarketing);

            Departamento depFinanzas = new Departamento("Finanzas");
            em.persist(depFinanzas);

            Empleado emp1 = new Empleado(0, "01-01234567-01", "Alice");	// jefa
            emp1.getEmails().add("a@a.com");
            emp1.getEmails().add("a@b.com");
            emp1.getEmails().add("c@a.com");
            emp1.setDepartamento(depMarketing);

            Empleado emp2 = new Empleado(0, "01-88888888-01", "Bob"); // asignada, con jefa		
            emp2.getEmails().add("a@b.com");
            emp2.getEmails().add("b@b.com");
            emp2.setJefe(emp1);
            emp2.setDepartamento(depMarketing);

            Empleado emp3 = new Empleado(0, "01-76276211-01", "Charlie"); // no asignado, con jefa
            emp3.setJefe(emp1);
            emp3.setDepartamento(depMarketing);


            em.persist(emp1);
            em.persist(emp2);
            em.persist(emp3);

            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        /*******************************************************************
         * 
         * Pendiente de averiguar cómo integrar el generador estático de
         * modelo con el soporte para Maven.
         * 
         * ************************************************************** */
        throw new UnsupportedOperationException("Pendiente de averiguar cómo "
                + "integrar el generador estático de"
                + " modelo con el soporte para Maven.");
        /*
        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(Empleado.class);
            // FROM clause
            Root empleadoRoot = criteria.from(Empleado.class);
            // SELECT clause
            criteria.select(empleadoRoot);
            // WHERE clause (.as permite convertir a Integer, por ejemplo)
            Predicate condition = 
                    builder.and(
                        builder.like(empleadoRoot.get(Empleado_.nombre).as(String.class), "%li%"),
                        builder.equal(empleadoRoot.get(Empleado_.nass), "01-76276211-01"));
            criteria.where(condition);

            TypedQuery query = em.createQuery(criteria);
            List<Empleado> empleados = query.getResultList();
            for (Empleado empleado : empleados) {
                System.out.println(empleado);
                System.out.println("\t" + empleado.getDepartamento());
            }
            

            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        */
    }
}
