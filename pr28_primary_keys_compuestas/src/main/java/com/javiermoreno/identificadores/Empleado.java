package com.javiermoreno.identificadores;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADOS")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Es una clave natural: puede utilizarse en el hashCode
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "nombreDivision", column =
                                        @Column(name = "DIV_EMP", length = 16)),
        @AttributeOverride(name = "codigo", column =
                                        @Column(name = "COD_EMP", length = 10))})
    private EmpleadoId id;
    
    @Column(name = "NASS_EMP", length = 14, nullable = false, unique = true)
    private String nass;
    @Column(name = "NOMBRE_EMP")
    private String nombre;

    public Empleado() {
        super();
    }

    public Empleado(String nombreDivision, String codigo, 
                   String nass, String nombre) {
        super();
        this.nass = nass;
        this.id = new EmpleadoId(nombreDivision, codigo);
        this.nombre = nombre;
    }

    public String getDivision() {
            return id.getNombreDivision();
    }

    public String getCodigo() {
            return id.getCodigo();
    }
    
    public String getNass() {
        return nass;
    }

    public void setNass(String nass) {
        this.nass = nass;
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
        final Empleado other = (Empleado) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nass=" + nass + ", nombre=" + nombre + '}';
    }

    

}
