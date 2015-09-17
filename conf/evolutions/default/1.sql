# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table jsonnoid (
  data                      jsonb)
;

create table jsonperson (
  id                        bigserial not null,
  data                      jsonb,
  constraint pk_jsonperson primary key (id))
;

create table person (
  id                        bigserial not null,
  name                      varchar(255),
  constraint pk_person primary key (id))
;




# --- !Downs

drop table if exists jsonnoid cascade;

drop table if exists jsonperson cascade;

drop table if exists person cascade;

