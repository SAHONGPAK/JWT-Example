DROP DATABASE IF EXISTS `jwt_example`;
CREATE DATABASE `jwt_example`;
USE `jwt_example`;

CREATE TABLE `role_list` (
	`role_name` VARCHAR(10) PRIMARY KEY
);

INSERT INTO `role_list` VALUE ("일반");
INSERT INTO `role_list` VALUE ("관리자");

CREATE TABLE `user` (
	`email` VARCHAR(100) PRIMARY KEY,
	`password` CHAR(128), # SHA 512
    `name` VARCHAR(100),
    `role` VARCHAR(10),
    FOREIGN KEY(`role`) REFERENCES `role_list`(`role_name`)
);

INSERT INTO `user` 
VALUE (
	"user@user.com", 
    "b14361404c078ffd549c03db443c3fede2f3e534d73f78f77301ed97d4a436a9fd9db05ee8b325c0ad36438b43fec8510c204fc1c1edb21d0941c00e9e2c1ce2", # user
    "유저1",
    "일반"
);

INSERT INTO `user`
VALUE (
	"admin@admin.com",
    "", # admin
    "관리자1",
    "관리자"
);

CREATE TABLE `token_status` (
	`user_email` VARCHAR(100),
    `hashed_token` CHAR(128), # SHA 512
    `expiration` TIMESTAMP,
    `valid` bOOLEAN,
	CONSTRAINT token_pk PRIMARY KEY(`user_email`, `hashed_token`)
);


SELECT * FROM `user`;
SELECT * FROM `token_status`;