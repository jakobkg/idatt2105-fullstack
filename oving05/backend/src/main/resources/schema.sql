drop table if exists calculations;
drop table if exists users;

CREATE TABLE calculations (
    ID INTEGER auto_increment, 
    username VARCHAR(128) NOT NULL, 
    expression VARCHAR(128) NOT NULL,
    result double NOT NULL,
    primary key(ID)
);

create table users (
    username varchar(255) not null,
    password varchar(255)
);