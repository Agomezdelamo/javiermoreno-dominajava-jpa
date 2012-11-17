package com.javiermoreno.colecciones;

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

            Departamento dept1 = new Departamento(0, "Marketing 1");
            Departamento dept2 = new Departamento(0, "Investigaci�n 2");

            Empleado emp1 = new Empleado(0, "01-01234567-02", "Alice 1");
            Empleado emp2 = new Empleado(0, "02-01234567-03", "Bob 2");

            dept1.asignarEmpleado(emp1);
            dept2.asignarEmpleado(emp2);

            em.persist(emp1);
            em.persist(emp2);
            em.persist(dept1);
            em.persist(dept2);

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

            Departamento dep1 = (Departamento) em.createQuery(
                    "from Departamento dep where dep.nombre = 'Marketing 1'").getSingleResult();
            System.out.println(dep1);
            for (Empleado emp : dep1.getEmpleados()) {
                System.out.println("\t" + emp);
            }

            Empleado emp2 = (Empleado) em.createQuery(
                    "from Empleado emp where emp.nass = '02-01234567-03'")
                    .getSingleResult();
            System.out.println(emp2);
            //    !!! 
            //    OJO! no está usando la doble navegación por lo que si no está el mappedBy
            //    no funcinoará y si sí que está funcionará como efecto secundario y no será
            //    coherente con lo que tenga en memoria.
            emp2.setDepartamento(dep1);

            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }


    }
}
