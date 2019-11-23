DELETE  FROM addresses;
ALTER SEQUENCE hibernate_sequence RESTART WITH 1;

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
VALUES (19, 'Dasha', 375297452387,'Mudilova', 11, 'WAITER', 15.00);
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
