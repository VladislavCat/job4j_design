create table category(
	id serial primary key,
	category_name varchar(255)
);

create table product(
	id serial primary key,
	product_name varchar(255),
	category_id references category(id)
);