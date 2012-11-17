package com.javiermoreno.triggers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        org.apache.log4j.BasicConfigurator.configure();
        org.apache.log4j.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
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

            Empleado emp = new Empleado(0, "01/01234567/01", "Javi");
            logger.debug("Última actualización [1] " + emp.getUltimaActualizacion());
            em.persist(emp);
            logger.debug("Última actualización [2] " + emp.getUltimaActualizacion());

            idEmp = emp.getId();
            logger.debug(String.valueOf(idEmp));
            tx.commit();
            logger.debug("Última actualización [3] " + emp.getUltimaActualizacion());
        } catch (PersistenceException e) {
            e.printStackTrace();
            if (tx.isActive() == true) { 
                tx.rollback();
            }
        } finally {
            em.close();
        }

        // Segundo test

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Empleado emp = em.find(Empleado.class, idEmp);
            logger.debug("Última actualización [4] " + emp.getUltimaActualizacion());
            emp.setNombre(emp.getNombre().toUpperCase());
            logger.debug("Última actualización [5] " + emp.getUltimaActualizacion());
            tx.commit();
            logger.debug("Última actualización [6] " + emp.getUltimaActualizacion());
        } catch (PersistenceException e) {
            e.printStackTrace();
            if (tx.isActive() == true) { 
                tx.rollback();
            }
        } finally {
            em.close();
        }

    }
}
