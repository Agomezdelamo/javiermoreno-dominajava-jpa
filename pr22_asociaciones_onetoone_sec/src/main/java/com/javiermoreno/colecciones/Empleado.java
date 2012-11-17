package com.javiermoreno.colecciones;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADOS")
@SecondaryTable(name = "EMP_EXPERIENCIA",
                pkJoinColumns = {
                    @PrimaryKeyJoinColumn(name = "EMP_ID")})
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
    @Column(name = "DB2_XP", table = "EMP_EXPERIENCIA")
    private int db2Level;
    @Column(name = "ORACLE_XP", table = "EMP_EXPERIENCIA")
    private int oracleLevel;

    public Empleado() {
        super();
    }

    public Empleado(int id, String nass, String nombre,
                    int db2Level, int oracleLevel) {
        super();
        this.nass = nass;
        this.id = id;
        this.nombre = nombre;
        this.db2Level = db2Level;
        this.oracleLevel = oracleLevel;
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

    public int getDb2Level() {
        return db2Level;
    }

    public void setDb2Level(int db2Level) {
        this.db2Level = db2Level;
    }

    public int getOracleLevel() {
        return oracleLevel;
    }

    public void setOracleLevel(int oracleLevel) {
        this.oracleLevel = oracleLevel;
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
        return "Empleado{" + "id=" + id + ", nass=" + nass + ", nombre=" + nombre + ", db2Level=" + db2Level + ", oracleLevel=" + oracleLevel + '}';
    }
}
