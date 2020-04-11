create table persons_group (
id                  integer         not null auto_increment,
title               varchar(255)    not null,
administrator_id    integer         not null,
description         varchar(255)    not null,

primary key (id),
foreign key (administrator_id) references person(id)
);