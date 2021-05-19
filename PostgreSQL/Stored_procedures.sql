-- Stored procedures 


-- Customer CRUD 
CREATE OR REPLACE PROCEDURE CustomerCRUD (id int, name varchar(40), address_id int, email varchar(40), telefon char(8), statementType varchar(10))
AS $$
BEGIN
	IF statementType = 'Insert' THEN 
		INSERT INTO customer 
						(name, 
						address_id, 
						email, 
						telefon) 
		VALUES 		  ( id, 
						name, 
						address_id, 
						email, 
						telefon);
	END IF ;
	IF statementType = 'Select' THEN 
		SELECT * 
		FROM customer;
	END IF; 
	IF statementType = 'Update' THEN 
		UPDATE customer 
		SET 	name = name,
				address_id = address_id,
				email = email,
				telefon = telefon
		WHERE customer_id = id;
	END IF; 
	IF statementType = 'Delete' THEN 
		DELETE FROM customer
		WHERE customer_id = id;
	END IF; 
END
$$ LANGUAGE plpgsql

-----
-- Update customer email
CREATE OR REPLACE PROCEDURE updateCustomerInfo(id int, email varchar(40))
LANGUAGE plpgsql 
AS $$ 
BEGIN
	UPDATE customer SET email = email  WHERE customer_id = id;
END $$ 