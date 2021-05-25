-- Stored procedures for Order
------------------
-- Delete customer

CREATE OR REPLACE PROCEDURE deleteOrder(id int)
LANGUAGE plpgsql 
AS $$ 
BEGIN
	DELETE FROM orders WHERE customer_id = id;
END $$ 

------------------

