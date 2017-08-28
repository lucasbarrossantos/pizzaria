INSERT INTO permissao VALUES (1, 'CADASTRAR_FORNECEDOR');
INSERT INTO permissao VALUES (2, 'CADASTRAR_USUARIO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 1);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 2);

INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (
  (SELECT id FROM usuario WHERE email = 'admin@admin.com'), 1);