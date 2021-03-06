package com.javiermoreno.jpql;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADOS")
public class Empleado extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "NASS_EMP", length = 14, nullable = false, unique = true)
    private String nass;
    
    @ManyToOne
    @JoinColumn(name = "ID_JEFE")
    private Empleado jefe;

    @ManyToOne
    @JoinColumn(name="DEP_ID", nullable = true)
    private Departamento departamento;
    
    @OneToOne
    private PuestoDeTrabajo puestoDeTrabajo;
    
    
    public Empleado() {
        super();
    }

    public Empleado(int id, String documentoIdentificacion, String nass, String nombre) {
        super(id, documentoIdentificacion, nombre);
        this.nass = nass;
    }

    public void asignarPuestoDeTrabajo(PuestoDeTrabajo puesto) {
        puesto.setEmpleado(this);
        this.puestoDeTrabajo = puesto;
    }

    public void desasignarPuestoDeTrabajo() { // <-- en una oneToOne no es necesario parámetro emp
        puestoDeTrabajo.setEmpleado(null);
        this.puestoDeTrabajo = null;
    }
    
    public String getNass() {
        return nass;
    }

    public void setNass(String nass) {
        this.nass = nass;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Empleado getJefe() {
        return jefe;
    }

    public void setJefe(Empleado jefe) {
        this.jefe = jefe;
    }

    public PuestoDeTrabajo getPuestoDeTrabajo() {
        return puestoDeTrabajo;
    }

    public void setPuestoDeTrabajo(PuestoDeTrabajo puestoDeTrabajo) {
        this.puestoDeTrabajo = puestoDeTrabajo;
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
        return super.toString() + ", " + "nass=" + nass + ", jefe=" + jefe + ", departamento=" + departamento + ", puestoDeTrabajo=" + puestoDeTrabajo + '}';
    }

    


    
}
