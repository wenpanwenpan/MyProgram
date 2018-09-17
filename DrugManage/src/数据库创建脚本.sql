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
'001','ֹʹƬ'��'2018-03-10','�Ĵ��ɶ������ҩ��','101','����ҩƷ','������Ⱥ','����һ��ֹʹ����ҩ����');
insert into drug(did,dname,ddate,daddress,pid,dgrade,dperson,dnote) values(
'002','�γ�ҩ'��'2018-06-10','����������888����ҩ��','102','����ҩƷ','������Ⱥ','����һ�������γ�Ż�µĵ���ҩ����');
ҩƷ��
ҩƷid��ҩƷ���ƣ�ҩƷ��飬ҩƷ������ڣ�ҩƷ�����أ�ҩƷ������id,ҩƷ�ȼ���ʵ����Ⱥ��


�����̱�provider��
������id�����������ƣ������̵�ַ����ϵ�绰�����䣬��˾�ȼ�
create table provider(
pid varchar2(50) primary key,
pname varchar2(50) not null,
paddress varchar2(50) not null,
ptel varchar2(50),
pemail varchar2(50),
pgrade varchar2(50)
);

insert into provider(pid,pname,paddress,ptel,pemail,pgrade) values('101','�����ҩ��','�Ĵ�ʡ�ɶ���','13548115681','wenpan@edu.com','˽����ҵ');
insert into provider(pid,pname,paddress,ptel,pemail,pgrade) values('102','888����ҩ��','����������','13548115681','wenpan@edu.com','������ҵ');
insert into provider(pid,pname,paddress,ptel,pemail,pgrade) values('103','�Ĵ������ٺ���ҩ��','�Ĵ�����','13548968475','wenpan@edu.com','һ����ҵ');

//����ҩƿ����
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



