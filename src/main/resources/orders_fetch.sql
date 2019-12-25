if not exists CREATE TABLE public.orders
(
    id bigint NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    address_id bigint,
    customer_id bigint,
    sum_of_order double precision,
    date_of_execution date NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT fk_order_address FOREIGN KEY (address_id)
        REFERENCES public.addresses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id)
        REFERENCES public.customers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO orders (id, description, address_id, customer_id,sum_of_order, date_of_execution) VALUES (101,'nice', 9, 24, 0.0, '2020-12-06');
INSERT INTO orders (id, description, address_id, customer_id,sum_of_order, date_of_execution) VALUES (102,'good', 11, 26, 0.0, '2020-09-03');

CREATE TABLE public.orders_beverages
(
    order_id bigint NOT NULL,
    beverage_id bigint,
    CONSTRAINT fk_order_beverages_beverage FOREIGN KEY (beverage_id)
        REFERENCES public.beverages (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_order_beverages_order FOREIGN KEY (order_id)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO orders_beverages (order_id, beverage_id) VALUES (101, 44);
INSERT INTO orders_beverages (order_id, beverage_id) VALUES (101, 45);
INSERT INTO orders_beverages (order_id, beverage_id) VALUES (102, 46);

CREATE TABLE public.orders_goods
(
    order_id bigint NOT NULL,
    goods_id bigint NOT NULL,
    CONSTRAINT fk_order_goods_goods FOREIGN KEY (goods_id)
        REFERENCES public.goods (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_order_goods_order FOREIGN KEY (order_id)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO orders_goods (order_id, goods_id) VALUES (101, 31);
INSERT INTO orders_goods (order_id, goods_id) VALUES (101, 33);

CREATE TABLE public.orders_staff
(
    order_id bigint NOT NULL,
    staff_id bigint NOT NULL,
    CONSTRAINT fk_orders_staff_order FOREIGN KEY (order_id)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO orders_staff (order_id, staff_id) VALUES (101, 18);
INSERT INTO orders_staff (order_id, staff_id) VALUES (101, 19);