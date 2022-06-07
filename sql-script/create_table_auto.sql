create table engine(
    id_engine serial primary key,
    name varchar(45)
);
create table carcase(
    id_carcase serial primary key,
    name varchar(45)
);
create table transmission(
    id_transmission serial primary key,
    name varchar(45)
);
create table auto(
    id_auto serial primary key,
    name varchar(35),
    engine_id int references engine(id_engine),
    carcase_id int references carcase(id_carcase),
    transmission_id int references transmission(id_transmission)
);