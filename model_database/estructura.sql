SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`urbanizacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`urbanizacion` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`urbanizacion` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `ancho` DOUBLE NULL ,
  `largo` DOUBLE NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`manzano`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`manzano` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`manzano` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `urbanizacion_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_manzano_urbanizacion_idx` (`urbanizacion_id` ASC) ,
  CONSTRAINT `fk_manzano_urbanizacion`
    FOREIGN KEY (`urbanizacion_id` )
    REFERENCES `mydb`.`urbanizacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`lote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`lote` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`lote` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `numero_lote` INT NOT NULL ,
  `precio` DOUBLE NOT NULL ,
  `descripcion` VARCHAR(255) NULL ,
  `manzano_id` INT NOT NULL ,
  `ancho` DOUBLE NULL ,
  `largo` DOUBLE NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_lote_manzano1_idx` (`manzano_id` ASC) ,
  CONSTRAINT `fk_lote_manzano1`
    FOREIGN KEY (`manzano_id` )
    REFERENCES `mydb`.`manzano` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`cliente` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`cliente` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(255) NOT NULL ,
  `apellido_paterno` VARCHAR(255) NOT NULL ,
  `apellido_materno` VARCHAR(255) NULL ,
  `direccion` VARCHAR(255) NULL ,
  `telefono_fijo` INT NULL ,
  `celular` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`venta` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`venta` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `fecha_venta` TIMESTAMP NOT NULL ,
  `cantidad_cuotas` INT NOT NULL ,
  `lote_id` INT NOT NULL ,
  `cliente_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_venta_lote1_idx` (`lote_id` ASC) ,
  INDEX `fk_venta_cliente1_idx` (`cliente_id` ASC) ,
  CONSTRAINT `fk_venta_lote1`
    FOREIGN KEY (`lote_id` )
    REFERENCES `mydb`.`lote` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_cliente1`
    FOREIGN KEY (`cliente_id` )
    REFERENCES `mydb`.`cliente` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`pago` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`pago` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `fecha_pago` TIMESTAMP NOT NULL ,
  `monto` DOUBLE NOT NULL ,
  `venta_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_pago_venta1_idx` (`venta_id` ASC) ,
  CONSTRAINT `fk_pago_venta1`
    FOREIGN KEY (`venta_id` )
    REFERENCES `mydb`.`venta` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
