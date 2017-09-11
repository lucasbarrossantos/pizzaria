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


-- Pedidos

INSERT INTO pedido(id, version, data_pedido, status, valor_total, codigo_usuario) VALUES (1, 0, '2017-03-29',  'CONCLUIDO', 600.00, 1)
INSERT INTO pedido(id, version, data_pedido, status, valor_total, codigo_usuario) VALUES (2, 0, '2017-04-28',  'CONCLUIDO', 1200.00, 1)
INSERT INTO pedido(id, version, data_pedido, status, valor_total, codigo_usuario) VALUES (3, 0, '2017-05-20',  'CONCLUIDO', 900.00, 1)
INSERT INTO pedido(id, version, data_pedido, status, valor_total, codigo_usuario) VALUES (4, 0, '2017-06-15',  'CONCLUIDO', 400.00, 1)
INSERT INTO pedido(id, version, data_pedido, status, valor_total, codigo_usuario) VALUES (5, 0, '2017-07-20',  'CONCLUIDO', 700.00, 1)
INSERT INTO pedido(id, version, data_pedido, status, valor_total, codigo_usuario) VALUES (6, 0, '2017-09-25',  'CONCLUIDO', 300.00, 1)