package com.javiermoreno.batch;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class Main {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("data");
		
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = factory.createEntityManager(); 
			tx = em.getTransaction();
			tx.begin();

			Empleado[] empleados =  {
				new Empleado("1234567890", "Alice", "Wonderland", false),
				new Empleado("2234567890", "Bob", "Wonderland", true),
				new Empleado("3234567890", "Charlie", "Wonderland", false),
				new Empleado("4234567890", "Dave", "Wonderland", true),
				new Empleado("5234567890", "Eveline", "Wonderland", false),
				new Empleado("6234567890", "Francis", "Wonderland", true)					
			};
			for (Empleado empleado : empleados) {
				em.persist(empleado);
			}
			
			tx.commit();
		} catch(PersistenceException ex) {
			tx.rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		try {
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			String hql = 
				"insert into EmpleadoArchivado " 
			  + " (id, numeroSeguridadSocial, nombre, fechaArchivacion) "
			  + "select "
			  + "  emp.id, "
			  + "  emp.numeroSeguridadSocial, "
			  + "  concat(emp.nombre, ' ', emp.apellidos), "
			  + "  current_timestamp "
			  + "from Empleado emp "
			  + "where emp.activo = false";
			int entidadesActualizadas = em 
				.createQuery(hql)
				.executeUpdate();
			System.out.println(entidadesActualizadas);
			hql = "delete "
			    + "from Empleado emp "
				+ "where emp.activo = false";
			entidadesActualizadas = em 
				.createQuery(hql)
				.executeUpdate();
			System.out.println(entidadesActualizadas);
			
			tx.commit();
		} catch (PersistenceException ex)  {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
	}

}
