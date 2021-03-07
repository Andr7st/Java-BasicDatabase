CREATE DATABASE animalia
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

create table animales (
	id 	      serial NOT NULL,
	nombre 	      varchar(22) NOT NULL,
	descripcion   varchar(1200) NOT NULL,
	primary key (id)
);

CREATE SEQUENCE incremento_id
  start 1
  increment 1;

insert into animales (id, nombre, descripcion) 
values (nextval('incremento_id'), 'Perro', 'Es el mejor amigo del hombre, un animal sin igual.');



