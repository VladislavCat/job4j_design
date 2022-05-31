create table country(
	id serial primary key,
	country_name varchar(255),
	flag_id int references flag(id) unique
);

create table flag(
	id serial primary key,
	flag_name varchar(255)
);
