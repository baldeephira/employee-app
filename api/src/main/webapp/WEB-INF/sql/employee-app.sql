drop table if exists employee;
drop table if exists department;
drop table if exists company;
drop table if exists contactinfo;
drop table if exists address;

create table address (
	id				bigint unsigned auto_increment,
	street			varchar(255),
	city			varchar(100),
	state			varchar(100),
	zip				varchar(20),
	country			varchar(100),
	primary key (id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;

create table contactinfo (
	id				bigint unsigned auto_increment,
	phone			varchar(20),
	fax				varchar(20),
	email			varchar(100),
	website			varchar(100),
	primary key (id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;

create table company (
	id          	bigint unsigned auto_increment,
	name			varchar(100) not null,
	industry		varchar(100),
	billingaddr		bigint unsigned,
	shippingaddr	bigint unsigned,
	contactinfo		bigint unsigned,
	created			datetime not null,
	modified		datetime not null,
	createdby		varchar(100) not null,
	modifiedby		varchar(100) not null,
	primary key (id),
	constraint fk_company_baddr foreign key (billingaddr) references address(id),
	constraint fk_company_saddr foreign key (shippingaddr) references address(id),
	constraint fk_company_cinfo foreign key (contactinfo) references contactinfo(id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;

create table department (
	id				bigint unsigned auto_increment,
	companyid		bigint unsigned not null,
	name			varchar(100) not null,
	billingaddr		bigint unsigned,
	shippingaddr	bigint unsigned,
	contactinfo		bigint unsigned,
	created			datetime not null,
	modified		datetime not null,
	createdby		varchar(100) not null,
	modifiedby		varchar(100) not null,
	primary key (id),
	unique key uq_department (companyid, name),
	constraint fk_department_compy foreign key (companyid) references company(id),
	constraint fk_department_baddr foreign key (billingaddr) references address(id),
	constraint fk_department_saddr foreign key (shippingaddr) references address(id),
	constraint fk_department_cinfo foreign key (contactinfo) references contactinfo(id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;
create index idx_department on department(companyid);

create table employee (
	id				bigint unsigned auto_increment,
	companyid		bigint unsigned not null,
	departmentid	bigint unsigned,
	name			varchar(100) not null,
	managerid		bigint unsigned,
	salutation		varchar(10),
	sex				varchar(10),
	dob				date,
	title			varchar(30),
	addr			bigint unsigned,
	contactinfo		bigint unsigned,
	created			datetime not null,
	modified		datetime not null,
	createdby		varchar(100) not null,
	modifiedby		varchar(100) not null,
	primary key (id),
	constraint fk_employee_compy foreign key (companyid) references company(id),
	constraint fk_employee_dept foreign key (departmentid) references department(id),
	constraint fk_employee_mgr foreign key (managerid) references employee(id),
	constraint fk_employee_addr foreign key (addr) references address(id),
	constraint fk_employee_cinfo foreign key (contactinfo) references contactinfo(id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;
create index idx_employee on employee(companyid);
