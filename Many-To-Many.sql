create table markets(
	id serial primary key, 
	market_name varchar(255)
);

create table products(
	id serial primary key,
	product_name varchar(255)
);

create table markets_products(
	id serial primary key,
	market_id references markets(id),
	product_id references products(id)
);