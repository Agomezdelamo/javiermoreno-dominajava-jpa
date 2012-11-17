package com.javiermoreno.ciclovida;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.persistence.PersistenceException;

public class Main {

    public static void main(String[] args) {
        
        // Entidades transient:
        Departamento depMark = new Departamento("Marketing");
        Departamento depFina = new Departamento("Finanzas");

        Empleado emp1 = new Empleado(0, "01-01234567-01", "Alice");
        emp1.setDepartamento(depFina);

        Empleado emp2 = new Empleado(0, "01-01234567-02", "Bob");
        emp2.setDepartamento(depFina);

        Empleado emp3 = new Empleado(0, "55-01234567-02", "Charlie");
        emp3.setDepartamento(depFina);
        
        
        EntityManager em = null;
        EntityTransaction tx = null;

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            // Entidades persistent (mediante persist)
            em.persist(emp1);
            em.persist(emp2);
            em.persist(emp3);
            em.persist(depMark);
            em.persist(depFina);
            
            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        
        // Entidades detached (por el close).

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            // Error! No puede volver a persistirse
            em.persist(emp1);            
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
            
            emp1.setNombre("Alicity.");
            Empleado attached1 = em.merge(emp1);
            System.out.println(emp1 == attached1);
            System.out.println(emp1.equals(attached1));
            System.out.println("Nombre: " + attached1.getNombre());
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
            
            Departamento depFinaReattached = em.merge(depFina);
            // Error, hay una foreign desde departamento... ¡pero no se
            // lanzará hasta el commit!
            em.remove(depFinaReattached);
            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            // El error se produce al realizar el commit lo que
            // deja la transacción inactiva
            if (tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            Departamento depMarkReattached = em.merge(depMark);
            // Ok, no hay una foreign desde departamento.
            // Pasa a estado deleted.
            em.remove(depMarkReattached);
            
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
            
            // Patrón de persistencia por copia de atributos
            Empleado empDesdeCtrl = new Empleado(emp1.getId(), null, "Boby");           
            Empleado empLoaded = em.find(Empleado.class, empDesdeCtrl.getId());
            empLoaded.setNombre(empDesdeCtrl.getNombre());
            
            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
