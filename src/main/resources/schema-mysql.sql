drop table if exists atransaction;
drop table if exists account;
drop table if exists customer;

create table customer (
  customer_id bigint primary key not null auto_increment,
  first_name varchar (50) not null,
  last_name varchar (50) not null,
  age int
);

create table account (
 account_id bigint primary key not null auto_increment,
 customer_id bigint,
 amount double,
 foreign key(customer_id)
              references customer(customer_id)
              on delete cascade
              on update cascade
);

create table atransaction (
 transaction_id bigint primary key not null auto_increment,
 tx_date date,
 src_account_id bigint,
 dst_account_id bigint,
 amount double,
 transaction_type varchar (10),
 foreign key (dst_account_id)
              references account(account_id)
              on delete cascade
              on update cascade,
 foreign key (src_account_id)
              references account(account_id)
              on delete cascade
              on update cascade
);