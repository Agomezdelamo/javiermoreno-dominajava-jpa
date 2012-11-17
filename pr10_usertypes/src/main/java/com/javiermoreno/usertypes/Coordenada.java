package com.javiermoreno.usertypes;

import java.io.Serializable;

public class Coordenada implements Serializable {

    private static final long serialVersionUID = 1L;
    private double latitud;
    private double longitud;

    public Coordenada(double latitude, double longitude) {
        super();
        this.latitud = latitude;
        this.longitud = longitude;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(latitud);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitud);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Coordenada other = (Coordenada) obj;
        if (Double.doubleToLongBits(latitud) != Double.doubleToLongBits(other.latitud)) {
            return false;
        }
        if (Double.doubleToLongBits(longitud) != Double.doubleToLongBits(other.longitud)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coordenada [latitud=" + latitud + ", longitud=" + longitud
                + "]";
    }
}
