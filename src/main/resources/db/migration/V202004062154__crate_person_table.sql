create table person (
id          integer not null auto_increment,
name        varchar(50),
surname     varchar(50),
username    varchar(255),
email       varchar(50),
address     varchar(255),
phone_number varchar(50),
password    varchar(50),
bios        varchar(255),

primary key (id)
);