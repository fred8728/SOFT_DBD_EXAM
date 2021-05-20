-- Insert operations

INSERT INTO cities VALUES (2670, 'Greve'), (2635, 'Ishøj'),(4534, 'Hørve');

INSERT INTO customer (name, street, city_id, email, phone) VALUES 
('Frederikke Nilsson', 'Haven 1', 2670, 'example@hotmail.com', '12345678'), 
('Simone Hansen', 'Haven 2', 2635, 'example1@hotmail.com', '12345679');

INSERT INTO orders (amount, price, customer_id) VALUES 
(3, 300, 1),
(2, 240, 2);

INSERT INTO order_details (item_id, price, amount, order_id) VALUES 
(1, 200, 2, 1),
(2, 100, 1, 1),
(3, 200, 1, 2),
(4, 40, 1, 2);


-- Views
SELECT * FROM order_details WHERE order_id = 1;

SELECT orders.order_id, customer."name", customer.telefon, orders.price,  orders.status FROM orders JOIN customer ON (orders.customer_id = customer.customer_id) WHERE order_id = 1;






