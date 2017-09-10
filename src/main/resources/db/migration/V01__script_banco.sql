CREATE TABLE estabelecimento
(
  id                  BIGSERIAL   NOT NULL
    CONSTRAINT estabelecimento_pkey
    PRIMARY KEY,
  version             INTEGER,
  cnpj                VARCHAR(20) NOT NULL,
  descricao           VARCHAR(255),
  bairro              VARCHAR(60),
  cep                 VARCHAR(10),
  cidade              VARCHAR(60),
  complemento         VARCHAR(40),
  endereco            VARCHAR(60),
  estado              VARCHAR(60),
  inscricao_estadual  VARCHAR(40),
  inscricao_municipal VARCHAR(60),
  nome_fantasia       VARCHAR(90) NOT NULL,
  razao_social        VARCHAR(90) NOT NULL
);

CREATE TABLE fornecedor
(
  id                  BIGSERIAL   NOT NULL
    CONSTRAINT fornecedor_pkey
    PRIMARY KEY,
  version             INTEGER,
  cnpj                VARCHAR(20) NOT NULL,
  bairro              VARCHAR(60),
  cep                 VARCHAR(10),
  cidade              VARCHAR(60),
  complemento         VARCHAR(40),
  endereco            VARCHAR(60),
  estado              VARCHAR(60),
  inscricao_estadual  VARCHAR(60),
  inscricao_municipal VARCHAR(60),
  nome_fantasia       VARCHAR(60) NOT NULL,
  razao_social        VARCHAR(60) NOT NULL
);

CREATE TABLE grupo
(
  id      BIGSERIAL    NOT NULL
    CONSTRAINT grupo_pkey
    PRIMARY KEY,
  version INTEGER,
  nome    VARCHAR(255) NOT NULL
);

CREATE TABLE grupo_permissao
(
  codigo_grupo     BIGINT NOT NULL
    CONSTRAINT fkh1lvrl72de4u5xhr1u3jvo0rq
    REFERENCES grupo,
  codigo_permissao BIGINT NOT NULL
);

CREATE TABLE item_pedido
(
  id             BIGSERIAL NOT NULL
    CONSTRAINT item_pedido_pkey
    PRIMARY KEY,
  version        INTEGER,
  quantidade     INTEGER,
  valor_unitario NUMERIC(19, 2),
  pedido_id      BIGINT,
  pizza_id       BIGINT,
  produto_id     BIGINT
);

CREATE TABLE mesa
(
  id            BIGSERIAL    NOT NULL
    CONSTRAINT mesa_pkey
    PRIMARY KEY,
  version       INTEGER,
  hora_cadastro DATE,
  numero        VARCHAR(255) NOT NULL,
  observacao    VARCHAR(255),
  status        VARCHAR(255),
  valor_itens   NUMERIC(19, 2),
  pedido_id     BIGINT
);

CREATE TABLE pedido
(
  id             BIGSERIAL NOT NULL
    CONSTRAINT pedido_pkey
    PRIMARY KEY,
  version        INTEGER,
  data_pedido    DATE,
  observacao     VARCHAR(255),
  status         VARCHAR(255),
  valor_total    NUMERIC(19, 2),
  codigo_usuario BIGINT
);

ALTER TABLE item_pedido
  ADD CONSTRAINT fk60ym08cfoysa17wrn1swyiuda
FOREIGN KEY (pedido_id) REFERENCES pedido;

ALTER TABLE mesa
  ADD CONSTRAINT fkp5sleibng9e8a1gmubicsgrd0
FOREIGN KEY (pedido_id) REFERENCES pedido;

CREATE TABLE permissao
(
  id   BIGSERIAL    NOT NULL
    CONSTRAINT permissao_pkey
    PRIMARY KEY,
  nome VARCHAR(255) NOT NULL
);

ALTER TABLE grupo_permissao
  ADD CONSTRAINT fkfp14wb9mt832y4jlw2rx3pf6p
FOREIGN KEY (codigo_permissao) REFERENCES permissao;

CREATE TABLE pizza
(
  id             BIGSERIAL      NOT NULL
    CONSTRAINT pizza_pkey
    PRIMARY KEY,
  version        INTEGER,
  adicional      VARCHAR(255),
  borda          VARCHAR(255),
  content_type   VARCHAR(255),
  descricao      VARCHAR(60)    NOT NULL,
  foto           VARCHAR(255),
  sabor_pizza    VARCHAR(90),
  tamanho        VARCHAR(255)   NOT NULL,
  valor_unitario NUMERIC(19, 2) NOT NULL,
  promocao_id    BIGINT
);

ALTER TABLE item_pedido
  ADD CONSTRAINT fk3939rp2uspsqd724arit4kl86
FOREIGN KEY (pizza_id) REFERENCES pizza;

CREATE TABLE pizza_sabor
(
  codigo_pizza BIGINT NOT NULL
    CONSTRAINT fkefmg2elscewjc6owo50bevwvk
    REFERENCES pizza,
  codigo_sabor BIGINT NOT NULL
);

