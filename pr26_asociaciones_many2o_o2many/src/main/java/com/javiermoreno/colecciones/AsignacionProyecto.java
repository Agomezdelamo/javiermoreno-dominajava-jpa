package com.javiermoreno.colecciones;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ASIG_PROYECTOS")
public class AsignacionProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "ID_ASIG")
    private long id;
    @Column(name = "FECHA_ASIG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "PROY_ID")
    private Proyecto proyecto;
    @ManyToOne
    @JoinColumn(name = "EMP_ID")
    private Empleado empleado;

    public AsignacionProyecto() {
        super();
    }

    public AsignacionProyecto(long id, Date fecha, 
            Proyecto proyecto, Empleado empleado) {
        super();
        this.id = id;
        this.fecha = fecha;
        this.proyecto = proyecto;
        this.empleado = empleado;
        proyecto.getAsignaciones().add(this);
    }

    public void desasignar() {
        proyecto.getAsignaciones().remove(this);
        this.empleado = null;
        this.proyecto = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
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
                + ((empleado == null) ? 0 : empleado.hashCode());
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result
                + ((proyecto == null) ? 0 : proyecto.hashCode());
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
        AsignacionProyecto other = (AsignacionProyecto) obj;
        if (empleado == null) {
            if (other.empleado != null) {
                return false;
            }
        } else if (!empleado.equals(other.empleado)) {
            return false;
        }
        if (fecha == null) {
            if (other.fecha != null) {
                return false;
            }
        } else if (!fecha.equals(other.fecha)) {
            return false;
        }
        if (proyecto == null) {
            if (other.proyecto != null) {
                return false;
            }
        } else if (!proyecto.equals(other.proyecto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AsignacionProyecto [id=" + id + ", fecha=" + fecha
                + ", proyecto=" + (proyecto == null ? null : proyecto.getNombre())
                + ", empleado=" + (empleado == null ? null : empleado.getNombre()) + "]";
    }
}
