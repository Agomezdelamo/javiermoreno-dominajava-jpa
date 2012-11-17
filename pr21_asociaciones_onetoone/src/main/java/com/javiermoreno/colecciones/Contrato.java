package com.javiermoreno.colecciones;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CONTRATOS")
public class Contrato implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "ID_CON")
    private long id;
    @Column(name = "INICIO_CON")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @OneToOne
    private Empleado empleado;

    public Contrato() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Contrato(long id, Date fechaInicio) {
        super();
        this.id = id;
        this.fechaInicio = fechaInicio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((empleado == null) ? 0 : empleado.getNombre().hashCode());
        result = prime * result
                + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
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
        Contrato other = (Contrato) obj;
        if (empleado == null) {
            if (other.empleado != null) {
                return false;
            }
        } else if (!empleado.getNombre().equals(other.empleado.getNombre())) {
            return false;
        }
        if (fechaInicio == null) {
            if (other.fechaInicio != null) {
                return false;
            }
        } else if (!fechaInicio.equals(other.fechaInicio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contrato [id=" + id + ", fechaInicio=" + fechaInicio
                + ", empleado=" + (empleado == null ? null : empleado.getNombre()) + "]";
    }
}
