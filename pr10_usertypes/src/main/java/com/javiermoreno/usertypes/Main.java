package com.javiermoreno.usertypes;

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

            Departamento[] deps = new Departamento[]{
                new Departamento(0, "RRHH", new Coordenada(42, 2)),
                new Departamento(0, "Marketing", new Coordenada(42, 2)),
                new Departamento(0, "Organizaci�n", new Coordenada(42, 2)),
                new Departamento(0, "Finanzas", new Coordenada(40.4, -3.7)),};

            for (Departamento departamento : deps) {
                em.persist(departamento);
            }
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

            List<Departamento> departamentos = em.createQuery(
                    "from Departamento dep "
                    + "where dep.geolocalizacion.latitud > 41.0").getResultList();
            for (Departamento departamento : departamentos) {
                System.out.println(departamento);
            }
            tx.commit();
        } catch (PersistenceException e) {
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
