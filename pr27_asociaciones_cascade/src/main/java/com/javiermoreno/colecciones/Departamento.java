package com.javiermoreno.colecciones;

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
    @GeneratedValue
    @Column(name = "ID_DEP")
    private long id;
    @Column(name = "NOMBRE_DEP")
    private String nombre;
    @OneToMany(mappedBy = "departamento") //sin el mappedBy no se crean correctamente las tablas
    private Set<Empleado> empleados = new HashSet<Empleado>();
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<PuestoDeTrabajo> puestosTrabajo = new HashSet<PuestoDeTrabajo>();

    public Departamento() {
        super();
    }

    public Departamento(long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public void asignarEmpleado(Empleado emp) {
        empleados.add(emp);
        emp.setDepartamento(this);
    }

    public void retirarEmpleado(Empleado emp) {
        empleados.remove(emp);
        if (emp.getPuestoDeTrabajo() != null) {
            emp.desasignarPuestoDeTrabajo();
        }
        emp.setDepartamento(null);
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

    public Set<PuestoDeTrabajo> getPuestosTrabajo() {
        return puestosTrabajo;
    }

    public void setPuestosTrabajo(Set<PuestoDeTrabajo> puestosTrabajo) {
        this.puestosTrabajo = puestosTrabajo;
    }

    @Override
    public String toString() {
        return "Departamento [id=" + id + ", nombre=" + nombre + "]";
    }
}
