package com.javiermoreno.colecciones;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.SortType;

@Entity
@Table(name = "EMPLEADOS")
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "ID_EMP")
    private int id;
    @Column(name="NASS_EMP", length=14,nullable=false,unique=true)
    private String nass;    
    @Column(name = "NOMBRE_EMP")
    private String nombre;
    @ElementCollection(targetClass=String.class)
    @CollectionTable(name = "EMP_MAILS")
    @org.hibernate.annotations.LazyCollection(LazyCollectionOption.EXTRA)
    @org.hibernate.annotations.Sort(type = SortType.NATURAL)
    @Column(name = "ADDRESS")
    private SortedSet<String> emails = new TreeSet<String>();

    public Empleado() {
        super();
    }

    public Empleado(int id, String nass, String nombre) {
        super();
        this.nass = nass;
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNass() {
        return nass;
    }

    public void setNass(String nass) {
        this.nass = nass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SortedSet<String> getEmails() {
        return emails;
    }

    public void setEmails(SortedSet<String> emails) {
        this.emails = emails;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if ((this.nass == null) ? (other.nass != null) : !this.nass.equals(other.nass)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.nass != null ? this.nass.hashCode() : 0);
        hash = 67 * getClass().hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "id=" + id + ", nass=" + nass + ", nombre=" + nombre ;
    }

    
}
