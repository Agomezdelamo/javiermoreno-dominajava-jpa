package com.javiermoreno.colecciones;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADOS")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_EMP")
    private int id;
    @Column(name = "NASS_EMP", length = 14, nullable = false, unique = true)
    private String nass;
    @Column(name = "NOMBRE_EMP")
    private String nombre;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "empleado")
    @PrimaryKeyJoinColumn
    private Contrato contrato;

    public Empleado() {
        super();
    }

    public Empleado(int id, String nass, String nombre) {
        super();
        this.nass = nass;
        this.id = id;
        this.nombre = nombre;
    }

    public void asignarContrato(Contrato con) {
            this.contrato = con;
            con.setEmpleado(this);
    }

    public void retirarContrato() {
            this.contrato.setEmpleado(null);
            this.contrato = null;
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

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
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
        return "Empleado{" + "id=" + id + ", nass=" + nass + ", nombre=" + nombre + ", contrato=" + contrato + '}';
    }

    
}
