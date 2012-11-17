package com.javiermoreno.jpql;

public class GrupoDeTrabajo {
	
	private Empleado director;
	
	private Empleado realizador;

	public GrupoDeTrabajo(Empleado director, Empleado realizador) {
		super();
		this.director = director;
		this.realizador = realizador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((director == null) ? 0 : director.hashCode());
		result = prime * result
				+ ((realizador == null) ? 0 : realizador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoDeTrabajo other = (GrupoDeTrabajo) obj;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (realizador == null) {
			if (other.realizador != null)
				return false;
		} else if (!realizador.equals(other.realizador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GrupoDeTrabajo [director=" + director.getNombre() + ", realizador="
				+ realizador.getNombre() + "]";
	}
	
	

}
