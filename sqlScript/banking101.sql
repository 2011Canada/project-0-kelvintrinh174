drop schema if exists banking101 cascade;
create schema banking101;
set schema 'banking101';


create type "user-status" as enum ('PENDING','ACTIVE','INACTIVE','CLOSED');

create table "user"(
	  "user_id" serial primary key,
	  "email" text unique not null,
	  "first_name" text,
	  "last_name" text,
	  "password" text not null,
	  "isCustomer" boolean not null,
	  "user_status" "user-status" not null 
);

create type "banking-status" as enum('PENDING','ACTIVE','CLOSED');

create table "banking_account"(
		"bank_id" serial primary key,
		"customer_id" int unique references "user"("user_id"),
		"mailing_address" text not null,
		"pending_transaction" boolean not null,
		"banking_status" "banking-status" not null,
		"initial_deposit" numeric(15,2) not null check(initial_deposit >=0)
);

create table "chequing_account"(
		"ca_account_number" text not null,
		"bank_id" int unique references "banking_account"("bank_id"),
		"ca_balance" numeric(15,2) not null check(ca_balance >=0),
	    constraint "chequing_account_pk" primary key ("bank_id","ca_account_number")
);

create table "saving_account"(
		"sa_account_number" text not null,
		"bank_id" int unique references "banking_account"("bank_id"),
		"sa_balance" numeric(15,2) not null check(sa_balance >=0),
	    constraint "saving_account_pk" primary key ("bank_id","sa_account_number")
);

create type "transaction-status" as enum ('PENDING','ACCEPTED','REJECTED','CANCELLED');

create table "transaction"(
		"transaction_id" serial primary key,
		"repicient_id" int references "user"("user_id"),
		"sender_id" int references "user"("user_id"),
		"sender_account_number" text not null,
		"transaction_amount" numeric(15,2) not null check(transaction_amount >=0),
		"transaction_status" "transaction-status" not null		
);
------------------------------------------------------------------------------------
begin;
insert into "user" ("email","password","isCustomer","user_status") 
values ('kelvintrinh@gmail.com','12345',true,'PENDING');
insert into "user" ("email","password","isCustomer","user_status") 
values ('nick@gmail.com','12345',true,'ACTIVE');

insert into "user" ("email","password","isCustomer","user_status") 
values ('bank101@bank101.ca','12345',false,'ACTIVE') returning "user_id";

insert into banking_account (customer_id,mailing_address,pending_transaction,banking_status,initial_deposit)
values(2,'13 Yonge St',false,'ACTIVE',0.00) returning "bank_id";
insert into banking_account (customer_id,mailing_address,pending_transaction,banking_status,initial_deposit)
values(1,'15 Yonge St',false,'PENDING',0.00) returning "bank_id";

insert into chequing_account (bank_id,ca_account_number,ca_balance) 
values(1,'654456',300.50);
insert into saving_account (bank_id,sa_account_number,sa_balance) 
values(1,'654457',2350.50);
update "user" set first_name = 'Nick', last_name = 'Fubu' where email = 'nick@gmail.com';
update "user" set first_name = 'Allysa', last_name = 'G' where email = 'bank101@bank101.ca';
update "user" set first_name = 'Kelvin', last_name = 'Trinh' where email = 'kelvintrinh@gmail.com';
insert into "user" ("email","password","first_name","last_name","isCustomer","user_status")
values('quang@gmail.com','12345','Quang','Trung',true,'PENDING') returning "user_id";

insert into "banking_account" (customer_id,pending_transaction,mailing_address,banking_status,initial_deposit)
values(4,false,'Not Available','PENDING',0.00);
commit;



--select * from banking101."user";
--select * from banking101."banking_account";

