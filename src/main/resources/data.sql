INSERT INTO cargo (id,nome) VALUES (1,'Analista');
INSERT INTO cargo (id,nome) VALUES (2,'Contador');
INSERT INTO cargo (id,nome) VALUES (3,'Diretor');
INSERT INTO cargo (id,nome) VALUES (4,'Gerente');
INSERT INTO cargo (id,nome) VALUES (5,'Secretária');
INSERT INTO cargo (id,nome) VALUES (6,'Zelador');

INSERT INTO perfil (id,nome) VALUES (1,'USER');
INSERT INTO perfil (id,nome) VALUES (2,'ADMIN');
INSERT INTO perfil (id,nome) VALUES (3,'SUPER_USER');

INSERT INTO usuario (id, nome, cpf, sexo, data_nascimento, cargo_id, data_cadastro)
VALUES (1, 'João Ferreira',  '013.456.780-37', 'M', '1970-03-14', 1, '2020-11-20');
INSERT INTO usuario (id, nome, cpf, sexo, data_nascimento, cargo_id, data_cadastro)
VALUES(2, 'João Miranda',   '797.129.400-04', 'M', '1977-01-22', 2, '2020-11-20');
INSERT INTO usuario (id, nome, cpf, sexo, data_nascimento, cargo_id, data_cadastro)
VALUES(3, 'Antonio Silva',  '427.875.500-70', 'M', '1983-02-03', 3, '2020-11-20');
INSERT INTO usuario (id, nome, cpf, sexo, data_nascimento, cargo_id, data_cadastro)
VALUES(4, 'Luiz Ferreira',  '133.692.350-48', 'M', '1990-03-17', 4, '2020-11-20');
INSERT INTO usuario (id, nome, cpf, sexo, data_nascimento, cargo_id, data_cadastro)
VALUES(5, 'José Almeida',   '866.410.250-39', 'M', '1974-04-11', 5, '2020-11-20');
INSERT INTO usuario (id, nome, cpf, sexo, data_nascimento, cargo_id, data_cadastro)
VALUES(6, 'Maria Ramos',    '599.476.560-04', 'F', '1981-05-12', 1, '2020-11-20');
INSERT INTO usuario (id, nome, cpf, sexo, data_nascimento, cargo_id, data_cadastro)
VALUES(7, 'Amanda Nunes',   '576.234.410-00', 'F', '1970-08-24', 2, '2020-11-20');
INSERT INTO usuario (id, nome, cpf, sexo, data_nascimento, cargo_id, data_cadastro)
VALUES(8, 'Fernanda Abreu', '205.556.070-03', 'F', '1971-09-25', 3, '2020-11-20');
INSERT INTO usuario (id, nome, cpf, sexo, data_nascimento, cargo_id, data_cadastro)
VALUES(9, 'Renata Borges',  '490.166.070-56', 'F', '1992-02-19', 4, '2020-11-20');
INSERT INTO usuario (id, nome, cpf, sexo, data_nascimento, cargo_id, data_cadastro)
VALUES(10,'Joana Fagundes', '148.174.030-04', 'F', '1995-12-08', 5, '2020-11-20');

INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (1,1);
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (1,2);
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (2,2);
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (2,3);
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (3,1);
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (4,2);
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (5,3);
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (6,1);
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (7,2);
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (8,1);
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (9,3);
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (10,2);
