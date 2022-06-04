create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
	people_id int references people(id),
    device_id int references devices(id)
);

INSERT INTO devices(name, price) VALUES('Mobile', 10),
('Notebook', 15),
('PC', 30),
('TV', 80);
INSERT INTO people(name) VALUES('Гриша'),
('Сема'),
('Петр'),
('Влад'),
('Иван');
INSERT INTO devices_people(people_id, device_id) VALUES(1, 1),(1, 4),(2, 3),(2, 1),(3, 3),(3, 2),(4, 1),(4, 3);

SELECT p.name, 
AVG(d.price) AS Средняя_цена_гаджетов
FROM devices_people AS dp
JOIN devices AS d
ON dp.device_id = d.id
JOIN people AS p
ON dp.people_id = p.id
GROUP BY p.name
HAVING AVG(d.price) > 22;
