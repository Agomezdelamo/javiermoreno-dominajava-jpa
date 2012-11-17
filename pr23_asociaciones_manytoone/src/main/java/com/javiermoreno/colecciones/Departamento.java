package com.javiermoreno.colecciones;

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
    private long id;
    @Column(name = "NOMBRE_DEP")
    private String nombre;

    public Departamento() {
        super();
    }

    public Departamento(long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Departamento [id=" + id + ", nombre=" + nombre + "]";
    }
}
