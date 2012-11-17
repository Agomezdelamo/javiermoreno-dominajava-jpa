package com.javiermoreno.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.persistence.PersistenceException;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

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

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            org.hibernate.Session session = (org.hibernate.Session) em.getDelegate();
            org.hibernate.Criteria criteria = 
                    session.createCriteria(Empleado.class)
                               .setFetchMode("emails", FetchMode.JOIN)
                               .add(Restrictions.isNotNull("jefe"))
                               .createCriteria("departamento")
                                    .add(Restrictions.eq("nombre", "marketing")
                                    .ignoreCase());
            List<Empleado> result = criteria.list();

            for (Empleado empleado : result) {
                System.out.println(empleado.getNombre());
                System.out.println(empleado.getEmails());
            }

            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
