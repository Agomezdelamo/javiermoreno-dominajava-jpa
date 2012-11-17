package com.javiermoreno.lucene;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

public class Main {
    
    public static String readResource(String resourcePath) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(Main.class.getClassLoader().getResourceAsStream(resourcePath), writer, "UTF-8");
        String result = writer.toString();        
        return result;
    }

    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = factory.createEntityManager();
            FullTextEntityManager fullTextEm =
                    Search.getFullTextEntityManager(em);
            fullTextEm.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        } finally {
            if (em != null) {
                em.close();
            }
        }


        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            String curri1 =readResource("cv1.txt");
            String curri2 = readResource("cv2.txt");
            String carta1 = readResource("ca1.txt");
            String carta2 = readResource("ca2.txt");

            Empleado[] empleados = {
                new Empleado("1234567890", "Alice", "Wonderland", false),
                new Empleado("2234567890", "Bob", "Wonderland", true),
                new Empleado("3234567890", "Charlie", "Wonderland", false),
                new Empleado("4234567890", "Dave", "Wonderland", true),
                new Empleado("5234567890", "Eveline", "Wonderland", false),
                new Empleado("6234567890", "Francis", "Wonderland", true)
            };
            for (Empleado empleado : empleados) {
                if (Math.random() < 0.33) {
                    empleado.setCurriculum(curri1);
                } else if (Math.random() < 0.33) {
                    empleado.setCurriculum(curri2);
                }
                if (Math.random() < 0.33) {
                    empleado.setCartaPresentacion(carta1);
                } else if (Math.random() < 0.33) {
                    empleado.setCartaPresentacion(carta2);
                }

                em.persist(empleado);
            }

            tx.commit();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            tx.rollback();
        } catch (IOException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            FullTextEntityManager fullTextEm =
                    Search.getFullTextEntityManager(em);
            MultiFieldQueryParser parser =
                    new MultiFieldQueryParser(
                    Version.LUCENE_31,
                    new String[]{"curriculum",
                        "cartaPresentacion"},
                    new StandardAnalyzer(Version.LUCENE_31));
            org.apache.lucene.search.Query luceneQuery =
                    parser.parse("Complutense");
            List<Empleado> empleados =
                    fullTextEm.createFullTextQuery(luceneQuery, Empleado.class).getResultList();
            for (Empleado empleado : empleados) {
                System.out.println(empleado);
            }
            tx.commit();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            tx.rollback();
        } catch (ParseException ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        org.hibernate.SessionFactory sessionFactory = null;
        try {
            em = factory.createEntityManager();
            org.hibernate.Session sesion =
                    (org.hibernate.Session) em.getDelegate();
            sessionFactory = sesion.getSessionFactory();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        org.hibernate.stat.Statistics estadisticas =
                sessionFactory.getStatistics();
        System.out.println(estadisticas);

        System.exit(0);
    }
}
