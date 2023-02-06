--liquibase formatted sql

--changeset piomin:1
create table person (
  id serial primary key,
  name varchar(255),
  gender varchar(255),
  age int
);