package com.javiermoreno.criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
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
    
    @ElementCollection
    @CollectionTable(name="EMAILS", 
            joinColumns = @JoinColumn(name = "PER_ID"))   
    @OrderColumn(name = "ROW")	
    @Column(name="ADDRESS")
    private List<String> emails= new ArrayList<String>();
    
    @ManyToOne
    @JoinColumn(name="DEP_ID", nullable = true)
    private Departamento departamento;
    
    @ManyToOne
    @JoinColumn(name = "ID_JEFE")
    private Empleado jefe;


    public Empleado() {
        super();
    }

    public Empleado(int id, String nass, String nombre) {
        super();
        this.nass = nass;
        this.id = id;
        this.nombre = nombre;
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

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Empleado getJefe() {
        return jefe;
    }

    public void setJefe(Empleado jefe) {
        this.jefe = jefe;
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
        return "Empleado{" + "id=" + id + ", nass=" + nass + ", nombre=" + nombre + ", departamento=" + departamento + '}';
    }


    
}
