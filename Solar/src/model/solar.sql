 DROP DATABASE IF EXISTS solar;
CREATE DATABASE IF not EXISTS solar;
USE solar;

CREATE TABLE IF NOT EXISTS colaborador (
    login 		varchar(20) NOT NULL,
	nome 		varchar(60) DEFAULT NULL,
	cpf 		varchar(25) DEFAULT NULL,
    sexo 		char(1) DEFAULT NULL,
	tel 		varchar(20) DEFAULT NULL,
    email 		varchar(50) DEFAULT NULL,
    senha 		varchar(20) DEFAULT NULL,
	PRIMARY KEY (login)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS setor (
  id     SMALLINT UNSIGNED NOT NULL,
  nome   VARCHAR(60) DEFAULT NULL,
  fone   CHAR(10) DEFAULT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS avaliador (
	login 			varchar(20) NOT NULL,
    setor_id 		SMALLINT UNSIGNED NOT NULL,
	nome 			varchar(60) DEFAULT NULL,
    senha 			varchar(20) DEFAULT NULL,
	PRIMARY KEY (login),
 	KEY fk_avaliador_setor_idx (setor_id),
 	CONSTRAINT fk_avaliador_setor FOREIGN KEY (setor_id) REFERENCES setor(id)  
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS sugestao (
	id 					int(5) NOT NULL auto_increment,
	titulo 				varchar(20) DEFAULT NULL,
	setor 				varchar(20) DEFAULT NULL,
    colaborador_login 	varchar(20) DEFAULT NULL,
    avaliador_login 	varchar(20) DEFAULT NULL,
    descricao 			varchar(200) DEFAULT NULL,
    id_setor			SMALLINT UNSIGNED NOT NULL,
    aprovado			varchar(2) DEFAULT NULL,
    PRIMARY KEY (id),
		INDEX fk_sugestao_colaborador1_loginx (colaborador_login ASC),
		INDEX fk_sugestao_avaliador1_idx (avaliador_login ASC),
        INDEX fk_sugestao_setorx(id_setor ASC),
	CONSTRAINT fk_sugestao_colaborador1 FOREIGN KEY (colaborador_login) REFERENCES colaborador(login),
    CONSTRAINT fk_sugestao_avaliador1 FOREIGN KEY (avaliador_login) REFERENCES avaliador(login),
    CONSTRAINT fk_sugestao_setor FOREIGN KEY (id_setor) REFERENCES setor(id)
)ENGINE = InnoDB;

INSERT INTO setor VALUES 
(1,'Recursos Humanos','1122222222'),
(2,'Patrimonio','1122222223'),
(3,'Bolsas','1122222224'),
(4,'Reitoria','1122222225'),
(5,'Coordenação','1122222226'),
(6,'Danos','1122222227');

INSERT INTO colaborador VALUES
('asd','lucas','413.444.567-41','M','(11)3244-3344','lucas@hotmail.com','asd');

INSERT INTO colaborador VALUES
('zxc','roger','123.456.321-99','M','(11)5467-1423','roger@hotmail.com','rty');

INSERT INTO avaliador VALUES
('qwe','1','lucas','qwe');

INSERT INTO avaliador VALUES
('abc','3','jose','asd');
 
 INSERT INTO sugestao VALUES
 ('1','Reclamação','Recursos Humanos','asd','qwe','Atendemento foi horrível.','1','S');
 INSERT INTO sugestao VALUES
 ('2','Aquisição de Bolsa','Bolsa','zxc','abc','Quais são os requisitos para conseguir uma bolsa da faculdade.','3','S');
 
SELECT *
from colaborador;	

SELECT *
from avaliador;

SELECT *
from setor;

SELECT *
from sugestao;



