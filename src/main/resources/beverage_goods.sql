if not exists CREATE TABLE public.goods
(
    id bigint NOT NULL,
    count integer,
    name character varying(255) COLLATE pg_catalog."default",
    price double precision,
    type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT goods_pkey PRIMARY KEY (id)
);

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

if not exists CREATE TABLE public.beverages
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    type character varying(255) COLLATE pg_catalog."default",
    count integer NOT NULL,
    price double precision,
    volume double precision NOT NULL,
    CONSTRAINT beverages_pkey PRIMARY KEY (id)
);

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