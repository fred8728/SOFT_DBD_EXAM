
-- Allorders
CREATE OR REPLACE VIEW allOrders AS
SELECT orders.order_id, customer."name", customer.email, customer.phone, orders.amount, orders.price,  orders.status 
FROM orders 
JOIN customer ON (orders.customer_id = customer.customer_id);

---------------
-- AllCustomers
CREATE OR REPLACE VIEW allCustomers AS 
SELECT * FROM customer;

---------------
-- MaxOrderNo
CREATE OR REPLACE VIEW MaxOrderNo
AS 
SELECT * FROM orders 
WHERE order_id = (
    SELECT MAX(order_id) FROM orders);