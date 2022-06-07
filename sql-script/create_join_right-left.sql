create table departaments(
    id_departaments serial primary key,
    name varchar(30)
);
create table employees(
    id_employees serial primary key,
    name varchar(30),
    departaments_id int references departaments(id_departaments)
);
create table teens(
    id_teens serial primary key,
    name varchar(30),
    gender varchar(30)
);


