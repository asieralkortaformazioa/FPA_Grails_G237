-- drop table complexity;
create table complexity (
ID Integer,
Description Varchar (50),
PRIMARY KEY (ID)
);

insert into complexity values (0,'Low');
insert into complexity values (1,'Average');
insert into complexity values (2,'High');


-- drop table complexity_matrix;

create table complexity_matrix (
	ID Integer,
	type varchar (30),
	horizontal_low_limit integer,
	horizontal_top_limit integer,
	vertical_low_limit integer,
	vertical_top_limit integer,
	complexity integer,
	PRIMARY KEY (ID)
);


insert into complexity_matrix values (1,'ILFEIF',1,1,1,19,0);
insert into complexity_matrix values (2,'ILFEIF',1,1,20,50,0);

insert into complexity_matrix values (3,'ILFEIF',1,1,-1,51,0);
insert into complexity_matrix values (4,'ILFEIF',2,5,1,19,0);

insert into complexity_matrix values (5,'ILFEIF',2,5,20,50,1);

insert into complexity_matrix values (6,'ILFEIF',2,5,51,-1,2);

insert into complexity_matrix values (7,'ILFEIF',6,-1,1,19,1);

insert into complexity_matrix values (8,'ILFEIF',6,-1,20,50,2);

insert into complexity_matrix values (9,'ILFEIF',6,-1,51,-1,2);
insert into complexity_matrix values (10,'EI',0,1,1,4,0);

insert into complexity_matrix values (11,'EI',0,1,5,15,0);
insert into complexity_matrix values (12,'EI',0,1,16,-1,1);
insert into complexity_matrix values (13,'EI',2,2,1,4,0);
insert into complexity_matrix values (14,'EI',2,2,5,15,1);
insert into complexity_matrix values (15,'EI',2,2,16,-1,2);
insert into complexity_matrix values (16,'EI',3,-1,1,4,1);
insert into complexity_matrix values (17,'EI',3,-1,5,15,2);
insert into complexity_matrix values (18,'EI',3,-1,16,-1,2);
insert into complexity_matrix values (19,'EOEQ',0,1,1,5,0);

insert into complexity_matrix values (20,'EOEQ',0,1,6,19,0);
insert into complexity_matrix values (21,'EOEQ',0,1,20,-1,1);
insert into complexity_matrix values (22,'EOEQ',2,3,1,5,0);
insert into complexity_matrix values (23,'EOEQ',2,3,6,19,1);
insert into complexity_matrix values (24,'EOEQ',2,3,20,-1,2);
insert into complexity_matrix values (25,'EOEQ',4,-1,1,5,1);

insert into complexity_matrix values (26,'EOEQ',4,-1,6,19,2);
insert into complexity_matrix values (27,'EOEQ',4,-1,10,-1,2);



-- drop table cplx_translation;

create table cplx_translation (
  ID Integer,
  type varchar (30),
  complexity integer,
  UFP integer,
  PRIMARY KEY (ID)
);


insert into cplx_translation values (0,'ILF',0,7);
insert into cplx_translation values (1,'ILF',1,10);
insert into cplx_translation values (2,'ILF',2,15);
insert into cplx_translation values (3,'EIF',0,5);


insert into cplx_translation values (4,'EIF',1,7);
insert into cplx_translation values (5,'EIF',2,10);
insert into cplx_translation values (6,'EIEQ',0,3);
insert into cplx_translation values (7,'EIEQ',1,4);
insert into cplx_translation values (8,'EIEQ',2,6);
insert into cplx_translation values (9,'EO',0,4);
insert into cplx_translation values (10,'EO',1,5);
insert into cplx_translation values (11,'EO',2,7);





create table projects (
ID Integer AUTO_INCREMENT ,
Description Varchar (150),
Productivity DECIMAL NOT NULL DEFAULT 0,
PRIMARY KEY (ID)
);


create table functionality (
ID Integer AUTO_INCREMENT,
Description Varchar (50),
idProject integer,
type varchar(50),
hcount integer,
vcount integer,
Section varchar(100),
PRIMARY KEY (ID),
FOREIGN KEY (idProject) REFERENCES projects(ID)
);

INSERT INTO 'fpa'.'projects' ('ID', 'Description') VALUES ('1', 'ProjectTest');

INSERT INTO 'fpa'.' functionality' ('ID', 'Description', 'idProject', 'type', 'hcount', 'vcount', 'projects_id') VALUES ('1', 'Funct1', '1', 'ILF', '2', '3', '1');




CREATE TABLE adjustment_factor (
  id_project int(11) NOT NULL,
  id_question int(11) NOT NULL,
  response int(11) DEFAULT NULL,
  PRIMARY KEY (id_project, id_question),
  FOREIGN KEY (id_project) REFERENCES projects(ID)
) ;


