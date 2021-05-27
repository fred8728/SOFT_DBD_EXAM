-- Stored procedures for Order
------------------
-- Delete customer

CREATE OR REPLACE PROCEDURE deleteOrder(id int)
LANGUAGE plpgsql 
AS $$ 
BEGIN
	DELETE FROM orders WHERE order_id = id;
END $$ 

------------------


