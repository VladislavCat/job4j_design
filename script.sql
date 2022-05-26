create table product (
    id serial primary key, 
    nameProduct text,
	price varchar,
	weight varchar
);
insert into product(nameProduct, price, weight) values('milk,', '70', '1000');
select * from product;
update product set nameProduct = 'milk';
delete from product;