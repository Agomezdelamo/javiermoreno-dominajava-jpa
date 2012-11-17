package com.javiermoreno.jpql;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class PuestoDeTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private long id;
    
    private String telefono;
    
    @ManyToOne
    @JoinColumn(name = "DEP_ID")
    private Departamento departamento;
    
    @OneToOne(mappedBy = "puestoDeTrabajo")
    private Empleado empleado;

    public PuestoDeTrabajo() {
        super();
    }

    public PuestoDeTrabajo(long id, String telefono, Departamento departamento) {
        super();
        this.id = id;
        this.telefono = telefono;
        this.departamento = departamento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PuestoDeTrabajo other = (PuestoDeTrabajo) obj;
        if ((this.telefono == null) ? (other.telefono != null) : !this.telefono.equals(other.telefono)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.telefono != null ? this.telefono.hashCode() : 0);
        return hash;
    }

    
    
    @Override
    public String toString() {
        return "PuestoTrabajo [id=" + id + ", telefono=" + telefono
                + ", departamento=" + (departamento == null ? null : departamento.getNombre())
                + ", empleado=" + (empleado == null ? null : empleado.getNombre())
                + "]";
    }
}
