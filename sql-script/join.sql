create table category(
	id_category SERIAL PRIMARY KEY,
	category_name VARCHAR(30)
);
CREATE TABLE material(
	material_id SERIAL PRIMARY KEY,
	material_name VARCHAR(30),
	material_price INT,
	category_id INT REFERENCES category(id_category)
);
INSERT INTO category(category_name) VALUES('Крепежные материалы'),
('Клеящие материалы'),
('Строительные материалы');
INSERT INTO material(material_name, material_price, category_id) VALUES('Раствор для бетона', 50, 3),
('Гвозди 50 шт.', 5, 1),
('Кирпичи из красной глины', 10, 3),
('Доски из дуба', 30, 3),
('Клей по дереву', 10, 2),
('Болты и гайки 50 шт', 8, 1),
('Клей для полимеров' , 7, 2);

SELECT material_name AS Название_материала, 
category_name AS Название_категории,
material_price AS Цена_материала
FROM material AS m 
JOIN category AS c
ON m.category_id = c.id_category;

SELECT material_name AS Название_материала, 
category_name AS Название_категории,
material_price AS Цена_материала
FROM material AS m 
JOIN category AS c
ON m.category_id = c.id_category
WHERE material_price < 20 AND category_name LIKE('%Клея%');

SELECT material_name AS Название_материала, 
category_name AS Название_категории
FROM material AS m 
JOIN category AS c
ON m.category_id = c.id_category
WHERE category_name LIKE('%Строительные%');