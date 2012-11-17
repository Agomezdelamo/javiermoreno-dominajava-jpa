package com.javiermoreno.batch;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleado implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "id_emp")
    private int id;
    @Column(name = "nass_emp")
    private String numeroSeguridadSocial;
    @Column(name = "nombre_emp",
    columnDefinition = "varchar(32)",
    length = 32)
    private String nombre;
    @Column(name = "apellidos_emp",
    columnDefinition = "varchar(64)",
    length = 64)
    private String apellidos;
    @Column(name = "activo")
    private boolean activo;

    public Empleado() {
        super();
    }

    public Empleado(
            String numeroSeguridadSocial,
            String nombre,
            String apellidos, boolean activo) {
        super();
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroSeguridadSocial() {
        return numeroSeguridadSocial;
    }

    public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
        this.numeroSeguridadSocial = numeroSeguridadSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((numeroSeguridadSocial == null) ? 0 : numeroSeguridadSocial.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Empleado other = (Empleado) obj;
        if (numeroSeguridadSocial == null) {
            if (other.numeroSeguridadSocial != null) {
                return false;
            }
        } else if (!numeroSeguridadSocial.equals(other.numeroSeguridadSocial)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombre=" + nombre
                + ", numeroSeguridadSocial=" + numeroSeguridadSocial + "]";
    }
}