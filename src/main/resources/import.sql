INSERT INTO grupo (id, version, nome) VALUES (1,0,'Administrador');
INSERT INTO grupo (id, version, nome) VALUES (2,0, 'Vendedor');

-- Inserir usuario admin e vendedor

INSERT INTO usuario (id, version,  confirme_senha, senha, cpf, email, nome) VALUES (1, 0, '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG', '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG','11169496440', 'admin@admin.com', 'Lucas Barros');

INSERT INTO usuario (id, version, confirme_senha, senha, cpf, email, nome) VALUES (2, 0, '$2a$10$uBvw5nohfGR6.0HelaC/X.TzKky.YFtVv4ZNXFc41wLeGMa1mXS9e', '$2a$10$uBvw5nohfGR6.0HelaC/X.TzKky.YFtVv4ZNXFc41wLeGMa1mXS9e' , '11169496440', 'vendas@vendas.com', 'Adriana Souza');

-- V04__inserir_permissoes_e_relacionar_usuario_admin

--- Inserir permissões
INSERT INTO permissao VALUES (1, 0, 'ADMINISTRADOR');
INSERT INTO permissao VALUES (2, 0, 'VENDEDOR');
INSERT INTO permissao VALUES (3, 0, 'CADASTRAR_GRUPO');
INSERT INTO permissao VALUES (4, 0,'CADASTRAR_MESA');
INSERT INTO permissao VALUES (5, 0, 'CADASTRAR_PIZZA');
INSERT INTO permissao VALUES (6, 0, 'CADASTRAR_PRODUTO');
INSERT INTO permissao VALUES (7, 0, 'CADASTRAR_PROMOCAO');
INSERT INTO permissao VALUES (8, 0, 'FINANCEIRO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 1);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 2);

INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (1, 1);

INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (2, 2);