CREATE TABLE estabelecimento
(
  id                  BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version             INT          NULL,
  cnpj                VARCHAR(20)  NOT NULL,
  descricao           VARCHAR(255) NULL,
  bairro              VARCHAR(60)  NULL,
  cep                 VARCHAR(10)  NULL,
  cidade              VARCHAR(60)  NULL,
  complemento         VARCHAR(40)  NULL,
  endereco            VARCHAR(60)  NULL,
  estado              VARCHAR(60)  NULL,
  inscricao_estadual  VARCHAR(40)  NULL,
  inscricao_municipal VARCHAR(60)  NULL,
  nome_fantasia       VARCHAR(90)  NOT NULL,
  razao_social        VARCHAR(90)  NOT NULL
);

CREATE TABLE fornecedor
(
  id                  BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version             INT         NULL,
  cnpj                VARCHAR(20) NOT NULL,
  bairro              VARCHAR(60) NULL,
  cep                 VARCHAR(10) NULL,
  cidade              VARCHAR(60) NULL,
  complemento         VARCHAR(40) NULL,
  endereco            VARCHAR(60) NULL,
  estado              VARCHAR(60) NULL,
  inscricao_estadual  VARCHAR(60) NULL,
  inscricao_municipal VARCHAR(60) NULL,
  nome_fantasia       VARCHAR(60) NOT NULL,
  razao_social        VARCHAR(60) NOT NULL
);

CREATE TABLE grupo
(
  id      BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version INT          NULL,
  nome    VARCHAR(255) NOT NULL
);

CREATE TABLE grupo_permissao
(
  codigo_grupo     BIGINT NOT NULL,
  codigo_permissao BIGINT NOT NULL,
  CONSTRAINT FKh1lvrl72de4u5xhr1u3jvo0rq
  FOREIGN KEY (codigo_grupo) REFERENCES grupo (id)
);

CREATE INDEX FKfp14wb9mt832y4jlw2rx3pf6p
  ON grupo_permissao (codigo_permissao);

CREATE INDEX FKh1lvrl72de4u5xhr1u3jvo0rq
  ON grupo_permissao (codigo_grupo);

CREATE TABLE item_pedido
(
  id             BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version        INT            NULL,
  quantidade     INT            NULL,
  valor_unitario DECIMAL(19, 2) NULL,
  pedido_id      BIGINT         NULL,
  pizza_id       BIGINT         NULL,
  produto_id     BIGINT         NULL
);

CREATE INDEX FK3939rp2uspsqd724arit4kl86
  ON item_pedido (pizza_id);

CREATE INDEX FK60ym08cfoysa17wrn1swyiuda
  ON item_pedido (pedido_id);

CREATE INDEX FKtk55mn6d6bvl5h0no5uagi3sf
  ON item_pedido (produto_id);

CREATE TABLE mesa
(
  id            BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version       INT            NULL,
  hora_cadastro DATE           NULL,
  numero        VARCHAR(255)   NOT NULL,
  observacao    VARCHAR(255)   NULL,
  status        VARCHAR(255)   NULL,
  valor_itens   DECIMAL(19, 2) NULL,
  pedido_id     BIGINT         NULL
);

CREATE INDEX FKp5sleibng9e8a1gmubicsgrd0
  ON mesa (pedido_id);

CREATE TABLE pedido
(
  id             BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version        INT            NULL,
  data_pedido    DATE           NULL,
  observacao     VARCHAR(255)   NULL,
  status         VARCHAR(255)   NULL,
  valor_total    DECIMAL(19, 2) NULL,
  codigo_usuario BIGINT         NULL
);

CREATE INDEX FKmqlda2hhcolmusws35fq3ki6q
  ON pedido (codigo_usuario);

ALTER TABLE item_pedido
  ADD CONSTRAINT FK60ym08cfoysa17wrn1swyiuda
FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE mesa
  ADD CONSTRAINT FKp5sleibng9e8a1gmubicsgrd0
FOREIGN KEY (pedido_id) REFERENCES pedido (id);

CREATE TABLE permissao
(
  id      BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version INT          NULL,
  nome    VARCHAR(255) NOT NULL
);

ALTER TABLE grupo_permissao
  ADD CONSTRAINT FKfp14wb9mt832y4jlw2rx3pf6p
FOREIGN KEY (codigo_permissao) REFERENCES permissao (id);

CREATE TABLE pizza
(
  id             BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version        INT            NULL,
  adicional      VARCHAR(255)   NULL,
  borda          VARCHAR(255)   NULL,
  content_type   VARCHAR(255)   NULL,
  descricao      VARCHAR(60)    NOT NULL,
  foto           VARCHAR(255)   NULL,
  sabor_pizza    VARCHAR(90)    NULL,
  tamanho        VARCHAR(255)   NOT NULL,
  valor_unitario DECIMAL(19, 2) NOT NULL,
  promocao_id    BIGINT         NULL
);

CREATE INDEX FKcvpvak77xu52tg495n4ndbn97
  ON pizza (promocao_id);

ALTER TABLE item_pedido
  ADD CONSTRAINT FK3939rp2uspsqd724arit4kl86
FOREIGN KEY (pizza_id) REFERENCES pizza (id);

CREATE TABLE pizza_sabor
(
  codigo_pizza BIGINT NOT NULL,
  codigo_sabor BIGINT NOT NULL,
  CONSTRAINT FKefmg2elscewjc6owo50bevwvk
  FOREIGN KEY (codigo_pizza) REFERENCES pizza (id)
);

CREATE INDEX FKefmg2elscewjc6owo50bevwvk
  ON pizza_sabor (codigo_pizza);

CREATE INDEX FKfc6o83iwklosbviuw969bk9al
  ON pizza_sabor (codigo_sabor);

CREATE TABLE produto
(
  id                 BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version            INT            NULL,
  caracteristicas    LONGTEXT       NULL,
  descricao          VARCHAR(100)   NOT NULL,
  quantidade_estoque INT            NOT NULL,
  sku                VARCHAR(20)    NOT NULL,
  unidade            VARCHAR(60)    NULL,
  valor_compra       DECIMAL(19, 2) NOT NULL,
  valor_unitario     DECIMAL(19, 2) NOT NULL,
  categoria_id       BIGINT         NOT NULL,
  estabelecimento_id BIGINT         NULL,
  CONSTRAINT UK_j6npst3feop938l4x5h675kyv
  UNIQUE (sku),
  CONSTRAINT FKsd4xk0na2qggce5khx0mqujl8
  FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimento (id)
);

CREATE INDEX FKgqq1rrte5xp1r7v61wkiptq3x
  ON produto (categoria_id);

CREATE INDEX FKsd4xk0na2qggce5khx0mqujl8
  ON produto (estabelecimento_id);

ALTER TABLE item_pedido
  ADD CONSTRAINT FKtk55mn6d6bvl5h0no5uagi3sf
FOREIGN KEY (produto_id) REFERENCES produto (id);

CREATE TABLE promocao
(
  id                 BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version            INT            NULL,
  descricao          VARCHAR(90)    NOT NULL,
  valor              DECIMAL(19, 2) NOT NULL,
  estabelecimento_id BIGINT         NULL,
  CONSTRAINT FKbj4dljhk2tx0yhc7ugqhufaal
  FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimento (id)
);

CREATE INDEX FKbj4dljhk2tx0yhc7ugqhufaal
  ON promocao (estabelecimento_id);

ALTER TABLE pizza
  ADD CONSTRAINT FKcvpvak77xu52tg495n4ndbn97
FOREIGN KEY (promocao_id) REFERENCES promocao (id);

CREATE TABLE sabor
(
  id        BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version   INT         NULL,
  descricao VARCHAR(60) NULL
);

