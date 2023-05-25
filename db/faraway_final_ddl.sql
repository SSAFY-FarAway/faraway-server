-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema faraway
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema faraway
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `faraway` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `faraway` ;

-- -----------------------------------------------------
-- Table `faraway`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`member` ;

CREATE TABLE IF NOT EXISTS `faraway`.`member` (
  `member_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `main_address` VARCHAR(255) NOT NULL,
  `sub_address` VARCHAR(255) NOT NULL,
  `zipcode` VARCHAR(5) NOT NULL,
  `birth` VARCHAR(6) NOT NULL,
  `certified` INT NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `login_id` VARCHAR(20) NOT NULL,
  `login_pwd` VARCHAR(255) NOT NULL,
  `mileage` INT NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `role` VARCHAR(255) NOT NULL,
  `salt` VARCHAR(45) NOT NULL,
  `token` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE INDEX `UK_mbmcqelty0fbrvxp1q58dn57t` (`email` ASC) VISIBLE,
  UNIQUE INDEX `UK_enfm5patwjqulw8k4wwuo6f60` (`login_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`category` ;

CREATE TABLE IF NOT EXISTS `faraway`.`category` (
  `category_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `category_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE INDEX `UK_lroeo5fvfdeg4hpicn4lw7x9b` (`category_name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`post` ;

CREATE TABLE IF NOT EXISTS `faraway`.`post` (
  `post_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `content` TEXT NOT NULL,
  `hit` INT NOT NULL,
  `title` VARCHAR(120) NOT NULL,
  `category_id` BIGINT NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  INDEX `FKg6l1ydp1pwkmyj166teiuov1b` (`category_id` ASC) VISIBLE,
  INDEX `FK83s99f4kx8oiqm3ro0sasmpww` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FK83s99f4kx8oiqm3ro0sasmpww`
    FOREIGN KEY (`member_id`)
    REFERENCES `faraway`.`member` (`member_id`),
  CONSTRAINT `FKg6l1ydp1pwkmyj166teiuov1b`
    FOREIGN KEY (`category_id`)
    REFERENCES `faraway`.`category` (`category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`attachment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`attachment` ;

CREATE TABLE IF NOT EXISTS `faraway`.`attachment` (
  `attachment_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `store_file_name` VARCHAR(255) NULL DEFAULT NULL,
  `upload_file_name` VARCHAR(255) NULL DEFAULT NULL,
  `post_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`attachment_id`),
  INDEX `FK57nlwn59e1o3uor5njjmukiar` (`post_id` ASC) VISIBLE,
  CONSTRAINT `FK57nlwn59e1o3uor5njjmukiar`
    FOREIGN KEY (`post_id`)
    REFERENCES `faraway`.`post` (`post_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`sido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`sido` ;

CREATE TABLE IF NOT EXISTS `faraway`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `faraway`.`gugun`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`gugun` ;

CREATE TABLE IF NOT EXISTS `faraway`.`gugun` (
  `gugun_code` INT NOT NULL,
  `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
  `sido_code` INT NOT NULL,
  PRIMARY KEY (`gugun_code`, `sido_code`),
  INDEX `gugun_to_sido_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `gugun_to_sido_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `faraway`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `faraway`.`attraction_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`attraction_info` ;

CREATE TABLE IF NOT EXISTS `faraway`.`attraction_info` (
  `content_id` INT NOT NULL,
  `content_type_id` INT NULL DEFAULT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `addr1` VARCHAR(100) NULL DEFAULT NULL,
  `addr2` VARCHAR(50) NULL DEFAULT NULL,
  `zipcode` VARCHAR(50) NULL DEFAULT NULL,
  `tel` VARCHAR(50) NULL DEFAULT NULL,
  `first_image` VARCHAR(200) NULL DEFAULT NULL,
  `first_image2` VARCHAR(200) NULL DEFAULT NULL,
  `readcount` INT NULL DEFAULT NULL,
  `sido_code` INT NULL DEFAULT NULL,
  `gugun_code` INT NULL DEFAULT NULL,
  `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `mlevel` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id` ASC) VISIBLE,
  INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `attraction_to_content_type_id_fk`
    FOREIGN KEY (`content_type_id`)
    REFERENCES `faraway`.`content_type` (`content_type_id`),
  CONSTRAINT `attraction_to_gugun_code_fk`
    FOREIGN KEY (`gugun_code`)
    REFERENCES `faraway`.`gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `faraway`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `faraway`.`attraction_description`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`attraction_description` ;

CREATE TABLE IF NOT EXISTS `faraway`.`attraction_description` (
  `content_id` INT NOT NULL,
  `homepage` VARCHAR(100) NULL DEFAULT NULL,
  `overview` VARCHAR(10000) NULL DEFAULT NULL,
  `telname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `faraway`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `faraway`.`attraction_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`attraction_detail` ;

CREATE TABLE IF NOT EXISTS `faraway`.`attraction_detail` (
  `content_id` INT NOT NULL,
  `cat1` VARCHAR(3) NULL DEFAULT NULL,
  `cat2` VARCHAR(5) NULL DEFAULT NULL,
  `cat3` VARCHAR(9) NULL DEFAULT NULL,
  `created_time` VARCHAR(14) NULL DEFAULT NULL,
  `modified_time` VARCHAR(14) NULL DEFAULT NULL,
  `booktour` VARCHAR(5) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_basic_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `faraway`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `faraway`.`attraction_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`attraction_like` ;

CREATE TABLE IF NOT EXISTS `faraway`.`attraction_like` (
  `attraction_like_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `content_id` INT NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`attraction_like_id`),
  INDEX `FKo30irsovwlxp43hw4g41w03r7` (`content_id` ASC) VISIBLE,
  INDEX `FKe6lp3keo2qxlhwui5lewqngm3` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FKe6lp3keo2qxlhwui5lewqngm3`
    FOREIGN KEY (`member_id`)
    REFERENCES `faraway`.`member` (`member_id`),
  CONSTRAINT `FKo30irsovwlxp43hw4g41w03r7`
    FOREIGN KEY (`content_id`)
    REFERENCES `faraway`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`hot_place`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`hot_place` ;

CREATE TABLE IF NOT EXISTS `faraway`.`hot_place` (
  `hot_place_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `main_address` VARCHAR(255) NOT NULL,
  `sub_address` VARCHAR(255) NOT NULL,
  `zipcode` VARCHAR(5) NOT NULL,
  `content` TEXT NOT NULL,
  `hit` INT NOT NULL,
  `rating` INT NULL DEFAULT NULL,
  `title` VARCHAR(120) NOT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`hot_place_id`),
  INDEX `FK3xc5rq6dqfcrugy5354vq2y4m` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FK3xc5rq6dqfcrugy5354vq2y4m`
    FOREIGN KEY (`member_id`)
    REFERENCES `faraway`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`hot_place_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`hot_place_comment` ;

CREATE TABLE IF NOT EXISTS `faraway`.`hot_place_comment` (
  `hot_place_comment_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `content` TEXT NOT NULL,
  `hotplace_id` BIGINT NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`hot_place_comment_id`),
  INDEX `FK14wljv6ru84u68o1t1dumg55c` (`hotplace_id` ASC) VISIBLE,
  INDEX `FKj8uh6lao23nt49sgpgrkwwh6p` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FK14wljv6ru84u68o1t1dumg55c`
    FOREIGN KEY (`hotplace_id`)
    REFERENCES `faraway`.`hot_place` (`hot_place_id`),
  CONSTRAINT `FKj8uh6lao23nt49sgpgrkwwh6p`
    FOREIGN KEY (`member_id`)
    REFERENCES `faraway`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`hot_place_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`hot_place_image` ;

CREATE TABLE IF NOT EXISTS `faraway`.`hot_place_image` (
  `hot_place_image_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `store_file_name` VARCHAR(255) NULL DEFAULT NULL,
  `upload_file_name` VARCHAR(255) NULL DEFAULT NULL,
  `hot_place_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`hot_place_image_id`),
  INDEX `FK9bxlq16ua2l2ggohjfqx9ynwr` (`hot_place_id` ASC) VISIBLE,
  CONSTRAINT `FK9bxlq16ua2l2ggohjfqx9ynwr`
    FOREIGN KEY (`hot_place_id`)
    REFERENCES `faraway`.`hot_place` (`hot_place_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`hot_place_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`hot_place_like` ;

CREATE TABLE IF NOT EXISTS `faraway`.`hot_place_like` (
  `hot_place_like_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `hot_place_id` BIGINT NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`hot_place_like_id`),
  INDEX `FK9s8x2s5cqnipguqx3kgregbqo` (`hot_place_id` ASC) VISIBLE,
  INDEX `FK7io5l1hdx9cinrkbtfwsmflva` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FK7io5l1hdx9cinrkbtfwsmflva`
    FOREIGN KEY (`member_id`)
    REFERENCES `faraway`.`member` (`member_id`),
  CONSTRAINT `FK9s8x2s5cqnipguqx3kgregbqo`
    FOREIGN KEY (`hot_place_id`)
    REFERENCES `faraway`.`hot_place` (`hot_place_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`plan` ;

CREATE TABLE IF NOT EXISTS `faraway`.`plan` (
  `plan_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `content` TEXT NOT NULL,
  `hit` INT NOT NULL DEFAULT '0',
  `title` VARCHAR(120) NOT NULL,
  `travel_plan` VARCHAR(255) NOT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`plan_id`),
  INDEX `FK8ib1c8odrbn5firtedh1wilgl` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FK8ib1c8odrbn5firtedh1wilgl`
    FOREIGN KEY (`member_id`)
    REFERENCES `faraway`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`plan_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`plan_comment` ;

CREATE TABLE IF NOT EXISTS `faraway`.`plan_comment` (
  `plan_comment_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `content` TEXT NOT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  `plan_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`plan_comment_id`),
  INDEX `FK4l0seyteiytfqb0u693cbtf0o` (`member_id` ASC) VISIBLE,
  INDEX `FKonxf3exneqx7ipapqhw4pj5tx` (`plan_id` ASC) VISIBLE,
  CONSTRAINT `FK4l0seyteiytfqb0u693cbtf0o`
    FOREIGN KEY (`member_id`)
    REFERENCES `faraway`.`member` (`member_id`),
  CONSTRAINT `FKonxf3exneqx7ipapqhw4pj5tx`
    FOREIGN KEY (`plan_id`)
    REFERENCES `faraway`.`plan` (`plan_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`plan_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`plan_like` ;

CREATE TABLE IF NOT EXISTS `faraway`.`plan_like` (
  `post_like_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  `plan_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`post_like_id`),
  INDEX `FKg1a1yi6o1lovraclldh3f6woj` (`member_id` ASC) VISIBLE,
  INDEX `FKjmg7kb2pni0ihcdohn0p03clj` (`plan_id` ASC) VISIBLE,
  CONSTRAINT `FKg1a1yi6o1lovraclldh3f6woj`
    FOREIGN KEY (`member_id`)
    REFERENCES `faraway`.`member` (`member_id`),
  CONSTRAINT `FKjmg7kb2pni0ihcdohn0p03clj`
    FOREIGN KEY (`plan_id`)
    REFERENCES `faraway`.`plan` (`plan_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`post_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`post_comment` ;

CREATE TABLE IF NOT EXISTS `faraway`.`post_comment` (
  `post_comment_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `content` TEXT NOT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  `post_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`post_comment_id`),
  INDEX `FKmboa7oymb913tsn3k1t3i208v` (`member_id` ASC) VISIBLE,
  INDEX `FKna4y825fdc5hw8aow65ijexm0` (`post_id` ASC) VISIBLE,
  CONSTRAINT `FKmboa7oymb913tsn3k1t3i208v`
    FOREIGN KEY (`member_id`)
    REFERENCES `faraway`.`member` (`member_id`),
  CONSTRAINT `FKna4y825fdc5hw8aow65ijexm0`
    FOREIGN KEY (`post_id`)
    REFERENCES `faraway`.`post` (`post_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `faraway`.`post_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `faraway`.`post_like` ;

CREATE TABLE IF NOT EXISTS `faraway`.`post_like` (
  `post_like_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `modified_date` DATETIME(6) NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  `post_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`post_like_id`),
  INDEX `FKqjxwr6kkv6pw2e4pwy4yktxyk` (`member_id` ASC) VISIBLE,
  INDEX `FKj7iy0k7n3d0vkh8o7ibjna884` (`post_id` ASC) VISIBLE,
  CONSTRAINT `FKj7iy0k7n3d0vkh8o7ibjna884`
    FOREIGN KEY (`post_id`)
    REFERENCES `faraway`.`post` (`post_id`),
  CONSTRAINT `FKqjxwr6kkv6pw2e4pwy4yktxyk`
    FOREIGN KEY (`member_id`)
    REFERENCES `faraway`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
