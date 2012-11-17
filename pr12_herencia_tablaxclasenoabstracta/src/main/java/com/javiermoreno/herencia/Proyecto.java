package com.javiermoreno.herencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "ID_PR")
    private int id;
    @Column(name = "NOMBRE_PR")
    private String nombre;
    @Column(name = "HORAS_PR")
    private int horasEstimadas;
    @ManyToOne
    @JoinColumn(name = "ID_RESPON_PR")
    private Empleado responsable;

    public Proyecto() {
        super();
    }

    public Proyecto(int id, String nombre, int horasEstimadas,
            Empleado responsable) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.horasEstimadas = horasEstimadas;
        this.responsable = responsable;
    }

    public int getId() {
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

    public int getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(int horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
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
        int hash = 7;
        hash = 61 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 61 * hash + getClass().hashCode();
        return hash;
    }    
    
    @Override
    public String toString() {
        return  "id=" + id + ", nombre=" + nombre + ", horasEstimadas=" + horasEstimadas + ", responsable=[" + responsable + ']';
    }
    
    
}
