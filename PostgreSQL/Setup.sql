CREATE SCHEMA IF NOT EXISTS Chocolate;

CREATE TABLE IF NOT EXISTS cities(
city_id int PRIMARY KEY, 
name varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS customer(
customer_id SERIAL PRIMARY KEY,
name varchar(40) NOT NULL,
street varchar(40),
city_id int REFERENCES cities ON DELETE CASCADE,
email varchar(40) UNIQUE,
phone char(8) UNIQUE
);

CREATE TABLE IF NOT EXISTS orders(
order_id SERIAL PRIMARY KEY,
amount int NOT NULL,
price int NOT NULL,
status varchar(20) DEFAULT 'not_shipped',
customer_id int REFERENCES customer ON DELETE CASCADE 
);

CREATE TABLE IF NOT EXISTS order_details(
item_id int PRIMARY KEY,
price int NOT NULL, 
amount int NOT NULL,
order_id int REFERENCES orders ON DELETE CASCADE 
);