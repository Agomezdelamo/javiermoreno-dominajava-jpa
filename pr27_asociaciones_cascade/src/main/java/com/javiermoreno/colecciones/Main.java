package com.javiermoreno.colecciones;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

            Departamento departamento = new Departamento(0, "Marketing");
            em.persist(departamento);

            PuestoDeTrabajo pto1 = new PuestoDeTrabajo(0, "649 999920", departamento);
            PuestoDeTrabajo pto2 = new PuestoDeTrabajo(0, "649 123456", departamento);

            departamento.asignarPuestoTrabajo(pto1);
            departamento.asignarPuestoTrabajo(pto2);
            // no es necesario un persist, vendr� en cascada

            Empleado emp1 = new Empleado(0, "01-01234567-01", "Alice 1");
            Empleado emp2 = new Empleado(0, "02-01234567-02", "Bob 2");

            emp1.asignarPuestoDeTrabajo(pto1);
            emp2.asignarPuestoDeTrabajo(pto2);

            departamento.asignarEmpleado(emp1);
            departamento.asignarEmpleado(emp2);

            em.persist(emp1);
            em.persist(emp2);

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

            List<Departamento> deps = em.createQuery("from Departamento").getResultList();
            for (Departamento departamento : deps) {
                System.out.println(departamento);
            }

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

            Departamento departamento = (Departamento) em.createQuery(
                    "from Departamento dep where dep.nombre = 'Marketing'").getSingleResult();
            System.out.println(departamento);

            Set<PuestoDeTrabajo> puestos = new HashSet(departamento.getPuestosTrabajo());
            // ^-- ojo que si no se crea una copia no se puede remover del set que se est� recorriendo
            for (PuestoDeTrabajo puesto : puestos) {
                departamento.retirarPuesto(puesto); // <-- considerado orphan, pasa a ser eliminable
                System.out.println(departamento.getPuestosTrabajo().size());
                // ^-- comprobar que pasa si retirarPuesto ejecuta el setEmpleado(null) y luego el remove.
            }


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

            Departamento departamento = (Departamento) em.createQuery(
                    "from Departamento dep where dep.nombre = 'Marketing'").getSingleResult();
            System.out.println(departamento);

            Set<Empleado> empleados = new HashSet<Empleado>(departamento.getEmpleados());
            for (Empleado empleado : empleados) {
                departamento.retirarEmpleado(empleado);
            }

            em.remove(departamento);

            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }


    }
}
