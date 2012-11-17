package com.javiermoreno.colecciones;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import org.hibernate.HibernateException;

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


            Empleado emp = new Empleado(0, "01-01234567-02", "Alice");
            Contrato con = new Contrato(0, new Date());
            emp.asignarContrato(con);

            em.persist(emp);

            tx.commit();
        } catch (PersistenceException exc) {
            exc.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            List<Empleado> empleados = em.createQuery("from Empleado").getResultList();
            for (Empleado empleado : empleados) {
                System.out.println(empleado);
            }

            tx.commit();
        } catch (PersistenceException exc) {
            exc.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            List<Empleado> empleados = em.createQuery("from Empleado").getResultList();
            Empleado emp = empleados.get(0);
            emp.retirarContrato(); // orphan!
            System.out.println(emp);

            tx.commit();
        } catch (PersistenceException exc) {
            exc.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            List<Empleado> empleados = em.createQuery("from Empleado").getResultList();
            Empleado emp = empleados.get(0);
            em.remove(emp);

            tx.commit();
        } catch (PersistenceException exc) {
            exc.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }


    }
}
