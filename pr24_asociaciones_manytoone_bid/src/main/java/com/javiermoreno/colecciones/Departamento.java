package com.javiermoreno.colecciones;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
        emp.setDepartamento(null);
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
    public String toString() {
        return "Departamento [id=" + id + ", nombre=" + nombre + "]";
    }
}
