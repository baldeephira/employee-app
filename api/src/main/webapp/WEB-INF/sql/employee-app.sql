drop table if exists company_cinfo;
drop table if exists department_cinfo;
drop table if exists employee_cinfo;
drop table if exists contactinfo;
drop table if exists employee;
drop table if exists department;
drop table if exists company;

create table contactinfo (
    id              bigint unsigned auto_increment,
    phone           varchar(20),
    fax             varchar(20),
    email           varchar(100),
    website         varchar(100),
    primary key (id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;

create table company (
    id              bigint unsigned auto_increment,
    name            varchar(100) not null,
    industry        varchar(100),
    billingaddr     varchar(255),
    shippingaddr    varchar(255),
    created         datetime not null,
    modified        datetime not null,
    createdby       varchar(100) not null,
    modifiedby      varchar(100) not null,
    primary key (id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;

create table company_cinfo (
    companyid       bigint unsigned not null unique,
    contactinfoid   bigint unsigned not null unique,
    primary key (companyid),
    constraint fk_company_cinfo_company foreign key (companyid) references company(id),
    constraint fk_company_cinfo_contact foreign key (contactinfoid) references contactinfo(id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;

create table department (
    id              bigint unsigned auto_increment,
    companyid       bigint unsigned not null,
    name            varchar(100) not null,
    billingaddr     varchar(255),
    shippingaddr    varchar(255),
    created         datetime not null,
    modified        datetime not null,
    createdby       varchar(100) not null,
    modifiedby      varchar(100) not null,
    primary key (id),
    unique key uq_department (companyid, name),
    constraint fk_department_compy foreign key (companyid) references company(id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;
create index idx_department on department(companyid);

create table department_cinfo (
    departmentid    bigint unsigned not null unique,
    contactinfoid   bigint unsigned not null unique,
    primary key (departmentid),
    constraint fk_department_cinfo_dept foreign key (departmentid) references department(id),
    constraint fk_department_cinfo_contact foreign key (contactinfoid) references contactinfo(id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;

create table employee (
    id              bigint unsigned auto_increment,
    companyid       bigint unsigned not null,
    departmentid    bigint unsigned,
    name            varchar(100) not null,
    managerid       bigint unsigned,
    salutation      varchar(10),
    sex             varchar(10),
    dob             date,
    title           varchar(30),
    addr            varchar(255),
    created         datetime not null,
    modified        datetime not null,
    createdby       varchar(100) not null,
    modifiedby      varchar(100) not null,
    primary key (id),
    constraint fk_employee_compy foreign key (companyid) references company(id),
    constraint fk_employee_dept foreign key (departmentid) references department(id),
    constraint fk_employee_mgr foreign key (managerid) references employee(id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;
create index idx_employee on employee(companyid);

create table employee_cinfo (
    employeeid      bigint unsigned not null unique,
    contactinfoid   bigint unsigned not null unique,
    primary key (employeeid),
    constraint fk_employee_cinfo_employee foreign key (employeeid) references employee(id),
    constraint fk_employee_cinfo_contact foreign key (contactinfoid) references contactinfo(id)
) engine=InnoDB, default character set utf8, collate utf8_general_ci;
