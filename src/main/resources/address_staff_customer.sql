if not exists CREATE TABLE public.addresses
(
    id bigint NOT NULL,
    apartment integer,
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    house_number integer NOT NULL,
    street character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT addresses_pkey PRIMARY KEY (id)
);

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

if not exists CREATE TABLE public.staff
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone_number bigint NOT NULL,
    surname character varying(255) COLLATE pg_catalog."default",
    department character varying(255) COLLATE pg_catalog."default",
    salary double precision,
    address_id bigint,
    CONSTRAINT staff_pkey PRIMARY KEY (id),
    CONSTRAINT fk_staff_address FOREIGN KEY (address_id)
        REFERENCES public.addresses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
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

if not exists CREATE TABLE public.customers
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone_number bigint NOT NULL,
    surname character varying(255) COLLATE pg_catalog."default",
    summa double precision,
    discount integer,
    address_id bigint,
    CONSTRAINT customers_pkey PRIMARY KEY (id),
    CONSTRAINT fk_customer_address FOREIGN KEY (address_id)
        REFERENCES public.addresses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

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
