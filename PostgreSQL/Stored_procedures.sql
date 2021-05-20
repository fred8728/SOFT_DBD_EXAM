-- Stored procedures 


-- Customer CRUD 
CREATE OR REPLACE PROCEDURE CustomerGetOrDelete (cust_id int, statementType varchar(10))
AS $$
BEGIN
	IF statementType = 'Select' THEN 
		SELECT * 
		FROM customer
		WHERE customer_id = cust_id;
	END IF; 
	IF statementType = 'Delete' THEN 
		DELETE FROM customer
		WHERE customer_id = cust_id;
	END IF; 
END
$$ LANGUAGE plpgsql

-----
-- Update customer 

CREATE OR REPLACE PROCEDURE updateCustomer(id int, cust_name varchar(40), cust_address_id int, cust_email varchar(40), cust_phone char(8))
LANGUAGE plpgsql 
AS $$ 
BEGIN
	UPDATE customer 
	SET name = cust_name,
	address_id = cust_address_id,
	email = cust_email, 
	phone = cust_phone
	WHERE customer_id = id;
END $$ 


CREATE OR REPLACE PROCEDURE updateCustomerEmailAndPhone(id int, cust_email varchar(40), cust_phone char(8))
LANGUAGE plpgsql 
AS $$ 
BEGIN
	UPDATE customer 
	SET email = cust_email, 
	phone = cust_phone
	WHERE customer_id = id;
END $$ 

CREATE OR REPLACE PROCEDURE insertCustomer(cust_name varchar(40),cust_street varchar(40), city_id int, cust_email varchar(40), cust_phone char(8), city_name varchar(30))
LANGUAGE plpgsql 
AS $$ 
BEGIN
	INSERT INTO cities VALUES (city_id, city_name);
	INSERT INTO customer (name, street, city_id, email, phone) VALUES (cust_name, cust_street, city_id, cust_email, cust_phone);
	
END $$ 


