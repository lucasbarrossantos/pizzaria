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
-- Table structure for table `estabelecimento`
--

DROP TABLE IF EXISTS `estabelecimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estabelecimento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `bairro` varchar(60) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `cidade` varchar(60) DEFAULT NULL,
  `complemento` varchar(40) DEFAULT NULL,
  `endereco` varchar(60) DEFAULT NULL,
  `estado` varchar(60) DEFAULT NULL,
  `inscricao_estadual` varchar(40) DEFAULT NULL,
  `inscricao_municipal` varchar(60) DEFAULT NULL,
  `nome_fantasia` varchar(90) NOT NULL,
  `razao_social` varchar(90) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estabelecimento`
--

LOCK TABLES `estabelecimento` WRITE;
/*!40000 ALTER TABLE `estabelecimento` DISABLE KEYS */;
/*!40000 ALTER TABLE `estabelecimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(20) NOT NULL,
  `bairro` varchar(60) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `cidade` varchar(60) DEFAULT NULL,
  `complemento` varchar(40) DEFAULT NULL,
  `endereco` varchar(60) DEFAULT NULL,
  `estado` varchar(60) DEFAULT NULL,
  `inscricao_estadual` varchar(60) DEFAULT NULL,
  `inscricao_municipal` varchar(60) DEFAULT NULL,
  `nome_fantasia` varchar(60) NOT NULL,
  `razao_social` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

--
-- Table structure for table `grupo_permissao`
--

DROP TABLE IF EXISTS `grupo_permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo_permissao` (
  `codigo_grupo` bigint(20) NOT NULL,
  `codigo_permissao` bigint(20) NOT NULL,
  KEY `FKfp14wb9mt832y4jlw2rx3pf6p` (`codigo_permissao`),
  KEY `FKh1lvrl72de4u5xhr1u3jvo0rq` (`codigo_grupo`),
  CONSTRAINT `FKfp14wb9mt832y4jlw2rx3pf6p` FOREIGN KEY (`codigo_permissao`) REFERENCES `permissao` (`id`),
  CONSTRAINT `FKh1lvrl72de4u5xhr1u3jvo0rq` FOREIGN KEY (`codigo_grupo`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_permissao`
--

--
-- Table structure for table `item_pedido`
--

DROP TABLE IF EXISTS `item_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_pedido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) DEFAULT NULL,
  `valor_unitario` decimal(19,2) DEFAULT NULL,
  `pedido_id` bigint(20) DEFAULT NULL,
  `pizza_id` bigint(20) DEFAULT NULL,
  `produto_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK60ym08cfoysa17wrn1swyiuda` (`pedido_id`),
  KEY `FK3939rp2uspsqd724arit4kl86` (`pizza_id`),
  KEY `FKtk55mn6d6bvl5h0no5uagi3sf` (`produto_id`),
  CONSTRAINT `FK3939rp2uspsqd724arit4kl86` FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`id`),
  CONSTRAINT `FK60ym08cfoysa17wrn1swyiuda` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FKtk55mn6d6bvl5h0no5uagi3sf` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_pedido`
--

LOCK TABLES `item_pedido` WRITE;
/*!40000 ALTER TABLE `item_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesa`
--

DROP TABLE IF EXISTS `mesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hora_cadastro` date DEFAULT NULL,
  `numero` varchar(255) NOT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `valor_itens` decimal(19,2) DEFAULT NULL,
  `pedido_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp5sleibng9e8a1gmubicsgrd0` (`pedido_id`),
  CONSTRAINT `FKp5sleibng9e8a1gmubicsgrd0` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesa`
--

LOCK TABLES `mesa` WRITE;
/*!40000 ALTER TABLE `mesa` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_pedido` date DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `valor_total` decimal(19,2) DEFAULT NULL,
  `codigo_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmqlda2hhcolmusws35fq3ki6q` (`codigo_usuario`),
  CONSTRAINT `FKmqlda2hhcolmusws35fq3ki6q` FOREIGN KEY (`codigo_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissao`
--

--
-- Table structure for table `pizza`
--

DROP TABLE IF EXISTS `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adicional` varchar(255) DEFAULT NULL,
  `borda` varchar(255) DEFAULT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `descricao` varchar(60) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `sabor_pizza` varchar(90) DEFAULT NULL,
  `tamanho` varchar(255) NOT NULL,
  `valor_unitario` decimal(19,2) NOT NULL,
  `promocao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcvpvak77xu52tg495n4ndbn97` (`promocao_id`),
  CONSTRAINT `FKcvpvak77xu52tg495n4ndbn97` FOREIGN KEY (`promocao_id`) REFERENCES `promocao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza_sabor`
--

DROP TABLE IF EXISTS `pizza_sabor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza_sabor` (
  `codigo_pizza` bigint(20) NOT NULL,
  `codigo_sabor` bigint(20) NOT NULL,
  KEY `FKfc6o83iwklosbviuw969bk9al` (`codigo_sabor`),
  KEY `FKefmg2elscewjc6owo50bevwvk` (`codigo_pizza`),
  CONSTRAINT `FKefmg2elscewjc6owo50bevwvk` FOREIGN KEY (`codigo_pizza`) REFERENCES `pizza` (`id`),
  CONSTRAINT `FKfc6o83iwklosbviuw969bk9al` FOREIGN KEY (`codigo_sabor`) REFERENCES `sabor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza_sabor`
--

LOCK TABLES `pizza_sabor` WRITE;
/*!40000 ALTER TABLE `pizza_sabor` DISABLE KEYS */;
/*!40000 ALTER TABLE `pizza_sabor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `caracteristicas` longtext,
  `descricao` varchar(100) NOT NULL,
  `quantidade_estoque` int(11) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `unidade` varchar(60) DEFAULT NULL,
  `valor_compra` decimal(19,2) NOT NULL,
  `valor_unitario` decimal(19,2) NOT NULL,
  `categoria_id` bigint(20) NOT NULL,
  `estabelecimento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j6npst3feop938l4x5h675kyv` (`sku`),
  KEY `FKgqq1rrte5xp1r7v61wkiptq3x` (`categoria_id`),
  KEY `FKsd4xk0na2qggce5khx0mqujl8` (`estabelecimento_id`),
  CONSTRAINT `FKgqq1rrte5xp1r7v61wkiptq3x` FOREIGN KEY (`categoria_id`) REFERENCES `tipo_produto` (`id`),
  CONSTRAINT `FKsd4xk0na2qggce5khx0mqujl8` FOREIGN KEY (`estabelecimento_id`) REFERENCES `estabelecimento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocao`
--

DROP TABLE IF EXISTS `promocao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(90) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `estabelecimento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbj4dljhk2tx0yhc7ugqhufaal` (`estabelecimento_id`),
  CONSTRAINT `FKbj4dljhk2tx0yhc7ugqhufaal` FOREIGN KEY (`estabelecimento_id`) REFERENCES `estabelecimento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocao`
--

LOCK TABLES `promocao` WRITE;
/*!40000 ALTER TABLE `promocao` DISABLE KEYS */;
/*!40000 ALTER TABLE `promocao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sabor`
--

DROP TABLE IF EXISTS `sabor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sabor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sabor`
--

LOCK TABLES `sabor` WRITE;
/*!40000 ALTER TABLE `sabor` DISABLE KEYS */;
/*!40000 ALTER TABLE `sabor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_produto`
--

DROP TABLE IF EXISTS `tipo_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_produto`
--

LOCK TABLES `tipo_produto` WRITE;
/*!40000 ALTER TABLE `tipo_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `titulo`
--

DROP TABLE IF EXISTS `titulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `titulo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `centro_de_custo` varchar(255) NOT NULL,
  `data_de_emissao` date DEFAULT NULL,
  `data_de_validade` date DEFAULT NULL,
  `data_do_pagamento` date DEFAULT NULL,
  `descricao` varchar(255) NOT NULL,
  `forma_de_pagamento` varchar(255) DEFAULT NULL,
  `situacao` varchar(40) NOT NULL,
  `tipo` varchar(40) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `valor_original` decimal(19,2) DEFAULT NULL,
  `valor_pago` decimal(19,2) DEFAULT NULL,
  `estabelecimento_id` bigint(20) DEFAULT NULL,
  `fornecedor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3hqlxi2alpaqoru1vwsx8l0e6` (`estabelecimento_id`),
  KEY `FKm4btffwmv6kmpk1l1r32abkfv` (`fornecedor_id`),
  CONSTRAINT `FK3hqlxi2alpaqoru1vwsx8l0e6` FOREIGN KEY (`estabelecimento_id`) REFERENCES `estabelecimento` (`id`),
  CONSTRAINT `FKm4btffwmv6kmpk1l1r32abkfv` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titulo`
--

LOCK TABLES `titulo` WRITE;
/*!40000 ALTER TABLE `titulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `titulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `confirme_senha` varchar(100) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `bairro` varchar(60) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `cidade` varchar(60) DEFAULT NULL,
  `complemento` varchar(40) DEFAULT NULL,
  `endereco` varchar(60) DEFAULT NULL,
  `estado` varchar(60) DEFAULT NULL,
  `nome` varchar(60) DEFAULT NULL,
  `senha` varchar(100) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `estabelecimento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpwqxybcq1mx2wkbm1aojirast` (`estabelecimento_id`),
  CONSTRAINT `FKpwqxybcq1mx2wkbm1aojirast` FOREIGN KEY (`estabelecimento_id`) REFERENCES `estabelecimento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

--
-- Table structure for table `usuario_grupo`
--

DROP TABLE IF EXISTS `usuario_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_grupo` (
  `codigo_usuario` bigint(20) NOT NULL,
  `codigo_grupo` bigint(20) NOT NULL,
  KEY `FK4yweq9u2sokki6o060mejfw8r` (`codigo_grupo`),
  KEY `FKcx5f61jsftmpnlu4ec8fyndg3` (`codigo_usuario`),
  CONSTRAINT `FK4yweq9u2sokki6o060mejfw8r` FOREIGN KEY (`codigo_grupo`) REFERENCES `grupo` (`id`),
  CONSTRAINT `FKcx5f61jsftmpnlu4ec8fyndg3` FOREIGN KEY (`codigo_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_grupo`
--

--
-- Dumping events for database 'pizzaria'
--

--
-- Dumping routines for database 'pizzaria'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-27 23:01:06
