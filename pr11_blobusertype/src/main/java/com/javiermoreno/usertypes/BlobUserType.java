package com.javiermoreno.usertypes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

public class BlobUserType implements CompositeUserType {
    
    @Override
    public Class returnedClass() {
        return File.class;
    }

    @Override
    public Object deepCopy(Object value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public boolean equals(Object arg0, Object arg1) throws HibernateException {
        File f0 = (File) arg0;
        File f1 = (File) arg1;
        if ((f0 == null) || (f1 == null)) {
            return false;
        }
        if ((f0.exists() ^ f1.exists())) {
            return false;
        }
        if ((f0.length() != f1.length())) {
            return false;
        }
        if (f0.getName().equals(f1.getName()) == false) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(Object arg0) throws HibernateException {
        File file = (File) arg0;
        return file.getName().hashCode() ^ (int) file.length();
    }

    @Override
    public Object assemble(Serializable arg0, SessionImplementor arg1,
            Object arg2) throws HibernateException {
        return null;
    }

    @Override
    public Serializable disassemble(Object arg0, SessionImplementor arg1)
            throws HibernateException {
        return null;
    }

    @Override
    public String[] getPropertyNames() {
        return new String[]{"data", "path"};
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{Hibernate.BLOB, Hibernate.STRING};
    }

    @Override
    public Object getPropertyValue(Object obj, int propIndex)
            throws HibernateException {
        try {
            File file = (File) obj;
            Object res = null;
            switch (propIndex) {
                case 0:
                    res = new BufferedInputStream(new FileInputStream(file));
                    break;
                case 1:
                    res = file.getName();
                    break;
            }
            return res;
        } catch (IOException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] columns, SessionImplementor imp, Object owner)
            throws HibernateException, SQLException {
        InputStream in = null;
        OutputStream out = null;
        try {
            File file = null;
            String fileName = rs.getString(columns[1]);
            if (rs.wasNull() == false) {
                in = rs.getBinaryStream(columns[0]);
                file = new File(fileName);
                out = new BufferedOutputStream(new FileOutputStream(file));
                byte[] data = new byte[1024];
                int len;
                do {
                    len = in.read(data, 0, data.length);
                    if (len > 0) {
                        out.write(data, 0, len);
                    }
                } while (len > -1);
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            throw new HibernateException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void nullSafeSet(
            PreparedStatement stmt, Object value,
            int index, SessionImplementor imp)
            throws SQLException {
        try {
            if (value == null) {
                stmt.setNull(index, java.sql.Types.BLOB);
                stmt.setNull(index + 1, java.sql.Types.VARCHAR);
            } else {
                File file = (File) value;
                stmt.setBinaryStream(index, new BufferedInputStream(new FileInputStream(file)));
                stmt.setString(index + 1, file.getName());
            }
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Object replace(Object arg0, Object arg1, SessionImplementor arg2,
            Object arg3) throws HibernateException {
        return arg0;
    }

    @Override
    public void setPropertyValue(Object arg0, int arg1, Object arg2)
            throws HibernateException {
        throw new UnsupportedOperationException("Binaries are immutable.");
    }
}