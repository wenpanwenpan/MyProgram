create table drug(
did varchar2(50) primary key,
dname varchar2(50) not null,
ddate varchar2(50) not null,
daddress varchar2(50),
pid varchar2(50) foreign key,
dgrade varchar2(50) not null,
dperson varchar2(50) not null,
dnote clob
);
commit;

insert into drug(did,dname,ddate,daddress,pid,dgrade,dperson,dnote) values(
'001','止痛片'，'2018-03-10','四川成都大和制药厂','101','常规药品','所有人群','这是一款止痛的良药。。');
insert into drug(did,dname,ddate,daddress,pid,dgrade,dperson,dnote) values(
'002','晕车药'，'2018-06-10','北京朝阳区888号制药房','102','常规药品','所有人群','这是一款治疗晕车呕吐的的良药。。');
药品表：
药品id，药品名称，药品简介，药品入库日期，药品生产地，药品供货商id,药品等级，实用人群，


供货商表provider：
供货商id，供货商名称，供货商地址，联系电话，邮箱，公司等级
create table provider(
pid varchar2(50) primary key,
pname varchar2(50) not null,
paddress varchar2(50) not null,
ptel varchar2(50),
pemail varchar2(50),
pgrade varchar2(50)
);

insert into provider(pid,pname,paddress,ptel,pemail,pgrade) values('101','大和制药厂','四川省成都市','13548115681','wenpan@edu.com','私有企业');
insert into provider(pid,pname,paddress,ptel,pemail,pgrade) values('102','888号制药厂','北京朝阳区','13548115681','wenpan@edu.com','国有企业');
insert into provider(pid,pname,paddress,ptel,pemail,pgrade) values('103','四川遂宁百合制药厂','四川遂宁','13548968475','wenpan@edu.com','一级企业');

//过期药瓶出库
create table drugout(
did varchar2(50) primary key,
dname varchar2(50) not null,
ddate varchar2(50) not null,
daddress varchar2(50),
pid varchar2(50) foreign key,
dgrade varchar2(50) not null,
dperson varchar2(50) not null,
dnote varchar2(50)
);



