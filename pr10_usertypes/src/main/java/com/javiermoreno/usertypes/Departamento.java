package com.javiermoreno.usertypes;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DPTS")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_DEP")
    private int id;
    @Column(name = "NOMBRE_DEP")
    private String nombre;
    @org.hibernate.annotations.Type(type = "com.javiermoreno.usertypes.CoordenadaUserType")
    @org.hibernate.annotations.Columns(columns = {
        @Column(name = "LAT_DEP"),
        @Column(name = "LON_DEP")})
    private Coordenada geolocalizacion;

    public Departamento() {
        super();
    }

    public Departamento(int id, String nombre, Coordenada geolocalizacion) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
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

    public Coordenada getGeolocalizacion() {
        return geolocalizacion;
    }

    public void setGeolocalizacion(Coordenada geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departamento other = (Departamento) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public String toString() {
        return "Departamento [id=" + id + ", nombre=" + nombre
                + ", geolocalizacion=" + geolocalizacion + "]";
    }
}
