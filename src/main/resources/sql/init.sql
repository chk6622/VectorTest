drop schema if exists `test`;
create schema `test`;
use `test`;
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User`
(
    `email`   varchar(100) primary key NOT NULL,
    `password` varchar(100)    NOT NULL,
    `firstName`       varchar(100),
    `lastName`        varchar(100),
)
