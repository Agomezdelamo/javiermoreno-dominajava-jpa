package com.javiermoreno.listeners;



import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class ListenersEmpleado {

	@PrePersist
	public void prePersist(Object entidad) {
		System.out.println("@PrePersist *****");
		System.out.println(entidad);
		System.out.println("*******************");
	}
	
	@PostPersist
	public void postPersistir(Object entidad) {
		System.out.println("postPersistir *****");
		System.out.println(entidad);
		System.out.println("*******************");
	}
	@PreUpdate
	public void preUpdate(Object entidad) {
		System.out.println("@PreUpdate *****");
		System.out.println(entidad);
		System.out.println("*******************");
	}
	@PostUpdate
	public void postUpdate(Object entidad) {
		System.out.println("@PostUpdate *****");
		System.out.println(entidad);
		System.out.println("*******************");
	}
	@PreRemove
	public void preRemove(Object entidad) {
		System.out.println("@PreRemove *****");
		System.out.println(entidad);
		System.out.println("*******************");
	}
	@PostRemove
	public void postRemove(Object entidad) {
		System.out.println("@PostRemove *****");
		System.out.println(entidad);
		System.out.println("*******************");
	}
	@PostLoad	
	public void postLoad(Object entidad) {
		System.out.println("@PostLoad *****");
		System.out.println(entidad);
		System.out.println("*******************");
	}

	
}
