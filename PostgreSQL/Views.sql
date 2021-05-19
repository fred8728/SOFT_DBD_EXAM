
-- All orders
CREATE OR REPLACE VIEW allOrders AS
SELECT orders.order_id, customer."name", customer.telefon, orders.price,  orders.status 
FROM orders 
JOIN customer ON (orders.customer_id = customer.customer_id);