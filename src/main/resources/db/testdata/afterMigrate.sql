set foreign_key_checks = 0;

delete from foto_documento;
delete from cliente;
delete from proposta;
delete from usuario;
delete from conta;
delete from endereco;
delete from transferencia;
delete from bairro;
delete from cidade;
delete from estado;

set foreign_key_checks = 1;

alter table proposta auto_increment = 1;
alter table conta auto_increment = 1;
alter table endereco auto_increment = 1;
alter table transferencia auto_increment = 1;
alter table bairro auto_increment = 1;
alter table cidade auto_increment = 1;
alter table estado auto_increment = 1;

INSERT INTO `estado` VALUES (1,'Santa Catarina','SC'),(2,'São Paulo','SP'),(3,'Minas Gerais','MG');
INSERT INTO `cidade` VALUES (1,1,'Joinville'),(2,2,'São Paulo'),(3,2,'Campinas'),(4,3,'Uberlândia');
INSERT INTO `bairro` VALUES (1,1,'Centro'),(2,2,'Centro'),(3,3,'Centro');
INSERT INTO `cliente` VALUES ('54574842032','Edson','Junior','edsonmoreirajr@gmail.com','21891731372','1986-01-08',0);
