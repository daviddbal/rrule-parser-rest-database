CREATE SCHEMA `rrule` ;
CREATE TABLE `rrule`.`history` (
  `uid` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `rruleContent` VARCHAR(80) NOT NULL,
  `dtstartContent` VARCHAR(45) NOT NULL,
  `maxRecurrences` INT UNSIGNED NOT NULL,
  `created` DATETIME NOT NULL,
  PRIMARY KEY (`uid`));