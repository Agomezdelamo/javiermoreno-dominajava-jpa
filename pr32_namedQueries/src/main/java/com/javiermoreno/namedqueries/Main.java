package com.javiermoreno.namedqueries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.jmx.StatisticsService;

public class Main {

    public static void main(String[] args) {
        EntityManager em = null;
        EntityTransaction tx = null;

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        StatisticsService stats = new StatisticsService();
        try {
            em = factory.createEntityManager();
            org.hibernate.Session session = (org.hibernate.Session) em.getDelegate();
            stats.setSessionFactory(session.getSessionFactory());
            stats.setStatisticsEnabled(true);
            tx = em.getTransaction();
            tx.begin();

            Departamento depMark = new Departamento("Marketing");
            em.persist(depMark);
            Departamento depFina = new Departamento("Finanzas");
            em.persist(depFina);

            for (int idx = 0; idx < 100; idx++) {
                Empleado emp = new Empleado();
                emp.setNass(RandomStringUtils.randomAlphabetic(10));
                emp.setNombre(RandomStringUtils.randomAlphabetic((int) (Math.random() * 20))
                             + " " 
                             + RandomStringUtils.randomAlphabetic((int) (Math.random() * 20)));
                if (Math.random() > 0.50) {
                    emp.setDepartamento(depMark);
                } else {
                    emp.setDepartamento(depFina);
                }
                em.persist(emp);
            }
            System.out.println("************************************************");
            System.out.println(stats.getEntityInsertCount());
            System.out.println("************************************************");
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

            Departamento depMark = em.getReference(Departamento.class, "Marketing");

            Query query = em.createNamedQuery("Empleado.findPorDepartamento")
                    .setFirstResult(20)
                    .setMaxResults(10)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .setHint("org.hibernate.timeout", 10 /*segundos*/)
                    .setHint("org.hibernate.fetchSize", 10)
                    .setHint("org.hibernate.comment", "[Prueba de query]")
                    .setHint("org.hibernate.cacheable", false)
                    .setHint("org.hibernate.readOnly", true /*Evita la necesidad del dirtycheck*/);
            query.setParameter("dep", depMark);
            List<Empleado> empleados = query.getResultList();

            System.out.println(empleados.size());

            // Ojo, no se actualizará pero tampoco produce un error debido al readOnly!
            empleados.get(0).setNombre("X");
            tx.commit();
            System.out.println("************************************************");
            System.out.println(stats.getTransactionCount());
            System.out.println("************************************************");
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
