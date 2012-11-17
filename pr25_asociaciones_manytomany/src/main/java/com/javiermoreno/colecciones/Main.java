package com.javiermoreno.colecciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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


            Empleado emp1 = new Empleado(0, "01-01234567-01", "Alice");
            Empleado emp2 = new Empleado(0, "02-01234567-02", "Bob");
            Empleado emp3 = new Empleado(0, "03-01234567-03", "Charlie");

            Proyecto proy1 = new Proyecto(0, "Alpha");
            Proyecto proy2 = new Proyecto(0, "Beta");

            // Alice y Bob en Alpha. Bob y Charlie en Beta
            proy1.asignarEmpleado(emp1);
            proy1.asignarEmpleado(emp2);
            proy2.asignarEmpleado(emp2);
            proy2.asignarEmpleado(emp3);


            em.persist(emp1);
            em.persist(emp2);
            em.persist(emp3);
            em.persist(proy1);
            em.persist(proy2);

            tx.commit();
        } catch (HibernateException e) {
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
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            List<Proyecto> proyectos = em.createQuery("from Proyecto").getResultList();
            for (Proyecto proyecto : proyectos) {
                List<Empleado> empleados = new ArrayList<Empleado>(proyecto.getEmpleados());
                for (Empleado emp : empleados) {
                    proyecto.retirarEmpleado(emp);
                }
                em.remove(proyecto);
            }

            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            em.close();
        }


    }
}
