package com.javiermoreno.herencia;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

@Entity
@DiscriminatorValue("interno")
@SecondaryTable(name = "PROY_DI",
pkJoinColumns =
@PrimaryKeyJoinColumn(name = "PROY_DES_ID"))
public class ProyectoDesarrolloInterno extends ProyectoDesarrollo {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_RESPON_PR", table = "PROY_DI")
    private Empleado supervisor;

    public ProyectoDesarrolloInterno() {
        super();
    }

    public ProyectoDesarrolloInterno(int id, String nombre,
            int horasEstimadas, Empleado responsable,
            PlataformaSoftwareType plataforma,
            Empleado supervisor) {
        super(id, nombre, horasEstimadas, responsable, plataforma);
        this.supervisor = supervisor;
    }

    public Empleado getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Empleado supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return super.toString() + " supervisor=[" + supervisor + ']';
    }
}
