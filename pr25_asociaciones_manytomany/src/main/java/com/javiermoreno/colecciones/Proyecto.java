package com.javiermoreno.colecciones;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROYECTOS")
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_PROY")
    private long id;
    @Column(name = "NOMBRE_PROY")
    private String nombre;
    @ManyToMany
    @JoinTable(name = "EMP_EN_PROY",
               joinColumns = {
                    @JoinColumn(name = "PROY_ID")},
               inverseJoinColumns = {
                    @JoinColumn(name = "EMP_ID")})
    private Set<Empleado> empleados = new HashSet<Empleado>();

    public Proyecto() {
        super();
    }

    public Proyecto(long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public void asignarEmpleado(Empleado emp) {
        empleados.add(emp);
        emp.getProyectos().add(this);
    }

    public void retirarEmpleado(Empleado emp) {
        empleados.remove(emp);
        emp.getProyectos().remove(this);
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

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proyecto other = (Proyecto) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        return hash;
    }

    

    @Override
    public String toString() {
        StringBuilder empDesc = new StringBuilder();
        for (Empleado emp : empleados) {
            empDesc.append(emp.getNombre()).append(", ");
        }

        return "Proyecto [id=" + id + ", nombre=" + nombre + ", empleados="
                + empDesc + "]";
    }
}
