
-- Allorders
CREATE OR REPLACE VIEW allOrders AS
SELECT orders.order_id, customer."name", customer.phone, orders.price,  orders.status 
FROM orders 
JOIN customer ON (orders.customer_id = customer.customer_id);


---------------
-- AllCustomers
CREATE OR REPLACE VIEW allCustomers AS 
SELECT * FROM customer;

