package com.javiermoreno.usertypes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.hibernate.HibernateException;

public class Main {

    public static void main(String[] args) {
        EntityManager em = null;
        EntityTransaction tx = null;

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        InputStream is = null;
        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            File file = new File("promo.jpg");
            if (file.exists() == false) {
                throw new FileNotFoundException("promo.jpg not found.");
            }
            Departamento dep = new Departamento(0, "Marketing", file);
            em.persist(dep);
            tx.commit();
        } catch (FileNotFoundException e) {
            if (tx.isActive() == true) tx.rollback();
            e.printStackTrace();
            return;
        } catch (HibernateException e) {
            if (tx.isActive() == true) tx.rollback();
            e.printStackTrace();
            return;
        } finally {
            em.close();
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            List<Departamento> departamentos = em.createQuery(
                    "from Departamento dep ").getResultList();
            Departamento dep = departamentos.get(0);
            File file = dep.getPlano();
            BufferedImage image = ImageIO.read(file);
            JLabel label = new JLabel(new ImageIcon(image));
            JFrame frm = new JFrame();
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.add(label);
            frm.setBounds(100, 100, 400, 600);
            frm.setVisible(true);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } catch (IOException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
