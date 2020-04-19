create table person (
id              integer         not null auto_increment,
name            varchar(50)     not null,
surname         varchar(50)     not null,
username        varchar(255)    not null,
email           varchar(50)     not null,
address         varchar(255),
phone_number    varchar(50),
password        integer         not null,
bios            varchar(255),

primary key (id)
);