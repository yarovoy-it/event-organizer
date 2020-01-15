INSERT INTO goods (id, name, count, type, price) VALUES
(28, 'Plastic', 100, 'Table', 15.00),

(29, 'Hultafors', 500, 'Knife', 0.65),
(30, 'King', 500, 'Knife', 0.85),
(31, 'Fox', 500, 'Knife', 0.55),

(32, 'Wood', 200, 'Chair', 4.75),
(33, 'Plastic', 200, 'Chair', 3.75),

(34, 'Maestro', 300, 'Fork', 1.75),
(35, 'BergHOFF', 300, 'Fork', 1.55),
(36, 'Zillinger', 300, 'Fork', 2.0),
(37, 'Kochsysteme',1000, 'Fork', 0.55),

(38, 'Paper napkin',10000, 'Napkin', 0.5),
(39, 'Rag napkin',1000, 'Napkin', 0.15),

(40, 'Plastic',1000, 'Plate', 0.10),
(41, 'Luval',700, 'Plate', 0.70),
(42, 'Arcoroc', 1500, 'Glass', 0.25);

INSERT INTO beverages (id, name, count, type, price, volume) VALUES
(43, 'Highland Park', 70, 'Whisky', 100.0, 1.0),
(44, 'Jack Daniels', 100, 'Whisky', 80.0, 1.0),
(45, 'Lagavulin', 50, 'Whisky', 180.0, 1.0),

(46, 'The Show Cabernet Sauvignon', 50.0, 'Red Wine', 100, 0.75),
(47, 'Montepulciano d Abruzzo', 50.0, 'Red Wine', 70, 0.75),
(48, 'Cupcake Red Velvet', 50.0, 'Red Wine', 90, 0.75),

(49, 'Beluga', 15, 'Vodka', 125.0, 0.7),
(50, 'Absolut', 70, 'Vodka', 40.0, 0.5),
(51, 'Finlandia', 50, 'Vodka', 75.0, 1.0),

(52, 'Hennessy', 65, 'Cognac', 115.0, 0.7),
(53, 'Martell', 70, 'Cognac', 55.0, 1.0),
(54, 'Remy Martin', 50, 'Cognac', 85.0, 1.0);

INSERT INTO addresses (id, city, street, house_number,apartment) VALUES (13,'GRODNO', 'Budenogo', 14,5);
INSERT INTO addresses (id,city, street, house_number,apartment) VALUES (2,'GRODNO', 'Derzinskogo', 56,16);
INSERT INTO addresses (id,city, street, house_number) VALUES (3,'GRODNO', '17may', 3);
INSERT INTO addresses (id,city, street, house_number,apartment) VALUES (4,'GRODNO', 'Derzinskogo', 135,76);
INSERT INTO addresses (id,city, street, house_number,apartment) VALUES (5,'GRODNO', 'Pushkina', 34, 28);
INSERT INTO addresses (id,city, street, house_number,apartment) VALUES (6,'GRODNO', 'Folush', 167, 104);
INSERT INTO addresses (id,city, street, house_number,apartment) VALUES (7,'GRODNO', 'Derzinskogo', 35,79);
INSERT INTO addresses (id,city, street, house_number,apartment) VALUES (8,'GRODNO', 'Pushkina', 64, 23);
INSERT INTO addresses (id,city, street, house_number,apartment) VALUES (9,'GRODNO', 'Folush', 137, 10);
INSERT INTO addresses (id,city, street, house_number,apartment) VALUES (10,'GRODNO', 'Derzinskogo', 105,74);
INSERT INTO addresses (id,city, street, house_number,apartment) VALUES (11,'GRODNO', 'Sovetskaia', 123, 28);
INSERT INTO addresses (id,city, street, house_number,apartment) VALUES (12,'GRODNO', 'Sovetskaia', 13, 104);

INSERT INTO staff (id, name, phone_number, surname, address_id, department , salary)
VALUES (18, 'Vasia', 375297642865,'Vasichkin', 2, 'BARTENDER', 20.00);
INSERT INTO staff (id, name, phone_number, surname, address_id, department , salary)
VALUES (19, 'Dasha', 375297452387,'Mudilova', null, 'WAITER', 15.00);
INSERT INTO staff (id, name, phone_number, surname, address_id, department , salary)
VALUES (20, 'Grisha', 375297272387,'Bikov', 7, 'SECURITY', 25.00);
INSERT INTO staff (id, name, phone_number, surname, address_id, department , salary)
VALUES (21, 'Dima', 375297562423,'Pypkin', 3, 'BARTENDER', 25.00);
INSERT INTO staff (id, name, phone_number, surname, address_id, department , salary)
VALUES (22, 'Denis', 375297863695,'Lusevech', 5, 'CLEANER', 30.00);

INSERT INTO customers (id, name, phone_number, surname, address_id, discount ,summa)
VALUES (23, 'Petia', 375337835746,'Petichkin', 4, 0 , 0);
INSERT INTO customers (id, name, phone_number, surname, address_id, discount ,summa)
VALUES (24, 'Tania', 375337238765,'Grivuch', 6, 0 , 0);
INSERT INTO customers (id, name, phone_number, surname, address_id, discount ,summa)
VALUES (25, 'Mania', 375337832475,'Golovach', 8, 0 , 50.00);
INSERT INTO customers (id, name, phone_number, surname, address_id, discount ,summa)
VALUES (26, 'Dima', 375337452798,'Gurtov', 9, 0 , 0);
INSERT INTO customers (id, name, phone_number, surname, address_id, discount ,summa)
VALUES (27, 'Viktor', 375337850745,'Melnik', 10, 0 , 0);

INSERT INTO orders (id, description, address_id, customer_id,sum_of_order, date_of_execution) VALUES (101,'nice', 9, 24, 0.0, '2020-12-06');
INSERT INTO orders (id, description, address_id, customer_id,sum_of_order, date_of_execution) VALUES (102,'good', 11, 26, 0.0, '2020-09-03');

INSERT INTO orders_beverages (order_id, beverage_id) VALUES (101, 44);
INSERT INTO orders_beverages (order_id, beverage_id) VALUES (101, 45);
INSERT INTO orders_beverages (order_id, beverage_id) VALUES (102, 46);

INSERT INTO orders_goods (order_id, goods_id) VALUES (101, 31);
INSERT INTO orders_goods (order_id, goods_id) VALUES (101, 33);

INSERT INTO orders_staff (order_id, staff_id) VALUES (101, 18);
INSERT INTO orders_staff (order_id, staff_id) VALUES (101, 19);

INSERT INTO roles (id, name) VALUES (9675,'ROLE_USER');
INSERT INTO roles (id ,name) VALUES (4563,'ROLE_ADMIN');