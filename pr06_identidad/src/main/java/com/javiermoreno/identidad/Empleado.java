package com.javiermoreno.identidad;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid",
                                            strategy = "uuid")
@Table(name = "EMPLEADOS")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @Column(name = "ID_EMP", columnDefinition = "char(32)")
    private String id;
    @Column(name="NASS_EMP", length=14,nullable=false,unique=true)
    private String nass;
    @Column(name = "NOMBRE_EMP")
    private String nombre;
    
    public Empleado() {
        super();
    }

    public Empleado(String id, String nass, String nombre) {
        super();
        this.id = id;
        this.nass = nass;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.nass, other.nass)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nass);
        return hash;
    }

    public String toString() {
        return "Empleado{" + "id=" + id + ", nass=" + nass + ", nombre=" + nombre + '}';
    }

    
}
