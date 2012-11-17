package com.javiermoreno.usertypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

public class CoordenadaUserType implements CompositeUserType {

    @Override
    public Class returnedClass() {
        return Coordenada.class;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object deepCopy(Object obj) {
        return obj;
    }

    @Override
    public Serializable disassemble(Object obj, SessionImplementor imp) {
        return (Serializable) obj;
    }

    @Override
    public Object assemble(Serializable cachedObj, SessionImplementor imp, Object owner)
            throws HibernateException {
        return cachedObj;
    }

    @Override
    public Object replace(Object original, Object target, SessionImplementor imp, Object owner) {
        return original;
    }

    @Override
    public boolean equals(Object arg0, Object arg1) throws HibernateException {
        Coordenada c1 = (Coordenada) arg0;
        Coordenada c2 = (Coordenada) arg1;

        if (c1 == c2) {
            return true;
        }
        if (c1 == null || c2 == null) {
            return false;
        }
        return c1.equals(c2);
    }

    @Override
    public int hashCode(Object obj) throws HibernateException {
        return obj.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] columns, SessionImplementor imp, Object owner)
            throws HibernateException, SQLException {
        Coordenada coord = null;
        double latitud;
        double longitud;
        latitud = rs.getDouble(columns[0]);
        if (rs.wasNull() == false) {
            longitud = rs.getDouble(columns[1]);
            coord = new Coordenada(latitud, longitud);
        }
        return coord;
    }

    @Override
    public void nullSafeSet(PreparedStatement stmt, Object value, int index, SessionImplementor imp)
            throws SQLException {
        if (value == null) {
            stmt.setNull(index, java.sql.Types.DOUBLE);
            stmt.setNull(index + 1, java.sql.Types.DOUBLE);
        } else {
            Coordenada coord = (Coordenada) value;
            stmt.setDouble(index, coord.getLatitud());
            stmt.setDouble(index + 1, coord.getLongitud());
        }
    }

    @Override
    public String[] getPropertyNames() {
        return new String[]{"latitud", "longitud"};
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{Hibernate.DOUBLE, Hibernate.DOUBLE};
    }

    @Override
    public Object getPropertyValue(Object obj, int propIndex)
            throws HibernateException {
        Coordenada coord = (Coordenada) obj;
        Object res = null;
        switch (propIndex) {
            case 0:
                res = coord.getLatitud();
                break;
            case 1:
                res = coord.getLongitud();
                break;
        }
        return res;
    }

    @Override
    public void setPropertyValue(Object obj, int propIndex, Object propValue)
            throws HibernateException {
        throw new UnsupportedOperationException("Coordinates are immutable.");
    }
}
