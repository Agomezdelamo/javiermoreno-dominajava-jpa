package com.javiermoreno.batch;

import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.transaction.UserTransaction;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.objectweb.jotm.Jotm;
 
public class Main {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);

        try {

            LocateRegistry.createRegistry(1099);

            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.ow2.carol.jndi.spi."
                    + "MultiOrbInitialContextFactory");
            InitialContext ictx =
                    new InitialContext(props);

            LocalDataSource ds = new LocalDataSource();
            ds.setDriverName(
                    "org.apache.derby.jdbc.EmbeddedDriver");
            ds.setUrl("jdbc:derby:/temp/data;create=true");
            ds.setUser("app");
            ds.setPassword("app");
            ictx.bind("java:comp/env/jdbc/DataDS", ds);

            Jotm jotm = new Jotm(true, false);
            jotm.getUserTransaction().setTransactionTimeout(60);
            UserTransaction userTx =
                    jotm.getUserTransaction();
            ictx.bind("java:comp/env/jdbc/userTransaction",
                    userTx);

        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(-1);
        }

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Empleado alice = new Empleado("Alice", "1234567890");
            Empleado bob = new Empleado("Bob", "2345678901");

            em.persist(alice);
            em.persist(bob);

            tx.commit();
        } catch (PersistenceException ex) {
            tx.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }


        System.exit(0);

    }
}
