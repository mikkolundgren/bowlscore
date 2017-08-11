CREATE SEQUENCE id_seq START WITH 1 INCREMENT BY 1;
create table scores (id bigint, name varchar(80), score int, date date, timestamp timestamp default now());
create table bowlers (id bigint, name varchar(80), paidwhen date);