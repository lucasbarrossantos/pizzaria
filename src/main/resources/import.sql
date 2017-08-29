INSERT INTO grupo (id, nome) VALUES (1, 'Administrador');
INSERT INTO grupo (id, nome) VALUES (2, 'Vendedor');

-- Inserir usuario admin e vendedor

INSERT INTO usuario (id, confirme_senha, senha, cpf, email, nome) VALUES (1, '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG', '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG','11169496440', 'admin@admin.com', 'Lucas Barros');

INSERT INTO usuario (id, confirme_senha, senha, cpf, email, nome) VALUES (2, '$2a$10$uBvw5nohfGR6.0HelaC/X.TzKky.YFtVv4ZNXFc41wLeGMa1mXS9e', '$2a$10$uBvw5nohfGR6.0HelaC/X.TzKky.YFtVv4ZNXFc41wLeGMa1mXS9e' , '11169496440', 'vendas@vendas.com', 'Adriana Souza');

-- V04__inserir_permissoes_e_relacionar_usuario_admin


INSERT INTO permissao VALUES (1, 'ADMINISTRADOR');
INSERT INTO permissao VALUES (2, 'VENDEDOR');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 1);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 2);

INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (1, 1);

INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (2, 2);

--- Inserir permissões

INSERT INTO permissao(id, nome) VALUES(3,'CADASTRAR_GRUPO');
INSERT INTO permissao(id, nome) VALUES(4,'CADASTRAR_MESA');
INSERT INTO permissao(id, nome) VALUES(5,'CADASTRAR_PIZZA');
INSERT INTO permissao(id, nome) VALUES(6,'CADASTRAR_PRODUTO');
INSERT INTO permissao(id, nome) VALUES(7,'CADASTRAR_PROMOCAO');
INSERT INTO permissao(id, nome) VALUES(8,'FINANCEIRO');