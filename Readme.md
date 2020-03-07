finalExam proj


------------ s q l --------------------

CREATE USER IF NOT EXISTS FEuser IDENTIFIED BY 'pass';

DROP DATABASE IF EXISTS finalExam;
CREATE DATABASE finalExam DEFAULT CHARACTER SET utf8;

USE finalExam;

GRANT ALL ON finalExam.* TO 'FEuser'@'%';

FLUSH PRIVILEGES;