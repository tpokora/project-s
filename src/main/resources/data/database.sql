CREATE DATABASE users_database;

USE users_database;

CREATE TABLE USERS(
    ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(32) NOT NULL,
    PASSWORD VARCHAR(32) NOT NULL,
    EMAIL VARCHAR(255) NOT NULL)
ENGINE=InnoDB;

INSERT INTO USERS(NAME, PASSWORD, EMAIL) VALUES ('Admin', 'admin', 'email@email.com');

