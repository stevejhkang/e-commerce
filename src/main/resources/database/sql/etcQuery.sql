commit;
select * from users;

update shop.users set user_type = 'ROLE_SELLER' where user_sn=3;
commit; 

select * from items;

delete from orders where order_sn=4;
select * from orderitems;
select * from items order by item_sn desc;


select * from deliveries;
update deliveries set user_sn = 0 where delivery_sn=3;

select * from users;
insert into deliveries(receiver_name, address, phone_number1, user_sn) values ("김민수","서울시 강남구 봉은사로22길 2 301호","010-8888-8888",0);


