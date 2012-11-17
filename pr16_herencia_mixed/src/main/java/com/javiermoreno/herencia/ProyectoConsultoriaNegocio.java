package com.javiermoreno.herencia;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("negocio")
public class ProyectoConsultoriaNegocio extends Proyecto {

    private static final long serialVersionUID = 1L;
    @Column(name = "AREA_PR")
    private AreaNegocioType area;

    public ProyectoConsultoriaNegocio() {
        super();
    }

    public ProyectoConsultoriaNegocio(int id, String nombre,
            int horasEstimadas, Empleado responsable,
            AreaNegocioType area) {
        super(id, nombre, horasEstimadas, responsable);
        this.area = area;
    }

    public AreaNegocioType getArea() {
        return area;
    }

    public void setArea(AreaNegocioType area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + "area=" + area;
    }
}
