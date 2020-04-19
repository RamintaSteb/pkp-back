create table task (
id              integer         not null auto_increment,
person_id       integer,
group_id        integer,
board_id        integer         not null,
time            timestamp       not null,
title           varchar(255)    not null,
description     varchar(255)    not null,
status          varchar(50)     not null,
start_date      timestamp       not null,
deadline_date   timestamp       not null,
creator_id      integer         not null,

primary key (id),
foreign key (person_id) references person(id),
foreign key (group_id) references persons_group(id),
foreign key (board_id) references board(id),
foreign key (creator_id) references person(id)
);