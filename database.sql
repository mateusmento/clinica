drop database if exists clinica_db;
create database clinica_db;
use clinica_db;

drop user if exists clinica@localhost;
create user clinica@localhost identified by 'clinica123';

grant all privileges on clinica_db.* to clinica@localhost;


create table medico (
	id int not null auto_increment,
	nome varchar(64) not null,
	especialidade varchar(64) not null,
	primary key(id)
);

create table paciente (
	id int not null auto_increment,
	nome varchar(64) not null,
	login varchar(64) not null,
	senha varchar(64) not null,
	primary key (id),
	unique key(login)
);

create table consulta (
	id int not null auto_increment,
	paciente_id int not null,
	medico_id int not null,
	data date not null,
	horario time not null,
	convenio varchar(32) not null,
	primary key(id)
);


insert into medico values
(null, "Samanta Gonçalves", "Homeopatia"   ),
(null, "Carlos Saramini",   "Oftalmologia" ),
(null, "Cecilia Santana",   "Neurologia"   ),
(null, "Mateus Montes",     "Neurologia"   ),
(null, "Gabriel Aguilar",   "Ortopedia"    ),
(null, "Tiago Soares",      "Ortopedia"    ),
(null, "Celia Tramonha",    "Cardiologia"  ),
(null, "Teonirio Ipanema",  "Cardiologia"  ),
(null, "Pedro Magalhães",   "Cardiologia"  ),
(null, "Sara Queiroz",      "Pediatria"    ),
(null, "Felipe Ávila",      "Pediatria"    ),
(null, "Luiz Pacheco",      "Pediatria"    );

insert into paciente values
(null, "Beatrice Oliveira ", "paciente", "123");
