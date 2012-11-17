package com.javiermoreno.ciclovida;

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
    @Column(name = "NOMBRE_DEP")
    private String nombre;

    public Departamento() {
        super();
    }

    public Departamento(String nombre) {
        super();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Departamento [nombre=" + nombre + "]";
    }
}
