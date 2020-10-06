SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Table `bancodigitalzup`.`bairro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancodigitalzup`.`bairro` (
  `bairro_id` bigint NOT NULL AUTO_INCREMENT,
  `cidade_id` bigint NOT NULL,
  `nome` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`bairro_id`),
  INDEX `fk_bairro_cidade_idx` (`cidade_id` ASC) VISIBLE,
  CONSTRAINT `fk_bairro_cidade`
    FOREIGN KEY (`cidade_id`)
    REFERENCES `bancodigitalzup`.`cidade` (`cidade_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bancodigitalzup`.`cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancodigitalzup`.`cidade` (
  `cidade_id` bigint NOT NULL AUTO_INCREMENT,
  `estado_id` bigint NOT NULL,
  `nome` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`cidade_id`),
  INDEX `fk_cidade_estado_idx` (`estado_id` ASC) VISIBLE,
  CONSTRAINT `fk_cidade_estado`
    FOREIGN KEY (`estado_id`)
    REFERENCES `bancodigitalzup`.`estado` (`estado_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bancodigitalzup`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancodigitalzup`.`cliente` (
  `cliente_cpf_cnpj` VARCHAR(14) NOT NULL,
  `nome` VARCHAR(30) NOT NULL,
  `sobrenome` VARCHAR(255) NOT NULL,
  `email` VARCHAR(254) NOT NULL,
  `cnh` VARCHAR(11) NULL,
  `data_nascimento` DATE NOT NULL,
  `ativo` TINYINT NOT NULL,
  PRIMARY KEY (`cliente_cpf_cnpj`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bancodigitalzup`.`conta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancodigitalzup`.`conta` (
  `conta_id` bigint NOT NULL AUTO_INCREMENT,
  `proposta_id` bigint NOT NULL,
  `agencia` CHAR(6) NOT NULL,
  `conta` CHAR(12) NOT NULL,
  `codigo_banco` CHAR(3) NOT NULL,
  `tipo_conta` VARCHAR(45) NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`conta_id`),
  INDEX `fk_conta_proposta1_idx` (`proposta_id` ASC) VISIBLE,
  CONSTRAINT `fk_conta_proposta1`
    FOREIGN KEY (`proposta_id`)
    REFERENCES `bancodigitalzup`.`proposta` (`proposta_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bancodigitalzup`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancodigitalzup`.`endereco` (
  `endereco_id` bigint NOT NULL AUTO_INCREMENT,
  `cliente_cpf_cnpj` VARCHAR(14) NOT NULL,
  `bairro_id` bigint NOT NULL,
  `cep` CHAR(8) NOT NULL,
  `rua` VARCHAR(100) NOT NULL,
  `numero` INT NOT NULL,
  `complemento` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`endereco_id`),
  INDEX `fk_endereco_bairro_idx` (`bairro_id` ASC) VISIBLE,
  INDEX `fk_endereco_cliente_idx` (`cliente_cpf_cnpj` ASC) VISIBLE,
  CONSTRAINT `fk_endereco_bairro`
    FOREIGN KEY (`bairro_id`)
    REFERENCES `bancodigitalzup`.`bairro` (`bairro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_cliente`
    FOREIGN KEY (`cliente_cpf_cnpj`)
    REFERENCES `bancodigitalzup`.`cliente` (`cliente_cpf_cnpj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bancodigitalzup`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancodigitalzup`.`estado` (
  `estado_id` bigint NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `sigla` CHAR(2) NOT NULL,
  PRIMARY KEY (`estado_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bancodigitalzup`.`foto_documento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancodigitalzup`.`foto_documento` (
  `foto_documento_id` bigint NOT NULL AUTO_INCREMENT,
  `cliente_cpf_cnpj` VARCHAR(14) NOT NULL,
  `nome_arquivo` VARCHAR(150) NOT NULL,
  `descricao` VARCHAR(150) NULL,
  `content_type` VARCHAR(80) NOT NULL,
  `tamanho` INT NOT NULL,
  PRIMARY KEY (`foto_documento_id`),
  CONSTRAINT `fk_foto_documento_cliente`
    FOREIGN KEY (`cliente_cpf_cnpj`)
    REFERENCES `bancodigitalzup`.`cliente` (`cliente_cpf_cnpj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bancodigitalzup`.`proposta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancodigitalzup`.`proposta` (
  `proposta_id` bigint NOT NULL AUTO_INCREMENT,
  `cliente_cpf_cnpj` VARCHAR(14) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `data_proposta` DATETIME NULL,
  PRIMARY KEY (`proposta_id`),
  INDEX `fk_proposta_cliente_idx` (`cliente_cpf_cnpj` ASC) VISIBLE,
  CONSTRAINT `fk_proposta_cliente`
    FOREIGN KEY (`cliente_cpf_cnpj`)
    REFERENCES `bancodigitalzup`.`cliente` (`cliente_cpf_cnpj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bancodigitalzup`.`transferencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancodigitalzup`.`transferencia` (
  `transferencia_id` bigint NOT NULL AUTO_INCREMENT,
  `conta_id` bigint NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `banco` VARCHAR(45) NOT NULL,
  `agencia` VARCHAR(6) NOT NULL,
  `conta` VARCHAR(12) NOT NULL,
  `cpf_cnpj` VARCHAR(14) NOT NULL,
  `favoritado` TINYINT NOT NULL,
  `tipo_conta` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `data_transferencia` DATETIME NOT NULL,
  `token` VARCHAR(45) NOT NULL,
  `data_criacao_token` DATETIME NOT NULL,
  PRIMARY KEY (`transferencia_id`),
  INDEX `fk_transferencia_conta1_idx` (`conta_id` ASC) VISIBLE,
  CONSTRAINT `fk_transferencia_conta1`
    FOREIGN KEY (`conta_id`)
    REFERENCES `bancodigitalzup`.`conta` (`conta_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bancodigitalzup`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancodigitalzup`.`usuario` (
  `cliente_cpf_cnpj` VARCHAR(14) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `token` VARCHAR(45) NOT NULL,
  `data_criacao_token` DATETIME NOT NULL,
  PRIMARY KEY (`cliente_cpf_cnpj`),
  CONSTRAINT `fk_usuario_cliente1`
    FOREIGN KEY (`cliente_cpf_cnpj`)
    REFERENCES `bancodigitalzup`.`cliente` (`cliente_cpf_cnpj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