CREATE TABLE produto
(
  id                 BIGSERIAL      NOT NULL
    CONSTRAINT produto_pkey
    PRIMARY KEY,
  version            INTEGER,
  caracteristicas    VARCHAR(255),
  descricao          VARCHAR(100)   NOT NULL,
  quantidade_estoque INTEGER        NOT NULL
    CONSTRAINT produto_quantidade_estoque_check
    CHECK (quantidade_estoque <= 9999),
  sku                VARCHAR(20)    NOT NULL
    CONSTRAINT uk_j6npst3feop938l4x5h675kyv
    UNIQUE,
  unidade            VARCHAR(60),
  valor_compra       NUMERIC(19, 2) NOT NULL,
  valor_unitario     NUMERIC(19, 2) NOT NULL,
  categoria_id       BIGINT         NOT NULL,
  estabelecimento_id BIGINT
    CONSTRAINT fksd4xk0na2qggce5khx0mqujl8
    REFERENCES estabelecimento
);

ALTER TABLE item_pedido
  ADD CONSTRAINT fktk55mn6d6bvl5h0no5uagi3sf
FOREIGN KEY (produto_id) REFERENCES produto;

CREATE TABLE promocao
(
  id                 BIGSERIAL      NOT NULL
    CONSTRAINT promocao_pkey
    PRIMARY KEY,
  version            INTEGER,
  descricao          VARCHAR(90)    NOT NULL,
  valor              NUMERIC(19, 2) NOT NULL,
  estabelecimento_id BIGINT
    CONSTRAINT fkbj4dljhk2tx0yhc7ugqhufaal
    REFERENCES estabelecimento
);

ALTER TABLE pizza
  ADD CONSTRAINT fkcvpvak77xu52tg495n4ndbn97
FOREIGN KEY (promocao_id) REFERENCES promocao;

CREATE TABLE sabor
(
  id        BIGSERIAL NOT NULL
    CONSTRAINT sabor_pkey
    PRIMARY KEY,
  version   INTEGER,
  descricao VARCHAR(60)
);

ALTER TABLE pizza_sabor
  ADD CONSTRAINT fkfc6o83iwklosbviuw969bk9al
FOREIGN KEY (codigo_sabor) REFERENCES sabor;

CREATE TABLE tipo_produto
(
  id        BIGSERIAL    NOT NULL
    CONSTRAINT tipo_produto_pkey
    PRIMARY KEY,
  version   INTEGER,
  descricao VARCHAR(100) NOT NULL
);

ALTER TABLE produto
  ADD CONSTRAINT fkgqq1rrte5xp1r7v61wkiptq3x
FOREIGN KEY (categoria_id) REFERENCES tipo_produto;

CREATE TABLE titulo
(
  id                 BIGSERIAL      NOT NULL
    CONSTRAINT titulo_pkey
    PRIMARY KEY,
  version            INTEGER,
  centro_de_custo    VARCHAR(255)   NOT NULL,
  data_de_emissao    DATE,
  data_de_validade   DATE,
  data_do_pagamento  DATE,
  descricao          VARCHAR(255)   NOT NULL,
  forma_de_pagamento VARCHAR(255),
  situacao           VARCHAR(40)    NOT NULL,
  tipo               VARCHAR(40)    NOT NULL,
  valor              NUMERIC(19, 2) NOT NULL,
  valor_original     NUMERIC(19, 2),
  valor_pago         NUMERIC(19, 2),
  estabelecimento_id BIGINT
    CONSTRAINT fk3hqlxi2alpaqoru1vwsx8l0e6
    REFERENCES estabelecimento,
  fornecedor_id      BIGINT
    CONSTRAINT fkm4btffwmv6kmpk1l1r32abkfv
    REFERENCES fornecedor
);

CREATE TABLE usuario
(
  id                 BIGSERIAL    NOT NULL
    CONSTRAINT usuario_pkey
    PRIMARY KEY,
  version            INTEGER,
  confirme_senha     VARCHAR(100) NOT NULL,
  cpf                VARCHAR(14)  NOT NULL,
  data_nascimento    DATE,
  email              VARCHAR(60),
  bairro             VARCHAR(60),
  cep                VARCHAR(10),
  cidade             VARCHAR(60),
  complemento        VARCHAR(40),
  endereco           VARCHAR(60),
  estado             VARCHAR(60),
  nome               VARCHAR(60),
  senha              VARCHAR(100) NOT NULL,
  telefone           VARCHAR(20),
  estabelecimento_id BIGINT
    CONSTRAINT fkpwqxybcq1mx2wkbm1aojirast
    REFERENCES estabelecimento
);

ALTER TABLE pedido
  ADD CONSTRAINT fkmqlda2hhcolmusws35fq3ki6q
FOREIGN KEY (codigo_usuario) REFERENCES usuario;

CREATE TABLE usuario_grupo
(
  codigo_usuario BIGINT NOT NULL
    CONSTRAINT fkcx5f61jsftmpnlu4ec8fyndg3
    REFERENCES usuario,
  codigo_grupo   BIGINT NOT NULL
    CONSTRAINT fk4yweq9u2sokki6o060mejfw8r
    REFERENCES grupo
);

