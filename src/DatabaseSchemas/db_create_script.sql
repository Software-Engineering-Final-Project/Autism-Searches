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
  `email` VARCHAR(45) NOT NULL,
  `path` VARCHAR(256) NULL,
  PRIMARY KEY (`id_account`),
  UNIQUE INDEX `idaccounts_UNIQUE` (`id_account` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article_db`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `article_db`.`categories` (
  `id_categories` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  `category_description` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`id_categories`),
  UNIQUE INDEX `id_categories_UNIQUE` (`id_categories` ASC) VISIBLE,
  UNIQUE INDEX `category_name_UNIQUE` (`category_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article_db`.`starred_articles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `article_db`.`starred_articles` (
  `id_stared_articles` INT NOT NULL AUTO_INCREMENT,
  `article_name` VARCHAR(60) NOT NULL,
  `article_authors` VARCHAR(256) NOT NULL,
  `article_site` VARCHAR(45) NOT NULL,
  `fk_account_id` INT NOT NULL,
  `fk_categories_id` INT NOT NULL,
  PRIMARY KEY (`id_stared_articles`),
  INDEX `fk_account_id_idx` (`fk_account_id` ASC) VISIBLE,
  INDEX `fk_categories_id_idx` (`fk_categories_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_id`
    FOREIGN KEY (`fk_account_id`)
    REFERENCES `article_db`.`accounts` (`id_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_categories_id`
    FOREIGN KEY (`fk_categories_id`)
    REFERENCES `article_db`.`categories` (`id_categories`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article_db`.`starred_categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `article_db`.`starred_categories` (
  `fk_accounts_id` INT NOT NULL,
  `fk_categories_id` INT NOT NULL,
  PRIMARY KEY (`fk_accounts_id`, `fk_categories_id`),
  INDEX `fk_accounts_has_categories_categories1_idx` (`fk_categories_id` ASC) VISIBLE,
  INDEX `fk_accounts_has_categories_accounts1_idx` (`fk_accounts_id` ASC) VISIBLE,
  CONSTRAINT `fk_accounts_has_categories_accounts1`
    FOREIGN KEY (`fk_accounts_id`)
    REFERENCES `article_db`.`accounts` (`id_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_accounts_has_categories_categories1`
    FOREIGN KEY (`fk_categories_id`)
    REFERENCES `article_db`.`categories` (`id_categories`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
