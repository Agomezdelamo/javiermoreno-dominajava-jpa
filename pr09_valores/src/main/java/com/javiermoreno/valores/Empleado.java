package com.javiermoreno.valores;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADOS")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_EMP")
    private int id;
    @Column(name="NASS_EMP", length=14,nullable=false,unique=true)
    private String nass;
    
    @Column(name = "NOMBRE_EMP")
    private String nombre;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "calle", column =
        @Column(name = "CALLE_EMP")),
        @AttributeOverride(name = "localidad", column =
        @Column(name = "LOCALIDAD_EMP")),
        @AttributeOverride(name = "codigoPostal", column =
        @Column(name = "CP_EMP"))
    })
    private Direccion direccion;

    public Empleado() {
        super();
    }

    public Empleado(int id, String nass, String nombre) {
        super();
        this.id = id;
        this.nass = nass;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getNass() {
        return nass;
    }

    public void setNass(String nass) {
        this.nass = nass;
    }
    
    

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.nass, other.nass)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nass);
        return hash;
    }

    public String toString() {
        return "Empleado{" + "id=" + id + ", nass=" + nass + ", nombre=" + nombre + ", direccion=" + direccion + '}';
    }
    
    
}
