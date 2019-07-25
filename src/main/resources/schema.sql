 drop table account if exists;
 drop table account_owner if exists;
 drop table account_transaction if exists;
 drop table account_type if exists;
 drop table transaction_type if exists;

 create table account (id bigint generated by default as identity, account_number varchar(255), name varchar(255), account_type integer not null, primary key (id));
 create table account_transaction (id varchar(255) generated by default as identity, amount decimal(19,2), date date, description varchar(255), account_id bigint not null, transaction_type bigint not null, primary key (id));
 create table account_type (id integer generated by default as identity, type varchar(255), primary key (id));
 create table transaction_type (id bigint generated by default as identity, type varchar(255), primary key (id));