if not exists CREATE TABLE public.users
(
    id bigint NOT NULL,
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

if not exists CREATE TABLE public.roles
(
    id bigint NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);

if not exists CREATE TABLE public.users_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT fk_users_roles_role FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_users_roles_user FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
DELETE  FROM users_roles;
DELETE  FROM roles;
DELETE  FROM users;
INSERT INTO roles (id, name) VALUES (9675,'ROLE_USER');
INSERT INTO roles (id ,name) VALUES (4563,'ROLE_ADMIN');