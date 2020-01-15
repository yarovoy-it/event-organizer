CREATE TABLE  IF NOT EXISTS public.addresses
(
    id bigint NOT NULL,
    apartment integer,
    city character varying(255) NOT NULL,
    house_number integer NOT NULL,
    street character varying(255) NOT NULL,
    CONSTRAINT addresses_pkey PRIMARY KEY (id)
);

CREATE TABLE  IF NOT EXISTS public.staff
(
    id bigint NOT NULL,
    name character varying(255)  NOT NULL,
    phone_number bigint NOT NULL,
    surname character varying(255),
    department character varying(255) ,
    salary double precision,
    address_id bigint,
    CONSTRAINT staff_pkey PRIMARY KEY (id),
    CONSTRAINT fk_staff_address FOREIGN KEY (address_id)
        REFERENCES public.addresses (id)
);

CREATE TABLE  IF NOT EXISTS public.customers
(
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    phone_number bigint NOT NULL,
    surname character varying(255),
    summa double precision,
    discount integer,
    address_id bigint,
    CONSTRAINT customers_pkey PRIMARY KEY (id),
    CONSTRAINT fk_customer_address FOREIGN KEY (address_id)
        REFERENCES public.addresses (id)
);

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL,
    username character varying(255)  NOT NULL,
    password character varying(255)  NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.roles
(
    id bigint NOT NULL,
    name character varying  NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT fk_users_roles_role FOREIGN KEY (role_id)
        REFERENCES public.roles (id),
    CONSTRAINT fk_users_roles_user FOREIGN KEY (user_id)
        REFERENCES public.users (id)
);

CREATE TABLE IF NOT EXISTS public.goods
(
    id bigint NOT NULL,
    count integer,
    name character varying(255),
    price double precision,
    type character varying(255),
    CONSTRAINT goods_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.beverages
(
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    type character varying(255) ,
    count integer NOT NULL,
    price double precision,
    volume double precision NOT NULL,
    CONSTRAINT beverages_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.orders
(
    id bigint NOT NULL,
    description character varying(255) ,
    address_id bigint,
    customer_id bigint,
    sum_of_order double precision,
    date_of_execution date NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT fk_order_address FOREIGN KEY (address_id)
        REFERENCES public.addresses (id),
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id)
        REFERENCES public.customers (id)
);

CREATE TABLE IF NOT EXISTS public.orders_beverages
(
    order_id bigint NOT NULL,
    beverage_id bigint,
    CONSTRAINT fk_order_beverages_beverage FOREIGN KEY (beverage_id)
        REFERENCES public.beverages (id) ,
    CONSTRAINT fk_order_beverages_order FOREIGN KEY (order_id)
        REFERENCES public.orders (id)
);

CREATE TABLE IF NOT EXISTS public.orders_goods
(
    order_id bigint NOT NULL,
    goods_id bigint NOT NULL,
    CONSTRAINT fk_order_goods_goods FOREIGN KEY (goods_id)
        REFERENCES public.goods (id),
    CONSTRAINT fk_order_goods_order FOREIGN KEY (order_id)
        REFERENCES public.orders (id)
);

CREATE TABLE IF NOT EXISTS public.orders_staff
(
    order_id bigint NOT NULL,
    staff_id bigint NOT NULL,
    CONSTRAINT fk_orders_staff_order FOREIGN KEY (order_id)
        REFERENCES public.orders (id)
);