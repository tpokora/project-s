CREATE DATABASE users_database;

USE users_database;

CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `PASSWORD` varchar(64) COLLATE utf8_polish_ci NOT NULL,
  `EMAIL` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `ROLE` varchar(45) COLLATE utf8_polish_ci DEFAULT 'ROLE_USER',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME_UNIQUE` (`USERNAME`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;


INSERT INTO USERS(USERNAME, PASSWORD, EMAIL, ROLE)
    VALUES ('admin', '$2a$10$RiDTA930W5o8lpeNbQc1/.1jDubtN7gLDmzUR4OrhMob8BDuW54J6', 'email@email.com', 'ROLE_ADMIN');

