create table work_time (
id                      integer         not null auto_increment,
person_id               integer   not null,
work_date               timestamp       not null,
works_from              time            not null,
works_to                time            not null,
is_working_from_home    char(1)         default 0,

primary key (id),
foreign key (person_id) references person(id)
);