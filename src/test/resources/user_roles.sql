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

INSERT INTO roles (id, name) VALUES (9675,'ROLE_USER');
INSERT INTO roles (id ,name) VALUES (4563,'ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT fk_users_roles_role FOREIGN KEY (role_id)
        REFERENCES public.roles (id),
    CONSTRAINT fk_users_roles_user FOREIGN KEY (user_id)
        REFERENCES public.users (id)
);

