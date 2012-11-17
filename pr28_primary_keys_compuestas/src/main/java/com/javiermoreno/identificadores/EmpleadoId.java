/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiermoreno.identificadores;

import java.io.Serializable;

/**
 *
 * @author ciberado
 */
public class EmpleadoId  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String nombreDivision;
    private String codigo;

    public EmpleadoId() {
    }

    public EmpleadoId(String nombreDivision, String codigo) {
        this.nombreDivision = nombreDivision;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreDivision() {
        return nombreDivision;
    }

    public void setNombreDivision(String nombreDivision) {
        this.nombreDivision = nombreDivision;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmpleadoId other = (EmpleadoId) obj;
        if ((this.nombreDivision == null) ? (other.nombreDivision != null) : !this.nombreDivision.equals(other.nombreDivision)) {
            return false;
        }
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.nombreDivision != null ? this.nombreDivision.hashCode() : 0);
        hash = 13 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "EmpleadoId{" + "nombreDivision=" + nombreDivision + ", codigo=" + codigo + '}';
    }

    
    
}
