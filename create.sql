create table rules(
	id serial primary key,
	rule_text text
);
create table role(
	id serial primary key,
	name_role varchar(255)
);
create table rules_roles(
	id serial primary key,
	id_role int references role(id),
	id_rules int references rules(id)
);
create table category(
	id serial primary key,
	name_category varchar(255)
);
create table state(
	id serial primary key,
	state_name varchar(255)
);
create table item(
	id serial primary key,
	item_name varchar(255),
	item_category int references category(id),
	item_state int references state(id)
);
create table users(
	id serial primary key,
	name_user varchar(255),
	role_user int references role(id),
	item_user int references item(id)
);
create table comments(
	id serial primary key,
	text_comment text,
	comment_item int references item(id)
);
create table attachs(
	id serial primary key,
	path_file text,
	attach_item int references item(id)
);