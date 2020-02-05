-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema article_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema article_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `article_db` DEFAULT CHARACTER SET utf8 ;
USE `article_db` ;

-- -----------------------------------------------------
-- Table `article_db`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `article_db`.`accounts` (
  `id_account` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(166) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_account`),
  UNIQUE INDEX `idaccounts_UNIQUE` (`id_account` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article_db`.`starred_articles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `article_db`.`starred_articles` (
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
    REFERENCES `article_db`.`accounts` (`id_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
