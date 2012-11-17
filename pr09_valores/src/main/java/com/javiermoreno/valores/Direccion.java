package com.javiermoreno.valores;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Parent;

@Embeddable
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "CALLE_DIR")
    private String calle;
    @Column(name = "LOC_DIR")
    private String localidad;
    @Column(name = "CP_DIR")
    private String codigoPostal;
    @Parent
    private Empleado empleado;

    public Direccion() {
        super();
    }

    public Direccion(String calle, String localidad, String codigoPostal) {
        super();
        this.calle = calle;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "Direccion [calle=" + calle + ", localidad=" + localidad
                + ", codigoPostal=" + codigoPostal + "]";
    }
}
