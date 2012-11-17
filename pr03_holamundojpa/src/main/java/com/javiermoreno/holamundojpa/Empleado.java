package com.javiermoreno.holamundojpa;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADOS")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_EMP", nullable = false)
    private int id;
    @Column(name = "NOMBRE_EMP")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "ID_JEFE")
    private Empleado jefe;

    public Empleado() {
        super();
    }

    public Empleado(int id, String nombre, Empleado jefe) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.jefe = jefe;
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

    public Empleado getJefe() {
        return jefe;
    }

    public void setJefe(Empleado jefe) {
        this.jefe = jefe;
    }

    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombre=" + nombre + ", jefe=" + jefe
                + "]";
    }
}
