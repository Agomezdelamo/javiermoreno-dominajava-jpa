CREATE TABLE "APP"."EMPLEADOS"
(
   ID_EMP int PRIMARY KEY NOT NULL,
   NASS_EMP varchar(14) NOT NULL,
   NOMBRE_EMP varchar(255),
   ARCHIVADO_EMP int DEFAULT 0
);

insert into EMPLEADOS (ID_EMP, NASS_EMP, NOMBRE_EMP, archivado_emp) values (1, '01-01234567-01', 'Alice', 0);
insert into EMPLEADOS (ID_EMP, NASS_EMP, NOMBRE_EMP, archivado_emp) values (2, '02-01234567-02', 'Bob', 1);
insert into EMPLEADOS (ID_EMP, NASS_EMP, NOMBRE_EMP, archivado_emp) values (3, '03-01234567-03', 'Charlie', 1);
insert into EMPLEADOS (ID_EMP, NASS_EMP, NOMBRE_EMP, archivado_emp) values (4, '04-01234567-04', 'Dave', 0);
