package com.javiermoreno.usertypes;

import java.io.File;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DPTS")
public class Departamento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "ID_DEP")
    private int id;
    @Column(name = "NOMBRE_DEP")
    private String nombre;
    @org.hibernate.annotations.Type(type = "com.javiermoreno.usertypes.BlobUserType")
    @org.hibernate.annotations.Columns(columns = {
        @Column(name = "DATA", length = 1024*128),
        @Column(name = "PATH")})
    private File plano;

    public Departamento() {
        super();
    }

    public Departamento(int id, String nombre, File plano) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.plano = plano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public File getPlano() {
        return plano;
    }

    public void setPlano(File plano) {
        this.plano = plano;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departamento other = (Departamento) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 97 * hash + getClass().hashCode();
        return hash;
    }

    
    
    @Override
    public String toString() {
        return "Departamento [id=" + id + ", nombre=" + nombre
                + "]";
    }
}
