package com.javiermoreno.triggers;

import java.io.Serializable;
import java.util.Date;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "EMPLEADOS")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_EMP")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(name="NASS_EMP", length=14,nullable=false,unique=true)
    private String nass;
    @Column(name = "NOMBRE_EMP")
    private String nombre;
    
    @Column(name = "ULT_MOD_EMP", updatable = false, insertable = false)
    @org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.ALWAYS)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ultimaActualizacion;

    public Empleado() {
        super();
    }

    public Empleado(int id, String nass, String nombre) {
        super();
        this.id = id;
        this.nass = nass;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    private void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
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
        hash = 59 * hash + Objects.hashCode(this.nass);
        return hash;
    }

    public String toString() {
        return "Empleado{" + "id=" + id + ", nass=" + nass + ", nombre=" + nombre + ", ultimaActualizacion=" + ultimaActualizacion + '}';
    }

    
}
