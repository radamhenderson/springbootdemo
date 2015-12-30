DROP TABLE IF EXISTS person;
CREATE TABLE person (
id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
email VARCHAR(50),
first_name VARCHAR(30),
last_name VARCHAR(30),
prefix VARCHAR(30),
phone  VARCHAR(30),
fax    VARCHAR(30),
title  VARCHAR(30),
company VARCHAR(100),
company_url varchar(255)
);