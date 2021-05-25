-- Stored procedures for customer

------------------
-- Delete customer

CREATE OR REPLACE PROCEDURE deleteCustomer(id int)
LANGUAGE plpgsql 
AS $$ 
BEGIN
	DELETE FROM customer WHERE customer_id = id;
END $$ 

------------------
-- Insert city

CREATE OR REPLACE PROCEDURE insertCity(id int, city_name varchar(30))
LANGUAGE plpgsql 
AS $$ 
BEGIN
	INSERT INTO cities     (city_id,
							name	
							) 
					VALUES 
						   (id, 
							city_name 
							);
END $$ 
------------------
-- Insert customer

CREATE OR REPLACE PROCEDURE insertCustomer(cust_name varchar(40),cust_street varchar(40), city_id int, cust_email varchar(40), cust_phone char(8))
LANGUAGE plpgsql 
AS $$ 
BEGIN
	INSERT INTO customer (	name, 
							street, 
							city_id, 
							email, 
							phone) 
					VALUES 
						   (cust_name, 
							cust_street, 
							city_id, 
							cust_email, 
							cust_phone
						);
END $$ 
------------------
-- Update customer basic

CREATE OR REPLACE PROCEDURE updateCustomerBasic(cust_id int, cust_name varchar(40), cust_email varchar(40), cust_phone char(8))
LANGUAGE plpgsql 
AS $$ 
BEGIN
	UPDATE customer 
	SET 
		name = cust_name, 
		email = cust_email, 
		phone = cust_phone 
	WHERE customer_id = cust_id;
	
END $$ 

------------------ 
-- Update customer address

CREATE OR REPLACE PROCEDURE updateCustomerAddress(cust_id int, cust_street varchar(40), cust_city_id int)
LANGUAGE plpgsql 
AS $$ 
BEGIN
	UPDATE customer 
	SET street = cust_street, city_id = cust_city_id
	WHERE customer_id = cust_id;
END $$ 

-------------------
-- Update full customer 

CREATE OR REPLACE PROCEDURE updateCustomer(id int, cust_name varchar(40), cust_address_id int, cust_email varchar(40), cust_phone char(8))
LANGUAGE plpgsql 
AS $$ 
BEGIN
	UPDATE customer 
	SET 
		name = cust_name,
		address_id = cust_address_id,
		email = cust_email, 
		phone = cust_phone
	WHERE customer_id = id;
END $$ 

