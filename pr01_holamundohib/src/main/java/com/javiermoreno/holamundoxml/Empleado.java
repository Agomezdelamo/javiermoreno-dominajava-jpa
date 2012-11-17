package com.javiermoreno.holamundoxml;

public class Empleado {

    private int id;
    private String nombre;
    private Empleado jefe;

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

    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", jefe=" + jefe + '}';
    }

    
}
