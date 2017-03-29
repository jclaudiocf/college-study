SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `financa` ;
CREATE SCHEMA IF NOT EXISTS `financa` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `financa` ;

-- -----------------------------------------------------
-- Table `financa`.`USUARIOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `financa`.`USUARIOS` ;

CREATE TABLE IF NOT EXISTS `financa`.`USUARIOS` (
  `IDUSUARIO` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(100) NOT NULL,
  `LOGIN` VARCHAR(30) NOT NULL,
  `SENHA` VARCHAR(10) NOT NULL,
  `ST` INT NULL DEFAULT 1 COMMENT 'VALORES\n1 = ATIVO\n2 = CANCELADO',
  PRIMARY KEY (`IDUSUARIO`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `financa`.`CONTAS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `financa`.`CONTAS` ;

CREATE TABLE IF NOT EXISTS `financa`.`CONTAS` (
  `IDCONTA` INT NOT NULL AUTO_INCREMENT,
  `IDUSUARIO` INT NOT NULL,
  `DESCRICAO` VARCHAR(100) NULL,
  `TIPO_CONTA` INT NULL DEFAULT 1 COMMENT 'VALORES\n1 = BANCO\n2 = CAIXA\n3 = APLICAÇÃO',
  `AGENCIA` VARCHAR(100) NULL,
  `DIGITO_AGENCIA` VARCHAR(5) NULL,
  `CONTA` VARCHAR(100) NULL,
  `DIGITO_CONTA` VARCHAR(5) NULL,
  `ENDERECO_AGENCIA` VARCHAR(100) NULL,
  `SALDO` DECIMAL(10,0) NULL DEFAULT 0,
  `ST` INT NULL DEFAULT 1 COMMENT 'VALORES\n1 = ATIVO\n2 = CANCELADO',
  PRIMARY KEY (`IDCONTA`),
  INDEX `FK_CONTAS_USUARIOS_idx` (`IDUSUARIO` ASC),
  CONSTRAINT `FK_CONTAS_USUARIOS`
    FOREIGN KEY (`IDUSUARIO`)
    REFERENCES `financa`.`USUARIOS` (`IDUSUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `financa`.`CCUSTOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `financa`.`CCUSTOS` ;

CREATE TABLE IF NOT EXISTS `financa`.`CCUSTOS` (
  `IDCCUSTO` INT NOT NULL AUTO_INCREMENT,
  `IDUSUARIO` INT NOT NULL,
  `DESCRICAO` VARCHAR(100) NULL,
  `ST` INT NULL DEFAULT 1 COMMENT 'VALORES\n1 = ATIVO\n2 = CANCELADO',
  PRIMARY KEY (`IDCCUSTO`),
  INDEX `FK_CCUSTOS_USUARIOS_idx` (`IDUSUARIO` ASC),
  CONSTRAINT `FK_CCUSTOS_USUARIOS`
    FOREIGN KEY (`IDUSUARIO`)
    REFERENCES `financa`.`USUARIOS` (`IDUSUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `financa`.`FIXOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `financa`.`FIXOS` ;

CREATE TABLE IF NOT EXISTS `financa`.`FIXOS` (
  `IDFIXO` INT NOT NULL AUTO_INCREMENT,
  `IDUSUARIO` INT NOT NULL,
  `TIPO_FIXO` INT NOT NULL DEFAULT 1 COMMENT 'VALORES\n1 = PROVENTO\n2 = DESCONTO',
  `DESCRICAO` VARCHAR(100) NULL,
  `VALOR` DECIMAL(10,0) NULL DEFAULT 0,
  `PERIODICIDADE` INT NULL DEFAULT 2 COMMENT 'VALORES\n1 = SEMANAL\n2 = MENSAL\n3 = ANUAL',
  `ST` INT NULL DEFAULT 1 COMMENT 'VALORES\n1 = ATIVO\n2 = CANCELADO',
  PRIMARY KEY (`IDFIXO`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `financa`.`MOVIMENTOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `financa`.`MOVIMENTOS` ;

CREATE TABLE IF NOT EXISTS `financa`.`MOVIMENTOS` (
  `IDMOVIMENTO` INT NOT NULL AUTO_INCREMENT,
  `IDUSUARIO` INT NOT NULL,
  `IDCCUSTO` INT NOT NULL,
  `IDCONTA` INT NOT NULL,
  `IDFIXO` INT NULL,
  `TIPO_MOVIMENTO` INT NOT NULL COMMENT 'VALORES\n1 = CREDITO_A_VISTA\n2 = DEBITO_A_VISTA\n3 = CREDITO_A_PRAZO\n4 = DEBITO_A_PRAZO\n5 = QUITACAO_DE_CREDITO\n6 = QUITACAO_DE_DEBITO',
  `DESCRICAO` VARCHAR(100) NULL,
  `DATA_LANCAMENTO` TIMESTAMP NULL,
  `VALOR` DECIMAL(10,0) NULL DEFAULT 0,
  `PARCELADO` INT NULL DEFAULT 2 COMMENT 'VALORES\n1 = SIM\n2 = NAO',
  `ST` INT NULL DEFAULT 1 COMMENT 'VALORES\n1 = ATIVO\n2 = CANCELADO',
  PRIMARY KEY (`IDMOVIMENTO`),
  INDEX `FK_MOVIMENTOS_USUARIOS_idx` (`IDUSUARIO` ASC),
  INDEX `FK_MOVIMENTOS_CCUSTO_idx` (`IDCCUSTO` ASC),
  INDEX `FK_MOVIMENTOS_CONTAS_idx` (`IDCONTA` ASC),
  INDEX `FK_MOVIMENTOS_FIXOS_idx` (`IDFIXO` ASC),
  CONSTRAINT `FK_MOVIMENTOS_USUARIOS`
    FOREIGN KEY (`IDUSUARIO`)
    REFERENCES `financa`.`USUARIOS` (`IDUSUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_MOVIMENTOS_CCUSTOS`
    FOREIGN KEY (`IDCCUSTO`)
    REFERENCES `financa`.`CCUSTOS` (`IDCCUSTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_MOVIMENTOS_CONTAS`
    FOREIGN KEY (`IDCONTA`)
    REFERENCES `financa`.`CONTAS` (`IDCONTA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_MOVIMENTOS_FIXOS`
    FOREIGN KEY (`IDFIXO`)
    REFERENCES `financa`.`FIXOS` (`IDFIXO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `financa`.`PARCELAS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `financa`.`PARCELAS` ;

CREATE TABLE IF NOT EXISTS `financa`.`PARCELAS` (
  `IDPARCELA` INT NOT NULL AUTO_INCREMENT,
  `IDMOVIMENTO` INT NOT NULL,
  `IDMOVIMENTO_QUITACAO` INT NULL,
  `IDCCUSTO` INT NOT NULL,
  `IDCONTA` INT NOT NULL,
  `TIPO_PARCELA` INT NULL COMMENT 'VALORES\n1 = QUITADO\n3 = CREDITO_A_PRAZO\n4 = DEBITO_A_PRAZO',
  `DESCRICAO` VARCHAR(100) NULL,
  `DATA_LANCAMENTO` TIMESTAMP NULL,
  `DATA_VENCIMENTO` TIMESTAMP NULL,
  `DATA_QUITACAO` TIMESTAMP NULL,
  `VALOR` DECIMAL(10,0) NULL,
  `NUMERO_PARCELA` INT NULL,
  `TOTAL_PARCELA` INT NULL,
  `ST` INT NULL DEFAULT 1 COMMENT 'VALORES\n1 = ATIVO\n2 = CANCELADO',
  PRIMARY KEY (`IDPARCELA`),
  INDEX `FK_PARCELAS_MOVIMENTOS_idx` (`IDMOVIMENTO` ASC),
  INDEX `FK_QUITACAO_MOVIMENTOS_idx` (`IDMOVIMENTO_QUITACAO` ASC),
  INDEX `FK_PARCELAS_CCUSTOS_idx` (`IDCCUSTO` ASC),
  INDEX `FK_PARCELAS_CONTAS_idx` (`IDCONTA` ASC),
  CONSTRAINT `FK_PARCELAS_MOVIMENTOS`
    FOREIGN KEY (`IDMOVIMENTO`)
    REFERENCES `financa`.`MOVIMENTOS` (`IDMOVIMENTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_QUITACAO_MOVIMENTOS`
    FOREIGN KEY (`IDMOVIMENTO_QUITACAO`)
    REFERENCES `financa`.`MOVIMENTOS` (`IDMOVIMENTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PARCELAS_CCUSTOS`
    FOREIGN KEY (`IDCCUSTO`)
    REFERENCES `financa`.`CCUSTOS` (`IDCCUSTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PARCELAS_CONTAS`
    FOREIGN KEY (`IDCONTA`)
    REFERENCES `financa`.`CONTAS` (`IDCONTA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

USE `financa` ;

-- -----------------------------------------------------
-- Placeholder table for view `financa`.`V_SALDO_CONTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financa`.`V_SALDO_CONTA` (`CODIGO` INT, `DESCRICAO` INT, `SALDO` INT);

-- -----------------------------------------------------
-- procedure SP_AJUSTA_SALDO
-- -----------------------------------------------------

USE `financa`;
DROP procedure IF EXISTS `financa`.`SP_AJUSTA_SALDO`;

DELIMITER $$
USE `financa`$$
CREATE PROCEDURE SP_AJUSTA_SALDO (
    IN STATE CHAR(1),
    IN IDCONTA_LANCAMENTO INTEGER,
    IN VALOR_LANCAMENTO DOUBLE,
    IN VALOR_LANCAMENTO_ANTERIOR DOUBLE,
    IN TIPO_MOVIMENTO INTEGER)
BEGIN
/*  MOVIMENTO.TIPO_MOVIMENTO
    1 - CREDITO A VISTA
    2 - DEBITO A VISTA
    (3 - CREDITO A PRAZO)
    (4 - DEBITO A PRAZO)
    5 - QUITACAO DE CREDITO
    6 - QUITACAO DE DEBITO
*/

  DECLARE SALDO_ATUAL DOUBLE DEFAULT 0;

  -- SALDO ATUAL
  SELECT CONTAS.SALDO FROM CONTAS WHERE CONTAS.IDCONTA = IDCONTA_LANCAMENTO INTO SALDO_ATUAL;

  IF (STATE IN ('I','U')) THEN -- INSERT, UPDATE
    IF (TIPO_MOVIMENTO IN (1,5)) THEN -- SOMA CREDITOS
        SET SALDO_ATUAL = ((SALDO_ATUAL - VALOR_LANCAMENTO_ANTERIOR) + VALOR_LANCAMENTO);
    ELSEIF (TIPO_MOVIMENTO IN (2,6)) THEN -- SOMA DEBITOS
        SET SALDO_ATUAL = ((SALDO_ATUAL + VALOR_LANCAMENTO_ANTERIOR) - VALOR_LANCAMENTO);
    END IF;
  ELSEIF (STATE = 'D') THEN -- DELETE
    IF (TIPO_MOVIMENTO IN (1,5)) THEN -- DESFAZ SOMA CREDITOS
        SET SALDO_ATUAL = (SALDO_ATUAL - VALOR_LANCAMENTO);
    ELSEIF (TIPO_MOVIMENTO IN (2,6)) THEN -- DESFAZ SOMA DEBITOS
        SET SALDO_ATUAL = (SALDO_ATUAL + VALOR_LANCAMENTO);
    END IF;
  END IF;

  UPDATE CONTAS SET CONTAS.SALDO = SALDO_ATUAL WHERE CONTAS.IDCONTA = IDCONTA_LANCAMENTO;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure SP_AJUSTA_QUITACAO
-- -----------------------------------------------------

USE `financa`;
DROP procedure IF EXISTS `financa`.`SP_AJUSTA_QUITACAO`;

DELIMITER $$
USE `financa`$$
CREATE PROCEDURE SP_AJUSTA_QUITACAO (
    IN IDMOVIMENTO_QUITACAO_PARCELA INTEGER,
    IN IDCCUSTO_PARCELA INTEGER,
    IN IDCONTA_PARCELA INTEGER,
    IN TIPO_PARCELA_P INTEGER,
    IN DESCRICAO_PARCELA VARCHAR(100),
    IN DATA_QUITACAO_PARCELA TIMESTAMP,
    IN VALOR_PARCELA DOUBLE,
    IN ST_PARCELA CHAR(1))
BEGIN
/*  MOVIMENTO.TIPO
    1 - CREDITO A VISTA
    2 - DEBITO A VISTA
    (3 - CREDITO A PRAZO)
    (4 - DEBITO A PRAZO)
    5 - QUITACAO DE CREDITO
    6 - QUITACAO DE DEBITO
*/
  DECLARE NOVO_TIPO_MOVIMENTO INTEGER;
  DECLARE NOVO_DESCRICAO VARCHAR(110);
    
    IF (ST_PARCELA = 'Q') THEN -- INSERE QUITACAO
        IF (TIPO_PARCELA_P = 3) THEN -- CREDITO A PRAZO
            SET NOVO_TIPO_MOVIMENTO = 1; -- CREDITO A VISTA
            SET NOVO_DESCRICAO = 'QC - ' || DESCRICAO_PARCELA;
        ELSEIF (TIPO_PARCELA_P = 4) THEN -- DEBITO A PRAZO
            SET NOVO_TIPO_MOVIMENTO = 2; -- DEBITO A VISTA
            SET NOVO_DESCRICAO = 'QD - ' || DESCRICAO_PARCELA;
        END IF;
        
        -- INSERE QUITACAO
        INSERT INTO MOVIMENTOS (MOVIMENTOS.IDMOVIMENTO, MOVIMENTOS.IDCCUSTO, MOVIMENTOS.IDCONTA, MOVIMENTOS.TIPO, MOVIMENTOS.DESCRICAO, MOVIMENTOS.DATA_LANCAMENTO, MOVIMENTOS.VALOR)
                       VALUES (IDMOVIMENTO_QUITACAO_PARCELA, IDCCUSTO_PARCELA, IDCONTA_PARCELA, NOVO_TIPO_MOVIMENTO, NOVO_DESCRICAO, DATA_QUITACAO_PARCELA, VALOR_PARCELA);
    ELSEIF (ST_PARCELA = 'C') THEN -- CANCELA QUITACAO
        UPDATE MOVIMENTOS SET MOVIMENTOS.ST = 'C' WHERE MOVIMENTOS.IDMOVIMENTO = IDMOVIMENTO_QUITACAO_PARCELA;
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure SP_EXTRATO_CONTA
-- -----------------------------------------------------

USE `financa`;
DROP procedure IF EXISTS `financa`.`SP_EXTRATO_CONTA`;

DELIMITER $$
USE `financa`$$
CREATE PROCEDURE SP_EXTRATO_CONTA (
    IN IDCONTA_EXTRATO INTEGER,
    IN IDCCUSTO_EXTRATO INTEGER,
    IN DATA_INICIO_EXTRATO TIMESTAMP,
    IN DATA_FIM_EXTRATO TIMESTAMP,
    OUT IDCONTA INTEGER,
    OUT IDCCUSTO INTEGER,
    OUT DESCRICAO VARCHAR(100),
    OUT DATA_MOVIMENTO TIMESTAMP,
    OUT TIPO_MOVIMENTO CHAR(1),
    OUT VALOR DOUBLE,
    OUT SALDO_MOVIMENTO DOUBLE)
BEGIN
/*  MOVIMENTO.TIPO_MOVIMENTO
    1 - CREDITO A VISTA
    2 - DEBITO A VISTA
    (3 - CREDITO A PRAZO)
    (4 - DEBITO A PRAZO)
    5 - QUITACAO DE CREDITO
    6 - QUITACAO DE DEBITO
*/

  DECLARE SQL_WHERE VARCHAR(5000) DEFAULT '';
  DECLARE SQL_TXT VARCHAR(5000) DEFAULT '';
  DECLARE TIPO INTEGER;
  DECLARE SALDO_ATUAL DOUBLE DEFAULT 0;
  DECLARE SALDO_ANTERIOR_CREDITO DOUBLE DEFAULT 0;
  DECLARE SALDO_ANTERIOR_DEBITO DOUBLE DEFAULT 0;

  -- SALDO ATUAL DA TABELA CONTA
  SELECT CONTAS.SALDO FROM CONTAS
   WHERE CONTAS.IDCONTA = IDCONTA_EXTRATO
    INTO SALDO_ATUAL;

  -- WHERES
  IF (IDCONTA_EXTRATO > 0) THEN
    SET SQL_WHERE = SQL_WHERE || ' AND MOVIMENTO.IDCONTA = ' || IDCONTA_EXTRATO;
  END IF;

  IF (IDCCUSTO_EXTRATO > 0) THEN
    SET SQL_WHERE = SQL_WHERE || ' AND MOVIMENTO.IDCONTA = ' || IDCCUSTO_EXTRATO;
  END IF;

  SET SQL_WHERE = SQL_WHERE || ' AND MOVIMENTO.ST = ''A'' ';

  -- TOTAL DE CREDITOS ANTERIORES
  SET SQL_TXT = 'SELECT COALESCE(SUM(MOVIMENTO.VALOR), 0) ' ||
              'FROM MOVIMENTO ' ||
             'WHERE 1 = 1 ' ||
               'AND MOVIMENTO.TIPO IN (1, 5) ' ||
               'AND MOVIMENTO.DATA_LANCAMENTO < ''' || DATA_INICIO_EXTRATO || ''' ' ||
            SQL_WHERE;
  -- EXECUTE STATEMENT SQL_TXT INTO :SALDO_ANTERIOR_CREDITO;

  -- TOTAL DE DEBITOS ANTERIORES
  SET SQL_TXT = 'SELECT COALESCE(SUM(MOVIMENTO.VALOR), 0) ' ||
              'FROM MOVIMENTO ' ||
             'WHERE 1 = 1 ' ||
               'AND MOVIMENTO.TIPO IN (2, 6) ' ||
               'AND MOVIMENTO.DATA_LANCAMENTO < ''' || DATA_INICIO_EXTRATO || ''' ' ||
            SQL_WHERE;
  -- EXECUTE STATEMENT :SQL_TXT INTO :SALDO_ANTERIOR_DEBITO;

  -- SELECT MOVIMENTOS DO PERIODO
  SET SQL_TXT = 'SELECT MOVIMENTO.IDCONTA, MOVIMENTO.IDCCUSTO, MOVIMENTO.DESCRICAO, MOVIMENTO.DATA_LANCAMENTO, MOVIMENTO.TIPO, MOVIMENTO.VALOR ' ||
              'FROM MOVIMENTO ' ||
             'WHERE 1 = 1 ' ||
               'AND MOVIMENTO.TIPO IN (1, 2, 5, 6) ' ||
               'AND MOVIMENTO.DATA_LANCAMENTO BETWEEN ''' || DATA_INICIO_EXTRATO || ''' AND ''' || DATA_FIM_EXTRATO || ''' ' ||
               SQL_WHERE ||
             'ORDER BY MOVIMENTO.DATA_LANCAMENTO, MOVIMENTO.IDMOVIMENTO ';

  -- INICIALIZA SALDO ATUAL COM INFORMACOES DE DATA ANTERIORES
  SET SALDO_ATUAL = ( SALDO_ATUAL + (SALDO_ANTERIOR_CREDITO - SALDO_ANTERIOR_DEBITO) );

  -- PERCORRE MOVIMENTOS
  /*FOR
    EXECUTE STATEMENT :SQL_TXT
    INTO :IDCONTA, :IDCCUSTO, :DESCRICAO, :DATA_MOVIMENTO, :TIPO, :VALOR
  DO
  BEGIN
    -- TIPO MOVIMENTO
    IF (TIPO IN (1, 5)) THEN -- CREDITO
    BEGIN
        TIPO_MOVIMENTO = 'C';
        SALDO_ATUAL = SALDO_ATUAL + VALOR;
    END
    ELSE IF (TIPO IN (2, 6)) THEN -- DEBITO
    BEGIN
        TIPO_MOVIMENTO = 'D';
        SALDO_ATUAL = SALDO_ATUAL - VALOR;
    END

    SALDO_MOVIMENTO = SALDO_ATUAL;

    SUSPEND;
  END*/
END$$

DELIMITER ;

-- -----------------------------------------------------
-- View `financa`.`V_SALDO_CONTA`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `financa`.`V_SALDO_CONTA` ;
DROP TABLE IF EXISTS `financa`.`V_SALDO_CONTA`;
USE `financa`;
CREATE  OR REPLACE VIEW V_SALDO_CONTA(
    CODIGO,
    DESCRICAO,
    SALDO)
AS
SELECT CONTAS.IDCONTA, CONTAS.DESCRICAO, CONTAS.SALDO FROM CONTAS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
USE `financa`;

DELIMITER $$

USE `financa`$$
DROP TRIGGER IF EXISTS `financa`.`MOVIMENTOS_BI` $$
USE `financa`$$
CREATE TRIGGER `MOVIMENTOS_BI` BEFORE INSERT ON `MOVIMENTOS` FOR EACH ROW
BEGIN
/*  MOVIMENTO.TIPO_MOVIMENTO
    1 - CREDITO A VISTA
    2 - DEBITO A VISTA
    (3 - CREDITO A PRAZO)
    (4 - DEBITO A PRAZO)
    5 - QUITACAO DE CREDITO
    6 - QUITACAO DE DEBITO

	MOVIMENTO.ST
	1 - ATIVO
	2 - CANCELADO
*/

	IF (NEW.IDFIXO = 0) THEN
		SET NEW.IDFIXO = NULL;
	END IF;

	IF (NEW.TIPO_MOVIMENTO IN (1,2,5,6)) THEN
		-- INSERINDO REGISTRO SALDO
		CALL SP_AJUSTA_SALDO('I', NEW.IDCONTA, NEW.VALOR, 0, NEW.TIPO_MOVIMENTO);
	END IF;
END;
$$


USE `financa`$$
DROP TRIGGER IF EXISTS `financa`.`MOVIMENTOS_BU` $$
USE `financa`$$
CREATE TRIGGER `MOVIMENTOS_BU` BEFORE UPDATE ON `MOVIMENTOS` FOR EACH ROW
BEGIN
/*  MOVIMENTO.TIPO_MOVIMENTO
    1 - CREDITO A VISTA
    2 - DEBITO A VISTA
    (3 - CREDITO A PRAZO)
    (4 - DEBITO A PRAZO)
    5 - QUITACAO DE CREDITO
    6 - QUITACAO DE DEBITO

	MOVIMENTO.ST
	1 - ATIVO
	2 - CANCELADO
*/

	IF ( (NEW.TIPO_MOVIMENTO IN (1,2,5,6)) OR (OLD.TIPO_MOVIMENTO IN (1,2,5,6)) ) THEN
		-- ATUALIZANDO REGISTRO SALDO
		IF ( (OLD.ST = 1) AND (NEW.ST = 2) ) THEN -- CANCELOU REGISTRO DE MOVIMENTO
			CALL SP_AJUSTA_SALDO('D', NEW.IDCONTA, NEW.VALOR, 0, NEW.TIPO_MOVIMENTO);
		ELSEIF ( (OLD.ST = 2) AND (NEW.ST = 1) ) THEN -- ATIVOU REGISTRO DE MOVIMENTO
			CALL SP_AJUSTA_SALDO('I', NEW.IDCONTA, NEW.VALOR, 0, NEW.TIPO_MOVIMENTO);
		ELSEIF ((OLD.IDCONTA <> NEW.IDCONTA) OR (OLD.TIPO_MOVIMENTO <> NEW.TIPO_MOVIMENTO)) THEN -- ALTEROU CONTA OU TIPO MOVIMENTO
			CALL SP_AJUSTA_SALDO('D', OLD.IDCONTA, OLD.VALOR, 0, OLD.TIPO_MOVIMENTO);
			CALL SP_AJUSTA_SALDO('I', NEW.IDCONTA, NEW.VALOR, 0, NEW.TIPO_MOVIMENTO);
		ELSEIF (OLD.VALOR <> NEW.VALOR) THEN -- ALTEROU VALOR
			CALL SP_AJUSTA_SALDO('U', NEW.IDCONTA, NEW.VALOR, OLD.VALOR, NEW.TIPO_MOVIMENTO);
		END IF;
	END IF;
END;
$$


USE `financa`$$
DROP TRIGGER IF EXISTS `financa`.`MOVIMENTOS_BD` $$
USE `financa`$$
CREATE TRIGGER `MOVIMENTOS_BD` BEFORE DELETE ON `MOVIMENTOS` FOR EACH ROW
BEGIN
/*  MOVIMENTO.TIPO_MOVIMENTO
    1 - CREDITO A VISTA
    2 - DEBITO A VISTA
    (3 - CREDITO A PRAZO)
    (4 - DEBITO A PRAZO)
    5 - QUITACAO DE CREDITO
    6 - QUITACAO DE DEBITO

	MOVIMENTO.ST
	1 - ATIVO
	2 - CANCELADO
*/
	IF (OLD.TIPO_MOVIMENTO IN (1,2,5,6)) THEN
		-- DELETANDO REGISTRO SALDO NAO CANCELADO
		IF (OLD.ST <> 2) THEN -- DELETANDO REGISTRO SALDO NAO CANCELADO
            CALL SP_AJUSTA_SALDO('D', OLD.IDCONTA, OLD.VALOR, 0, OLD.TIPO_MOVIMENTO);
        END IF;
	END IF;
END;$$


USE `financa`$$
DROP TRIGGER IF EXISTS `financa`.`PARCELAS_BI` $$
USE `financa`$$
CREATE TRIGGER `PARCELAS_BI` BEFORE INSERT ON `PARCELAS` FOR EACH ROW
BEGIN
	IF (NEW.IDMOVIMENTO_QUITACAO = 0) THEN
		SET NEW.IDMOVIMENTO_QUITACAO := NULL;
	END IF;
END;
$$


DELIMITER ;