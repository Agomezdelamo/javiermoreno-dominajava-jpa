package com.javiermoreno.listeners;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADOS")
@EntityListeners(ListenersEmpleado.class)
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "ID_EMP")
    private int id;
    @Column(name = "NASS_EMP", length = 14, nullable = false, unique = true)
    private String nass;
    @Column(name = "NOMBRE_EMP")
    private String nombre;
    

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
        return "id=" + id + ", nass=" + nass + ", nombre=" + nombre;
    }


    
}
