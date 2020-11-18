create table if not exists Vehicle(
	vin text primary key,
	make text,
	model text,
	made integer,
	vehicleType text,
	paintColor text default('WHITE'),
	mpg integer default(20),
	topSpeed integer default(100),
	horsepower integer default(200),
	isSold boolean default(false)
);

create table if not exists Customer( 
	CustomerID serial primary key,
	Username text unique not null,
	Passkey text not null
);

create table if not exists customer_owned(
	CustomerID integer,
	vin text,
	primary key (CustomerID, vin)
);

create table if not exists Employee( 
	EmployeeID serial primary key,
	Username text unique not null,
	Passkey text not null
);

create table if not exists Offer(
	OfferID serial,
	vin text,
	CustomerID integer,
	Amount integer,
	status text not null,
	primary key (vin, customerID),
	foreign key (vin) references Vehicle (vin) on delete cascade,
	foreign key (CustomerID) references Customer (CustomerID) on delete cascade
);

create table if not exists Payment( 
	PaymentID serial primary key,
	amount integer,
	vin text,
	CustomerID integer,
	lastPayment text,
	nextPayment text,
	paymentsLeft integer not null,
	foreign key (vin) references Vehicle (vin) on delete cascade,
	foreign key (CustomerID) references Customer (CustomerID) on delete cascade
);

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA car_dealership_schema TO jdbc_worker;
GRANT ALL ON TABLE car_dealership_schema.customer TO jdbc_worker;
GRANT ALL ON TABLE car_dealership_schema.customer_owned TO jdbc_worker;
GRANT ALL ON TABLE car_dealership_schema.employee TO jdbc_worker;
GRANT ALL ON TABLE car_dealership_schema.offer TO jdbc_worker;
GRANT ALL ON TABLE car_dealership_schema.payment TO jdbc_worker;
GRANT ALL ON TABLE car_dealership_schema.vehicle TO jdbc_worker;

truncate table customer_owned;
truncate table payment;
truncate table offer;
truncate table employee;
truncate table customer cascade;
truncate table vehicle cascade;

insert into vehicle values ('00000', 'Ford', 'F150', 2020, 'TRUCK', 'WHITE', 18, 90, 300, false);
insert into vehicle values ('00001', 'GMC', 'Siera', 2015, 'TRUCK', 'WHITE', 20, 95, 300, false);
insert into vehicle values ('00002', 'Dodge', 'Ram 2500', 2005, 'TRUCK', 'BLUE', 17, 80, 250, false);
insert into vehicle values ('00003', 'GMC', 'Siera', 2019, 'TRUCK', 'SILVER', 22, 100, 350, false);
insert into employee (Username, Passkey) values ('Bob', 'Pass');
insert into customer (username, passkey) values ('Jay', 'Pass');
insert into customer (username, passkey) values ('John', 'Pass');
insert into customer (username, passkey) values ('Janet', 'Pass');
commit;

select * from Customer;
select * from offer;
select * from Vehicle where isSold = false;
commit;

--insert into offer (vin, customerid, amount, status) values ('00000', 1, 2500, 'Pending');
--insert into offer (vin, customerid, amount, status) values ('00000', 2, 2650, 'Pending');


