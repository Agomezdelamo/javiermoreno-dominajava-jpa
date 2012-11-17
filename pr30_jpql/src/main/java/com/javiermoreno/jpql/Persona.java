package com.javiermoreno.jpql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="PERSONAS")
@Inheritance(strategy=InheritanceType.JOINED)
public class Persona implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "ID_PER")
    private int id;
    
    @Column(name = "DOCID_PER")
    private String documentoIdentificacion;
    
    @Column(name = "NOMBRE_PER")
    private String nombre;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "calle", column =
        @Column(name = "CALLE_PER")),
        @AttributeOverride(name = "localidad", column =
        @Column(name = "LOCALIDAD_PER")),
        @AttributeOverride(name = "codigoPostal", column =
        @Column(name = "CP_PER"))
    })
    private Direccion direccion;

    @ElementCollection
    @CollectionTable(name="EMAILS", 
            joinColumns = @JoinColumn(name = "PER_ID"))   
    @OrderColumn(name = "ROW")	
    @Column(name="ADDRESS")
    private List<String> emails= new ArrayList<String>();

    public Persona() {
    }

    public Persona(int id, String documentoIdentificacion, String nombre) {
        this.id = id;
        this.documentoIdentificacion = documentoIdentificacion;
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
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

    public String getDocumentoIdentificacion() {
        return documentoIdentificacion;
    }

    public void setDocumentoIdentificacion(String documentoIdentificacion) {
        this.documentoIdentificacion = documentoIdentificacion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if ((this.documentoIdentificacion == null) ? (other.documentoIdentificacion != null) : !this.documentoIdentificacion.equals(other.documentoIdentificacion)) {
            return false;
        }
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.documentoIdentificacion != null ? this.documentoIdentificacion.hashCode() : 0);
        hash = 79 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        return hash;
    }

    
    @Override
    public String toString() {
        return "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", emails=" + emails + " ";
    }

    
    
}