ALTER TABLE pizza_sabor
  ADD CONSTRAINT FKfc6o83iwklosbviuw969bk9al
FOREIGN KEY (codigo_sabor) REFERENCES sabor (id);

CREATE TABLE tipo_produto
(
  id        BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version   INT          NULL,
  descricao VARCHAR(100) NOT NULL
);

ALTER TABLE produto
  ADD CONSTRAINT FKgqq1rrte5xp1r7v61wkiptq3x
FOREIGN KEY (categoria_id) REFERENCES tipo_produto (id);

CREATE TABLE titulo
(
  id                 BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version            INT            NULL,
  centro_de_custo    VARCHAR(255)   NOT NULL,
  data_de_emissao    DATE           NULL,
  data_de_validade   DATE           NULL,
  data_do_pagamento  DATE           NULL,
  descricao          VARCHAR(255)   NOT NULL,
  forma_de_pagamento VARCHAR(255)   NULL,
  situacao           VARCHAR(40)    NOT NULL,
  tipo               VARCHAR(40)    NOT NULL,
  valor              DECIMAL(19, 2) NOT NULL,
  valor_original     DECIMAL(19, 2) NULL,
  valor_pago         DECIMAL(19, 2) NULL,
  estabelecimento_id BIGINT         NULL,
  fornecedor_id      BIGINT         NULL,
  CONSTRAINT FK3hqlxi2alpaqoru1vwsx8l0e6
  FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimento (id),
  CONSTRAINT FKm4btffwmv6kmpk1l1r32abkfv
  FOREIGN KEY (fornecedor_id) REFERENCES fornecedor (id)
);

CREATE INDEX FK3hqlxi2alpaqoru1vwsx8l0e6
  ON titulo (estabelecimento_id);

CREATE INDEX FKm4btffwmv6kmpk1l1r32abkfv
  ON titulo (fornecedor_id);

CREATE TABLE usuario
(
  id                 BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  version            INT          NULL,
  confirme_senha     VARCHAR(100) NOT NULL,
  cpf                VARCHAR(14)  NOT NULL,
  data_nascimento    DATE         NULL,
  email              VARCHAR(60)  NULL,
  bairro             VARCHAR(60)  NULL,
  cep                VARCHAR(10)  NULL,
  cidade             VARCHAR(60)  NULL,
  complemento        VARCHAR(40)  NULL,
  endereco           VARCHAR(60)  NULL,
  estado             VARCHAR(60)  NULL,
  nome               VARCHAR(60)  NULL,
  senha              VARCHAR(100) NOT NULL,
  telefone           VARCHAR(20)  NULL,
  estabelecimento_id BIGINT       NULL,
  CONSTRAINT FKpwqxybcq1mx2wkbm1aojirast
  FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimento (id)
);

CREATE INDEX FKpwqxybcq1mx2wkbm1aojirast
  ON usuario (estabelecimento_id);

ALTER TABLE pedido
  ADD CONSTRAINT FKmqlda2hhcolmusws35fq3ki6q
FOREIGN KEY (codigo_usuario) REFERENCES usuario (id);

CREATE TABLE usuario_grupo
(
  codigo_usuario BIGINT NOT NULL,
  codigo_grupo   BIGINT NOT NULL,
  CONSTRAINT FKcx5f61jsftmpnlu4ec8fyndg3
  FOREIGN KEY (codigo_usuario) REFERENCES usuario (id),
  CONSTRAINT FK4yweq9u2sokki6o060mejfw8r
  FOREIGN KEY (codigo_grupo) REFERENCES grupo (id)
);

CREATE INDEX FK4yweq9u2sokki6o060mejfw8r
  ON usuario_grupo (codigo_grupo);

CREATE INDEX FKcx5f61jsftmpnlu4ec8fyndg3
  ON usuario_grupo (codigo_usuario);

