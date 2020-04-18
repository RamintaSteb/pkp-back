create table board_person (
id          integer         not null auto_increment,
board_id    integer         not null,
person_id   integer         not null,

primary key (id),
foreign key (board_id) references board(id),
foreign key (person_id) references person(id)
);