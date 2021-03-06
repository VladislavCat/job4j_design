insert into engine(name) values('L-320'),('N12M1'),('SW22RTM2'),('TRE211'),('UTM55');
insert into carcase(name) values('logan'), ('UAZ'), ('chevrolet-sp2'), ('ford model t'),('Alfa Romeo 11');
insert into transmission(name) values('Default transmission'),('TR3'),('TransmissionSP2'),('RbtTRM'), ('TS9500'),('TPL11');
insert into auto(name, engine_id, carcase_id, transmission_id) values('Reno logan', 2, 1, 2),('Lada Granta', 1, 2, 1),
('Chevrolet Alma', 4, 3, 3);
insert into auto(name, engine_id, carcase_id) values('Reno logan', 2, 2),('Lada Granta', 2, 1);
insert into auto(name, engine_id, transmission_id) values('Porshe 911', 3, 5),('Lada Estate', 2, 4);
insert into auto(name, carcase_id, transmission_id) values('Pejo Sele', 5, 4),('Lada Simple', 1, 2);

select a.name as Название_машины,
e.name as Название_двигателя, c.name as Название_кузова,
t.name as Название_коробки_передач
from auto as a
left join engine as e
on a.engine_id = e.id_engine
left join carcase as c
on a.carcase_id = c.id_carcase
left join transmission as t
on a.transmission_id = t.id_transmission
where a.name is not null;

select e.name as Название_двигателя
from engine as e
left join auto as a
on e.id_engine = a.engine_id
where a.name is null;

select c.name as Название_каркаса
from carcase as c
left join auto as a
on c.id_carcase = a.carcase_id;
where a.name is null;

select t.name as Название_коробки_передач
from transmission as t
left join auto as a
on t.id_transmission = a.transmission_id
where a.name is null;
