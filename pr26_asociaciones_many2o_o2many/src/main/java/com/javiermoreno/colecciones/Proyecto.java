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
@Table(name = "PROYECTOS")
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_PROY")
    private long id;
    @Column(name = "NOMBRE_PROY")
    private String nombre;
    @OneToMany(mappedBy = "proyecto", orphanRemoval = true)
    private Set<AsignacionProyecto> asignaciones = new HashSet<AsignacionProyecto>();

    public Proyecto() {
        super();
    }

    public Proyecto(long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public Set<AsignacionProyecto> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(Set<AsignacionProyecto> asignaciones) {
        this.asignaciones = asignaciones;
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
        return "Proyecto{" + "id=" + id + ", nombre=" + nombre + ", asignaciones=" + asignaciones + '}';
    }
}
