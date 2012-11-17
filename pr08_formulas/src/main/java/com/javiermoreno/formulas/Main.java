package com.javiermoreno.formulas;


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
			Empleado emp = em.find(Empleado.class, 1);
			System.out.println(emp.getSueldo());
		} catch (PersistenceException e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
	}

}
