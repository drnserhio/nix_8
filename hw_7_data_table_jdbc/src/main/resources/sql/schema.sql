create database if not exists hw_7;

use hw_7;

drop table if exists relations;
drop table if exists employee;
drop table if exists department;


create table if not exists employee(
    id bigint primary key auto_increment,
    dateCreate datetime null,
    dateUpdate datetime null,
    firstname varchar(40) not null,
    lastname varchar(40) not null,
    username varchar(20) not null

);

create table if not exists department(
    id bigint primary key auto_increment,
    dateCreate datetime null,
    dateUpdate datetime null,
    nameCompany varchar(40) not null ,
    address varchar(40) not null
);

create table if not exists relations(
    department_id bigint not null,
    employee_id bigint not null,
    primary key (department_id, employee_id),
    foreign key (department_id) references department(id) on delete cascade,
    foreign key (employee_id) references employee(id) on delete cascade
);
