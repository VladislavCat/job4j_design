insert into departaments(name) values('Первый департамент'),('Второй департамент'),('Третий департамент');
insert into employees(name, departaments_id) values('Сема', 1), ('Алексей', 1),('Григорий', 2),('Арсений', 2),
('Михаил', 3),('Владислав', 3);

select *
from departaments AS d
left join employees AS e
on  d.id_departaments = e.departaments_id
where e.name is null;

select e.name as Имя_сотрудника, d.name AS Название_департамента
from employees AS e
left join departaments AS d
on e.departaments_id = d.id_departaments;

select e.name as Имя_сотрудника, d.name AS Название_департамента
from departaments AS d
right join employees AS e
on d.id_departaments = e.departaments_id;

insert into teens(name, gender) values('Маша', 'Ж'),('Наташа', 'Ж'),
('Галя', 'Ж'),('Лана', 'Ж'),('Александа', 'Ж'),('Вася', 'М'),('Миша', 'М'),('Сема', 'М'),('Петр', 'М'),('Григорий', 'М');

select m.name, w.name
from teens AS m
cross join teens AS w
where m.gender like '%М%' and w.gender like '%Ж%';