package com.javiermoreno.jpql;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DPTS")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "NOMBRE_DEP")
    private String nombre;
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<PuestoDeTrabajo> puestosTrabajo = new HashSet<PuestoDeTrabajo>();

    public Departamento() {
        super();
    }

    public Departamento(String nombre) {
        super();
        this.nombre = nombre;
    }

    public void asignarPuestoTrabajo(PuestoDeTrabajo puesto) {
        puestosTrabajo.add(puesto);
        puesto.setDepartamento(this);
    }

    public void retirarPuesto(PuestoDeTrabajo puesto) {
        puestosTrabajo.remove(puesto); // <-- cascade orphan!
        if (puesto.getEmpleado() != null) {
            // evita la foreign key violation!
            puesto.getEmpleado().desasignarPuestoDeTrabajo();
        }
        puesto.setDepartamento(null);
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
        final Departamento other = (Departamento) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        return hash;
    }
    
    

    @Override
    public String toString() {
        return "Departamento [nombre=" + nombre + "]";
    }
}
