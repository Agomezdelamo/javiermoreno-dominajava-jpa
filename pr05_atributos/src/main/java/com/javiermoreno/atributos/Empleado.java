package com.javiermoreno.atributos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADOS")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "ID_EMP")
    private int id;
    @Column(name = "NOMBRE_EMP")
    private String nombre;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_JEFE")
    private Empleado jefe;
    @Column(name = "DIA_CONT_EMP")
    private int dia;
    @Column(name = "MES_CONT_EMP")
    private int mes;
    @Column(name = "ANYO_CONT_EMP")
    private int anyo;

    public Empleado() {
        super();
    }

    public Empleado(int id, String nombre, Empleado jefe) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.jefe = jefe;
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

    public Empleado getJefe() {
        return jefe;
    }

    public void setJefe(Empleado jefe) {
        this.jefe = jefe;
    }

    public void setFechaContratacion(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        anyo = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH) + 1;
        dia = cal.get(Calendar.DAY_OF_YEAR);
    }

    public Date getFechaContratacion() {
        Date date = null;
        if (dia + mes + anyo != 0) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.set(Calendar.DAY_OF_YEAR, dia);
            cal.set(Calendar.MONTH, mes - 1);
            cal.set(Calendar.YEAR, anyo);
            date = cal.getTime();
        }
        return date;
    }

    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombre=" + nombre + ", jefe=" + jefe
                + "]";
    }
}
