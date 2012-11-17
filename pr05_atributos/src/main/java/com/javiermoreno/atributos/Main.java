package com.javiermoreno.atributos;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class Main {
	
	public static void main(String[] args) {
		int idEmp = 0;
		EntityManager em = null;
		EntityTransaction tx = null; 

		EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("data");
		
		// Primer test
		try {
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Empleado emp = new Empleado(0, "Javi", null);
			emp.setFechaContratacion(new Date());
			em.persist(emp);

			idEmp = emp.getId();
			tx.commit();
		} catch (PersistenceException e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		// Segundo test
		
		try {
			em = factory.createEntityManager();			
			Empleado emp = em.find(Empleado.class, idEmp);
			System.out.println(emp.getFechaContratacion());
		} catch (PersistenceException e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
	}

}
