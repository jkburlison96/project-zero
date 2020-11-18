create or replace function get_customer_owned_vehicles(i integer)
	returns table(
		"VIN" text,
		"MAKE" text,
		"MODEL" text,
		"YEAR" integer,
		"VEHICLE TYPE" text,
		"PAINT COLOR" text,
		"MPG" integer,
		"TOP SPEED" integer,
		"HORSEPOWER" integer
	) as $$
	begin
		return query select
			vin,
			make,
			model,
			made,
			vehicleType,
			paintColor,
			mpg,
			topSpeed,
			horsepower
		from Vehicle where vin in (
			select vin from customer_owned where customerid in (i) and isSold = true
	);
	end
	$$ language plpgsql;

create or replace function insert_into_customer_owned()
	returns trigger as $$
		begin
			insert into customer_owned values(new.customerID, new.vin);
		return new;
		end
	$$ language plpgsql;

create trigger insert_into_customer_owned_trigger
	before insert or update on payment
	for each row
	execute function insert_into_customer_owned();


