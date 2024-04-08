CREATE TABLE IF NOT EXISTS public.roles (
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.countries (
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    CONSTRAINT countries_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.cities (
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    country_name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cities_pkey PRIMARY KEY (name),
    CONSTRAINT cities_country_name_fkey FOREIGN KEY (country_name)
        REFERENCES public.countries (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.postal_codes (
    code VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    city_name VARCHAR COLLATE pg_catalog."default",
    CONSTRAINT postal_codes_pkey PRIMARY KEY (code),
    CONSTRAINT postal_codes_city_name_fkey FOREIGN KEY (city_name)
        REFERENCES public.cities (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE IF NOT EXISTS public.delivery_methods (
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    CONSTRAINT delivery_methods_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.order_statuses (
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    CONSTRAINT order_statuses_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.payment_methods (
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    CONSTRAINT payment_methods_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.payment_statuses (
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    CONSTRAINT payment_statuses_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.book_genres (
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT book_genres_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.book_parameter_formats (
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT book_parameter_formats_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.user_addresses (
    id integer NOT NULL,
    postal_code VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    street VARCHAR(60) COLLATE pg_catalog."default" NOT NULL,
    number VARCHAR(255) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    CONSTRAINT user_addresses_pkey PRIMARY KEY (id),
    CONSTRAINT user_addresses_postal_code_fkey FOREIGN KEY (postal_code)
        REFERENCES public.postal_codes (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.users (
    id integer NOT NULL,
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    surname VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    date_of_birth date NOT NULL,
    email_address VARCHAR COLLATE pg_catalog."default" NOT NULL,
    password VARCHAR COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    phone_number VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    address_id integer,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_address_key UNIQUE (email_address),
    CONSTRAINT users_address_id_fkey FOREIGN KEY (address_id)
        REFERENCES public.user_addresses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.user_roles (
    user_id integer NOT NULL,
    role_id VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    assigned_date date NOT NULL,
    id integer NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_roles_role_fkey FOREIGN KEY (role_id)
        REFERENCES public.roles (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);