insert into users(user_id, user_name,password,phone_number,reg_date,update_date,user_type) values ('test','test','test','010-1111-2222',now(),now(), 'BUYER');
insert into users(user_id, user_name,password,phone_number,reg_date,update_date,user_type) values ('test2','test2','test2','010-1111-2222',now(),now(),'BUYER');
insert into users(user_id, user_name,password,phone_number,reg_date,update_date,user_type) values ('admin','admin','admin','010-1111-2222',now(),now(),'SELLER');
select * from users;


insert into items(item_name, price, delivery_option, display_option,stock,manufacturer, description, img_src) values ('16인치 맥북',3000000,'ShippingCostInclude','DISPLAY',100,'Apple','16인치 노트북',"product01.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description,img_src) values ('젠하이저 헤드폰',1000000,'ShippingCostNotInclude','DISPLAY',100,'젠하이저','고퀄리티 헤드폰',"product02.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('13인치 맥북',2000000,'ShippingCostInclude','DISPLAY',100,'Apple','13인치 노크북',"product03.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('소니 테블릿PC',100000,'ShippingCostInclude','DISPLAY',100,'Sony','가성비 테블릿 pc',"product04.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('소니 헤드폰',200000,'ShippingCostNotInclude','DISPLAY',100,'Sony','소니 헤드폰',"product05.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('갤럭시 노트20',1500000,'ShippingCostNotInclude','DISPLAY',100,'Samsung','갤럭시의 최신 스마트폰입니다.',"product07.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('Asus 노트북',1000000,'ShippingCostNotInclude','DISPLAY',100,'Asus','Asus의 가성비 노트북.',"product08.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('Rekam 디지털 카메라',100000,'ShippingCostNotInclude','DISPLAY',100,'Rekam','Rekam의 레트로 디카.',"product09.png");
insert into items(item_name, price, delivery_option, display_option,stock,manufacturer, description, img_src) values ('16인치 맥북',3000000,'ShippingCostInclude','DISPLAY',100,'Apple','16인치 노트북',"product01.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description,img_src) values ('젠하이저 헤드폰',1000000,'ShippingCostNotInclude','DISPLAY',100,'젠하이저','고퀄리티 헤드폰',"product02.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('13인치 맥북',2000000,'ShippingCostInclude','DISPLAY',100,'Apple','13인치 노크북',"product03.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('소니 테블릿PC',100000,'ShippingCostInclude','DISPLAY',100,'Sony','가성비 테블릿 pc',"product04.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('소니 헤드폰',200000,'ShippingCostNotInclude','DISPLAY',100,'Sony','소니 헤드폰',"product05.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('갤럭시 노트20',1500000,'ShippingCostNotInclude','DISPLAY',100,'Samsung','갤럭시의 최신 스마트폰입니다.',"product07.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('Asus 노트북',1000000,'ShippingCostNotInclude','DISPLAY',100,'Asus','Asus의 가성비 노트북.',"product08.png");
insert into items(item_name, price, delivery_option, display_option, stock,manufacturer, description, img_src) values ('Rekam 디지털 카메라',100000,'ShippingCostNotInclude','DISPLAY',100,'Rekam','Rekam의 레트로 디카.',"product09.png");

select * from items;

commit;
