-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: pizzaria
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table estabelecimento
--

DROP TABLE IF EXISTS estabelecimento;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE estabelecimento (
  id SERIAL NOT NULL,
  cnpj varchar(20) NOT NULL,
  descricao varchar(255) DEFAULT NULL,
  bairro varchar(60) DEFAULT NULL,
  cep varchar(10) DEFAULT NULL,
  cidade varchar(60) DEFAULT NULL,
  complemento varchar(40) DEFAULT NULL,
  endereco varchar(60) DEFAULT NULL,
  estado varchar(60) DEFAULT NULL,
  inscricao_estadual varchar(40) DEFAULT NULL,
  inscricao_municipal varchar(60) DEFAULT NULL,
  nome_fantasia varchar(90) NOT NULL,
  razao_social varchar(90) NOT NULL,
  PRIMARY KEY (id)
) 
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table estabelecimento
--

--
-- Table structure for table fornecedor
--

DROP TABLE IF EXISTS fornecedor;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE fornecedor (
  id SERIAL NOT NULL ,
  cnpj varchar(20) NOT NULL,
  bairro varchar(60) DEFAULT NULL,
  cep varchar(10) DEFAULT NULL,
  cidade varchar(60) DEFAULT NULL,
  complemento varchar(40) DEFAULT NULL,
  endereco varchar(60) DEFAULT NULL,
  estado varchar(60) DEFAULT NULL,
  inscricao_estadual varchar(60) DEFAULT NULL,
  inscricao_municipal varchar(60) DEFAULT NULL,
  nome_fantasia varchar(60) NOT NULL,
  razao_social varchar(60) NOT NULL,
  PRIMARY KEY (id)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table fornecedor
--

--
-- Table structure for table grupo
--

DROP TABLE IF EXISTS grupo;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE grupo (
  id SERIAL NOT NULL ,
  nome varchar(255) NOT NULL,
  PRIMARY KEY (id)
);
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS permissao;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE permissao (
  id SERIAL NOT NULL ,
  nome varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ;

DROP TABLE IF EXISTS grupo_permissao;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE grupo_permissao (
  codigo_grupo INT NOT NULL,
  codigo_permissao INT NOT NULL,

  FOREIGN KEY (codigo_permissao) REFERENCES permissao (id),
  FOREIGN KEY (codigo_grupo) REFERENCES grupo (id)
) ;

DROP TABLE IF EXISTS mesa;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE mesa (
  id SERIAL NOT NULL ,
  hora_cadastro date DEFAULT NULL,
  numero varchar(255) NOT NULL,
  observacao varchar(255) DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  valor_itens decimal(19,2) DEFAULT NULL,
  pedido_id SERIAL DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (pedido_id) REFERENCES pedido (id)
) ;

DROP TABLE IF EXISTS pizza;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE pizza (
  id SERIAL NOT NULL ,
  adicional varchar(255) DEFAULT NULL,
  borda varchar(255) DEFAULT NULL,
  content_type varchar(255) DEFAULT NULL,
  descricao varchar(60) NOT NULL,
  foto varchar(255) DEFAULT NULL,
  sabor_pizza varchar(90) DEFAULT NULL,
  tamanho varchar(255) NOT NULL,
  valor_unitario decimal(19,2) NOT NULL,
  promocao_id SERIAL DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (promocao_id) REFERENCES promocao (id)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS sabor;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE sabor (
  id SERIAL NOT NULL ,
  descricao varchar(60) DEFAULT NULL,
  PRIMARY KEY (id)
) ;

DROP TABLE IF EXISTS pizza_sabor;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE pizza_sabor (
  codigo_pizza INT NOT NULL,
  codigo_sabor INT NOT NULL,
  FOREIGN KEY (codigo_pizza) REFERENCES pizza (id),
  FOREIGN KEY (codigo_sabor) REFERENCES sabor (id)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table pizza_sabor
--
--
-- Table structure for table produto
--

DROP TABLE IF EXISTS produto;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE produto (
  id SERIAL NOT NULL ,
  caracteristicas VARCHAR(100),
  descricao varchar(100) NOT NULL,
  quantidade_estoque INT NOT NULL,
  sku varchar(20) NOT NULL,
  unidade varchar(60) DEFAULT NULL,
  valor_compra decimal(19,2) NOT NULL,
  valor_unitario decimal(19,2) NOT NULL,
  categoria_id SERIAL NOT NULL,
  estabelecimento_id SERIAL DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE (sku),
  FOREIGN KEY (categoria_id) REFERENCES tipo_produto (id),
  FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimento (id)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table produto
--


--
-- Table structure for table promocao
--

DROP TABLE IF EXISTS promocao;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE promocao (
  id SERIAL NOT NULL ,
  descricao varchar(90) NOT NULL,
  valor decimal(19,2) NOT NULL,
  estabelecimento_id SERIAL DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimento (id)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS tipo_produto;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipo_produto (
  id SERIAL NOT NULL ,
  descricao varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table tipo_produto
--
--
-- Table structure for table titulo
--

DROP TABLE IF EXISTS titulo;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE titulo (
  id SERIAL NOT NULL ,
  centro_de_custo varchar(255) NOT NULL,
  data_de_emissao date DEFAULT NULL,
  data_de_validade date DEFAULT NULL,
  data_do_pagamento date DEFAULT NULL,
  descricao varchar(255) NOT NULL,
  forma_de_pagamento varchar(255) DEFAULT NULL,
  situacao varchar(40) NOT NULL,
  tipo varchar(40) NOT NULL,
  valor decimal(19,2) NOT NULL,
  valor_original decimal(19,2) DEFAULT NULL,
  valor_pago decimal(19,2) DEFAULT NULL,
  estabelecimento_id SERIAL DEFAULT NULL,
  fornecedor_id SERIAL DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimento (id),
  FOREIGN KEY (fornecedor_id) REFERENCES fornecedor (id)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table titulo
--

--
-- Table structure for table usuario
--

DROP TABLE IF EXISTS usuario;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE usuario (
  id SERIAL NOT NULL ,
  confirme_senha varchar(100) NOT NULL,
  cpf varchar(14) NOT NULL,
  data_nascimento date DEFAULT NULL,
  email varchar(60) DEFAULT NULL,
  bairro varchar(60) DEFAULT NULL,
  cep varchar(10) DEFAULT NULL,
  cidade varchar(60) DEFAULT NULL,
  complemento varchar(40) DEFAULT NULL,
  endereco varchar(60) DEFAULT NULL,
  estado varchar(60) DEFAULT NULL,
  nome varchar(60) DEFAULT NULL,
  senha varchar(100) NOT NULL,
  telefone varchar(20) DEFAULT NULL,
  estabelecimento_id SERIAL DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimento (id)
);

DROP TABLE IF EXISTS pedido;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE pedido (
  id SERIAL NOT NULL ,
  data_pedido date DEFAULT NULL,
  observacao varchar(255) DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  valor_total decimal(19,2) DEFAULT NULL,
  codigo_usuario INT DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (codigo_usuario) REFERENCES usuario (id)
) ;

DROP TABLE IF EXISTS usuario_grupo;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE usuario_grupo (
  codigo_usuario INT NOT NULL,
  codigo_grupo INT NOT NULL,
  FOREIGN KEY (codigo_grupo) REFERENCES grupo (id),
  FOREIGN KEY (codigo_usuario) REFERENCES usuario (id)
) ;

DROP TABLE IF EXISTS item_pedido;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE item_pedido (
  id SERIAL NOT NULL ,
  quantidade INT DEFAULT NULL,
  valor_unitario decimal(19,2) DEFAULT NULL,
  pedido_id SERIAL DEFAULT NULL,
  pizza_id SERIAL DEFAULT NULL,
  produto_id SERIAL DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (pizza_id) REFERENCES pizza (id),
  FOREIGN KEY (pedido_id) REFERENCES pedido (id),
  FOREIGN KEY (produto_id) REFERENCES produto (id)
) ;
