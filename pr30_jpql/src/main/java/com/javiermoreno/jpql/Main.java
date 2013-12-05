package com.javiermoreno.jpql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.persistence.PersistenceException;

public class Main {

    public static void main(String[] args) throws IOException {
        //org.apache.log4j.BasicConfigurator.configure();

        EntityManager em = null;
        EntityTransaction tx = null;

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("data");

        try {
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Departamento depMarketing = new Departamento("Marketing");
            em.persist(depMarketing);

            Departamento depFinanzas = new Departamento("Finanzas");
            em.persist(depFinanzas);

            PuestoDeTrabajo pto1 = new PuestoDeTrabajo(0, "649 999920", null);
            PuestoDeTrabajo pto2 = new PuestoDeTrabajo(0, "649 123456", null);

            depMarketing.asignarPuestoTrabajo(pto1);
            depMarketing.asignarPuestoTrabajo(pto2);

            Empleado emp1 = new Empleado(0, "8888888A", "01-01234567-01", "Alice");	// jefa
            emp1.setDireccion(new Direccion("Rovellat", "Esplugues", "08950"));
            emp1.getEmails().add("a@gmail.com");
            emp1.getEmails().add("a@hotmail.com");
            emp1.setDepartamento(depMarketing);
            emp1.asignarPuestoDeTrabajo(pto1);

            Empleado emp2 = new Empleado(0, "44444444A", "01-88888888-01", "Bob"); // asignada, con jefa		
            emp2.setDireccion(new Direccion("Bruc", "Esplugues", "08950"));
            emp2.setJefe(emp1);
            emp2.getEmails().add("x@bitting.com");
            emp2.setDepartamento(depMarketing);
            emp2.asignarPuestoDeTrabajo(pto2);

            Empleado emp3 = new Empleado(0, "12345678A", "01-76276211-01", "Charlie"); // no asignado, con jefa
            emp3.setDireccion(new Direccion("Indianapolis", "Esplugues", "08950"));
            emp3.setJefe(emp1);
            emp3.getEmails().add("y@bitting.com");
            emp3.setDepartamento(depMarketing);

            Persona per0 = new Persona(0, "66668888B", "Dave");
            per0.setDireccion(new Direccion("Josep Antoni", "Martorell", "09760"));
            per0.getEmails().add("y@gmail.com");
            per0.getEmails().add("xux@gmail.com");


            em.persist(emp1);
            em.persist(emp2);
            em.persist(emp3);

            em.persist(per0);

            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        try {
            System.out.println("\r\n\r\n***********************************************\r\n");
            String[] queries = {
                // 1 Elemento simple
                "select emp from Empleado as emp",
                // 2 Polimorismo
                "select per from Persona as per",
                // 3 HQL Limitando el polimorfismo
                "select per from Persona as per where per.class = Empleado",
                // 4 HQL Polimorismo extremo, multiquery. Ojo que con order by no ser�a correcto.
                "select obj from java.lang.Object as obj",
                // 5 Atributo
                "select emp.nombre from Empleado as emp",
                // 6 Concatenaciones
                "select emp.jefe.nombre || ' es jefe de ' || emp.nombre as resumen from Empleado emp join emp.jefe",
                // 7 Funciones
                "select emp.nombre, current_timestamp() from Empleado emp",
                // 8 Join, con fetch (ver reducci�n de lazies, �til para colecciones) 
                "select emp from Empleado as emp join fetch emp.puestoDeTrabajo as puesto join fetch emp.departamento  ",
                // 9 Join entre atributos, qued�ndonos con el �ltimo
                "select puesto from Empleado as emp join emp.puestoDeTrabajo as puesto",
                // 10 Equivalente, m�s compacto
                "select emp.puestoDeTrabajo from Empleado as emp",
                // 11 Recuperaci�n de diversos elementos. Ignora a Charlie proque no tiene puesto
                "select emp, emp.puestoDeTrabajo from Empleado as emp join emp.puestoDeTrabajo",
                // 12 Producto cartesiano (no hay join)
                "select emp, puesto from Empleado as emp, PuestoDeTrabajo as puesto",
                // 13 HQL En lugar de un array de objects lo recupera como una lista (lista de listas). 
                "select new list(emp, emp.puestoDeTrabajo) from Empleado as emp join emp.puestoDeTrabajo",
                // 14 El inner join recupera solo los empleados con puesto 
                "select emp from Empleado as emp join emp.puestoDeTrabajo",
                // 15 Recuperaci�n de diversos elementos con left join. Incluye a charlie
                // sin el alias expl�cito no se recuperar�a
                "select emp, puesto from Empleado as emp left join emp.puestoDeTrabajo as puesto",
                // 16 Creando un objeto usando su constructor.
                "select new com.javiermoreno.jpql.GrupoDeTrabajo(emp.jefe, emp) from Empleado as emp where emp.puestoDeTrabajo is null",
                // 17 Agregaciones. Se pueden usar avg, avg(dsitinct), sum, sum(distinct), min, max, count(*), count(distinct)
                "select count(*) from Empleado",
                // 18 HQL Recuperaci�n en map
                "select new map(emp.jefe as jefe, emp as empleado) from Empleado as emp",
                // 19 where simple
                "select emp from Empleado as emp where emp.nombre = 'Alice'",
                // 20 where no tan simple
                "select emp from Empleado as emp where emp.jefe.nombre = 'Alice'",
                // 21 igualdad utilizada para comparar instancias
                "select emp, jefe from Empleado as emp, Empleado as jefe where emp.jefe= jefe",
                // 22 navegaci�n por grafo autom�tica
                "select emp from Empleado emp where emp.puestoDeTrabajo.departamento.nombre='Marketing'",
                // 23 HQL la misma, usando la palabra reservada id (evita una join al indicar que est� en el fk)
                "select emp from Empleado as emp where emp.puestoDeTrabajo.departamento.id='Marketing'",
                // 24 Componentes
                   "select per from Persona as per where per.direccion is null ",
                // 25 enumeraciones				
                "select per from Persona as per where per.nombre in ('Alice', 'Dave')",
                // 26 las colecciones tienen una propiedad extra llamada size
                "select dep from Departamento as dep where dep.puestosTrabajo.size > 0",
                // 27 solo si la base de datos soporta subquieres:
                "select per from Persona per where size(per.emails) > 1",
                // 28 listas, arrays y maps se pueden indexar (solo en el where y si hay ordercolumn)
                "select per from Persona as per where per.emails[0] like '%gmail.com'",
                // 29 HQL hasta se puede usar para coger el �ltimo mail
                "select per from Persona as per where per.emails[size(per.emails)-1] like '%gmail.com'",
                // 30 HQL index(x) indica el �ndice de x y puede usarse si se quiere tambi�n en el where (como limit)
                "select cast(index(email) as char) || ' ' || email from Persona per join per.emails email ",
                "select index(email), email from Persona per join per.emails email ",
                // 31 Order by, como siempre. Ojo a los �ndices.
                "select emp from Empleado emp order by emp.nombre desc",
                // 32 group by (podr�a usarse having)
                "select count(*), dir.localidad from Persona per join per.direccion dir group by dir.localidad ",
                // 33 gropy y Order by con un twist: el n�mero de direcciones
                "select emp.nombre from Empleado emp left join emp.emails  email group by emp.nombre order by count(email)",};


            em = factory.createEntityManager();
            tx = em.getTransaction();
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            for (int idx = 0; idx < queries.length; idx++) {
                String ql = queries[idx];
                tx.begin();
                System.out.println(idx + "\t" + ql + "\r\n");
                List resultados = em.createQuery(ql).getResultList();
                System.out.println();
                for (Object resultado : resultados) {
                    if (resultado instanceof Object[]) {
                        Object[] resArray = (Object[]) resultado;
                        for (Object resObject : resArray) {
                            System.out.println("\t" + resObject);
                        }
                        System.out.println();
                    } else {
                        System.out.println("\t" + resultado);
                    }
                }
                System.out.println("***********************************************\r\n");
                console.readLine();
                tx.commit();
                em.clear();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
