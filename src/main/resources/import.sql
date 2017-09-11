-- Inserir permissões
INSERT INTO permissao VALUES (1, 'ADMINISTRADOR');
INSERT INTO permissao VALUES (3, 'CADASTRAR_GRUPO');
INSERT INTO permissao VALUES (4, 'CADASTRAR_MESA');
INSERT INTO permissao VALUES (5, 'CADASTRAR_PIZZA');
INSERT INTO permissao VALUES (6, 'CADASTRAR_PRODUTO');
INSERT INTO permissao VALUES (7, 'CADASTRAR_PROMOCAO');
INSERT INTO permissao VALUES (8, 'FINANCEIRO');

-- Inserir Grupo
INSERT INTO grupo (id, version, nome) VALUES (1, 0, 'Administrador');

-- Inserir usuario admin e vendedor
INSERT INTO usuario (version,  confirme_senha, senha, cpf, email, nome) VALUES (0, '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG', '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG','11169496440', 'admin@admin.com', 'Lucas Barros');

-- Usuario, Grupo e Permissao
INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (1, 1);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 1);