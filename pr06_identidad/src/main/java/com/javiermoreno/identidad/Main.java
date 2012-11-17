package com.javiermoreno.identidad;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;



public class Main {
	
	public static void main(String[] args) {
		String idEmp = null;
		EntityManager em = null;
		EntityTransaction tx = null; 

		EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("data");
		
		// Primer test
		try {
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Empleado emp = new Empleado(null, "01/01234567/01", "Javi");
                        em.persist(emp);
			idEmp = emp.getId();
			System.out.println(idEmp);
			tx.commit();
		} catch (PersistenceException e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		
	}

}
