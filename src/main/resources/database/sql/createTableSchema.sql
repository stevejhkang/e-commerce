drop table if exists orderitems;
drop table if exists orders;
drop table if exists items;
drop table if exists deliveries;
drop table if exists users;


create table users(
	user_sn int(11) not null auto_increment primary key,
    user_id varchar(32) not null unique, 
    password varchar(64) not null,
    user_name varchar(32) not null,
    phone_number varchar(32) not null,
    reg_date timestamp,
    update_date timestamp,
    user_type varchar(32) not null
);

create table deliveries (
	delivery_sn int(11) not null auto_increment primary key,
    receiver_name varchar(32) not null,
    address varchar(64) not null,
    phone_number1 varchar(32) not null,
    user_sn int(11) not null,
    
    foreign key(user_sn) references users(user_sn) 
);

create table items (
	item_sn int(11) not null auto_increment primary key,
    item_name varchar(64) not null,
    price int(11) not null,
    delivery_option varchar(32) not null,
    display_option varchar(32) not null,
    
    stock int(11) not null,
    manufacturer varchar(64),
    description varchar(1024) not null,
    img_src varchar(512)
);


create table orders(
	order_sn int(11) not null auto_increment primary key,
    order_id varchar(32) not null,
    order_date timestamp not null,
    order_status varchar(32) not null,
    price int(11) not null,
	delivery_sn int(11) not null,
    user_sn int(11) not null,
    
    foreign key (delivery_sn) references deliveries(delivery_sn),
    foreign key (user_sn) references users(user_sn)
);


create table orderitems(
	orderitem_sn int(11) not null auto_increment primary key,
    order_sn int(11) not null,
    item_sn int(11) not null,
    quantity int(11) not null,
    foreign key (order_sn) references orders(order_sn),
    foreign key (item_sn) references items(item_sn)
);


select * from users;
select * from items;


