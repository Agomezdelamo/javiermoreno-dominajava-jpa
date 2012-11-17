package com.javiermoreno.holamundoxml;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    
    private static Logger log = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        BasicConfigurator.configure();
        org.apache.log4j.LogManager.getRootLogger().setLevel(Level.INFO);
        
        Session session = null;
        Transaction tx = null;
        log.info("Inicializando factory.");
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        // Primer test
        try  {
            log.info("Inicializando sesión.");
            session = factory.openSession();
            tx = session.beginTransaction();

            Empleado jefe = new Empleado(0, "Thor", null);
            Empleado emp = new Empleado(0, "Javi", jefe);

            log.info("Registrando jefe.");
            int idJefe = (Integer) session.save(jefe);
            
            
            log.info("Registrando empleado.");
            int idEmp = (Integer) session.save(emp);

            log.info("JEFE: " + jefe);
            log.info("EMP: " + emp);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
        }

        // Segundo test

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            List<Empleado> empleados = session.createQuery("from Empleado e order by e.nombre asc").list();
            for (Empleado empleado : empleados) {
                System.out.println(empleado);
            }
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback(); 
        } finally {
            session.close();
        }

    }
}
