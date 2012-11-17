package com.javiermoreno.auditoria;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class Main {

    public static void main(String[] args) throws Exception {
        //BasicConfigurator.configure();

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        EntityManager em = null;
        EntityTransaction tx = null;
        Empleado empDetached = null;
        
        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Empleado emp = new Empleado("01-01234567-01", "Alice");
            em.persist(emp);
            empDetached = emp;
            tx.commit();
        } catch (PersistenceException exc) {
            if (tx.isActive() == true) {
                tx.rollback();
            }
            exc.printStackTrace(System.err);
        } finally {
            em.close();
        }
        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Empleado emp = em.find(Empleado.class, empDetached.getId());
            emp.setNombre("Alicity");
            tx.commit();
        } catch (PersistenceException exc) {
            if (tx.isActive() == true) {
                tx.rollback();
            }
            exc.printStackTrace(System.err);
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Empleado emp = em.find(Empleado.class, empDetached.getId());
            emp.setNombre("Alicity");
            tx.commit();
        } catch (PersistenceException exc) {
            if (tx.isActive() == true) {
                tx.rollback();
            }
            exc.printStackTrace(System.err);
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Empleado emp = em.find(Empleado.class, empDetached.getId());
            emp.setNombre("Aliné");
            tx.commit();
        } catch (PersistenceException exc) {
            if (tx.isActive() == true) {
                tx.rollback();
            }
            exc.printStackTrace(System.err);
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            org.hibernate.envers.AuditReader reader = 
                    org.hibernate.envers.AuditReaderFactory.get(em);
            List<Number> revisionNumbers = 
                    reader.getRevisions(Empleado.class, empDetached.getId());
            for (Number revisionNumber : revisionNumbers) {
                Empleado emp1 = 
                        reader.find(Empleado.class, empDetached.getId(), revisionNumber);
                System.out.println(emp1);
            }
            
            tx.commit();
        } catch (PersistenceException exc) {
            if (tx.isActive() == true) {
                tx.rollback();
            }
            exc.printStackTrace(System.err);
        } finally {
            em.close();
        }

    }
}
