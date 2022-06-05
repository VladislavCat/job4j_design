CREATE TABLE type(
	id_type SERIAL PRIMARY KEY,
	name VARCHAR(30)
);
CREATE TABLE product(
	id_product SERIAL PRIMARY KEY,
	name VARCHAR(30),
	type_id INT REFERENCES TYPE(id_type),
	expired_date DATE,
	price FLOAT
);

INSERT INTO type(name) VALUES('СЫР'),('МОЛОКО'),('МЯСО'),
('КРУПЫ'),('ХОЗТОВАРЫ'),('ХЛЕБОБУЛОЧНЫЕ ИЗДЕЛИЯ');
INSERT INTO product(name, type_id, expired_date, price) VALUES
('Козий сыр', 1, '11-08-2022', 230.33),
('Канинское молоко', 2, '12-06-2022', 67.99),
('Говядина' , 3, '02-06-2022', 300.33),
('Рис', 4, '02-10-2023', 111),
('Бельгийский сыр', 1, '21-07-2022', 423),
('Влажные салфетки' , 5, '28-12-2022', 162.09),
('Буханка ржаного хлеба', 6, '15-06-2022', 38.11),
('Сыр с плесенью', 1, '01-08-2022', 208.74),
('Свинина в кляре' , 3, '09-06-2022', 293.98),
('Гречка', 4, '01-01-2024', 57.94),
('Кефир', 2, '11-06-2022', 61.92),
('Ряженка' , 2, '08-06-2022', 82),
('Рязанский сыр', 1, '28-08-2022', 202.33),
('Лимнинское молоко', 2, '13-06-2022', 87.99),
('Куриная грудка' , 3, '02-06-2022', 300.33),
('Сливочное мороженое', 2, '30-06-2022', 120.23),
('Шоколадное мороженое', 2, '27-06-2022', 130.10),
('Хлеб белый', 5, '29-05-2022', 40.32);

SELECT p.name AS Название_продукта 
FROM product AS p
JOIN type AS t
ON p.type_id = t.id_type
WHERE t.name LIKE '%СЫР%';

SELECT name AS Название_продукта
FROM product
WHERE name LIKE '%мороженное%';

SELECT name AS Название_продукта
FROM product
WHERE expired_date < CURRENT_DATE;

SELECT name FROM product WHERE price = MAX(price);

SELECT t.name, COUNT(t.id_type = p.type_id)
FROM type AS t
JOIN product AS p
ON t.id_type = p.type_id
GROUP BY t.name;

SELECT p.name AS Название_продукта 
FROM product AS p
JOIN type AS t
ON p.type_id = t.id_type
WHERE t.name LIKE '%СЫР%' OR t.name LIKE '%МОЛОКО%';

SELECT t.name, COUNT(t.id_type = p.type_id)
FROM type AS t
JOIN product AS p
ON t.id_type = p.type_id
GROUP BY t.name
HAVING COUNT(t.id_type = p.type_id) < 4;

SELECT p.name, p.expired_date,  p.price, t.name
FROM product AS p
JOIN type AS t
ON t.id_type = p.type_id;
