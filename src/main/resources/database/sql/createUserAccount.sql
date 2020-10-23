create user 'developer'@'localhost' identified by '1234';
grant all privileges on shop.* to 'developer'@'localhost';
show grants for 'developer'@'localhost';

select * from items;
insert  into shop.items(item_name, price, delivery_option,display_option,stock,manufacturer, description) values 
(123,23,"ShippingCostInclude","NONE", 2, 123, 123);
delete from items where item_name = 123;