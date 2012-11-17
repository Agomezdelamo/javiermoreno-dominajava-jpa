package com.javiermoreno.batch;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "empleadosArchivados")
public class EmpleadoArchivado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_emp")
    private int id;
    @Column(name = "nass_emp")
    private String numeroSeguridadSocial;
    @Column(name = "nombre_emp",
    columnDefinition = "varchar(32)",
    length = 32)
    private String nombre;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fec_arc_emp")
    private Date fechaArchivacion;

    public EmpleadoArchivado() {
        super();
    }

    public EmpleadoArchivado(
            String numeroSeguridadSocial,
            String nombre) {
        super();
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.nombre = nombre;
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

    public Date getFechaArchivacion() {
        return fechaArchivacion;
    }

    public void setFechaArchivacion(Date fechaArchivacion) {
        this.fechaArchivacion = fechaArchivacion;
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
        EmpleadoArchivado other = (EmpleadoArchivado) obj;
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
