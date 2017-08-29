INSERT INTO grupo (id, nome) VALUES (1, 'Administrador');
INSERT INTO grupo (id, nome) VALUES (2, 'Vendedor');

-- Inserir usuario admin e vendedor

INSERT INTO usuario (confirme_senha, senha, cpf, email, nome)
VALUES ('$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG',
        '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG'
  , '11169496440', 'admin@admin.com', 'Lucas Barros');

INSERT INTO usuario (confirme_senha, senha, cpf, email, nome)
VALUES ('$2a$10$uBvw5nohfGR6.0HelaC/X.TzKky.YFtVv4ZNXFc41wLeGMa1mXS9e',
        '$2a$10$uBvw5nohfGR6.0HelaC/X.TzKky.YFtVv4ZNXFc41wLeGMa1mXS9e'
  , '11169496440', 'vendas@vendas.com', 'Adriana Souza');

-- V04__inserir_permissoes_e_relacionar_usuario_admin


INSERT INTO permissao VALUES (1, 'ADMINISTRADOR');
INSERT INTO permissao VALUES (2, 'VENDEDOR');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 1);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 2);

INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (
  (SELECT id
   FROM usuario
   WHERE email = 'admin@admin.com'), 1);

INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (
  (SELECT id
   FROM usuario
   WHERE email = 'vendas@vendas.com'), 2);

--- Inserir permissões

INSERT INTO permissao(nome) VALUES('CADASTRAR_GRUPO');
INSERT INTO permissao(nome) VALUES('CADASTRAR_MESA');
INSERT INTO permissao(nome) VALUES('CADASTRAR_PIZZA');
INSERT INTO permissao(nome) VALUES('CADASTRAR_PRODUTO');
INSERT INTO permissao(nome) VALUES('CADASTRAR_PROMOCAO');
INSERT INTO permissao(nome) VALUES('FINANCEIRO');