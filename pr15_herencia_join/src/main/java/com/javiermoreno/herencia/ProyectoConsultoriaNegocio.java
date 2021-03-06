package com.javiermoreno.herencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "ID_PR")
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
}
