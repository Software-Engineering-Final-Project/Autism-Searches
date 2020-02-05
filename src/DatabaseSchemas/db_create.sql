-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema articledb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema articledb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `articledb` DEFAULT CHARACTER SET utf8 ;
USE `articledb` ;

-- -----------------------------------------------------
-- Table `articledb`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `articledb`.`accounts` (
  `id_account` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(166) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_account`),
  UNIQUE INDEX `idaccounts_UNIQUE` (`id_account` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `articledb`.`stared_articles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `articledb`.`stared_articles` (
  `id_stared_articles` INT NOT NULL AUTO_INCREMENT,
  `article_name` VARCHAR(60) NOT NULL,
  `article_authors` VARCHAR(256) NOT NULL,
  `stared_articlescol` VARCHAR(45) NOT NULL,
  `article_site` VARCHAR(45) NOT NULL,
  `fk_account_id` INT NOT NULL,
  PRIMARY KEY (`id_stared_articles`),
  INDEX `fk_account_id_idx` (`fk_account_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_id`
    FOREIGN KEY (`fk_account_id`)
    REFERENCES `articledb`.`accounts` (`id_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
