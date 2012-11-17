package com.javiermoreno.batch;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_proy")
    private int id;
    @Column(name = "nombre_proy",
    columnDefinition = "varchar(32)", length = 32,
    nullable = false, unique = true)
    private String nombre;
    @Column(name = "horas_proy")
    private int horasEstimadas;

    public Proyecto() {
        super();
    }

    public Proyecto(String nombre, int horasEstimadas) {
        super();
        this.nombre = nombre;
        this.horasEstimadas = horasEstimadas;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Proyecto other = (Proyecto) obj;
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Proyecto [id=" + id + ", nombre=" + nombre
                + ", horasEstimadas=" + horasEstimadas + "]";
    }
}
