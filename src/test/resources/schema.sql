CREATE TABLE IF NOT EXISTS public.roles (
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.countries (
    name VARCHAR(45) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    CONSTRAINT countries_pkey PRIMARY KEY (name)
);

CREATE SEQUENCE IF NOT EXISTS public.cities_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

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

CREATE SEQUENCE IF NOT EXISTS public.book_parameters_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.book_parameters
(
    id integer NOT NULL,
    author character varying(60) COLLATE pg_catalog."default" NOT NULL,
    format character varying COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    CONSTRAINT book_parameters_pkey PRIMARY KEY (id),
    CONSTRAINT book_parameters_format_fkey FOREIGN KEY (format)
        REFERENCES public.book_parameter_formats (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE SEQUENCE IF NOT EXISTS public.books_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.books
(
    id integer NOT NULL,
    title character varying COLLATE pg_catalog."default" NOT NULL,
    price numeric(38,2) NOT NULL,
    genre character varying COLLATE pg_catalog."default" NOT NULL,
    parameters_id integer,
    stock integer NOT NULL,
    is_active boolean NOT NULL,
    isbn character varying(45) COLLATE pg_catalog."default" NOT NULL,
    image oid,
    CONSTRAINT books_pkey PRIMARY KEY (id),
    CONSTRAINT books_isbn_key UNIQUE (isbn),
    CONSTRAINT books_genre_fkey FOREIGN KEY (genre)
        REFERENCES public.book_genres (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT books_parameters_id_fkey FOREIGN KEY (parameters_id)
        REFERENCES public.book_parameters (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE SEQUENCE IF NOT EXISTS public.user_addresses_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

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

CREATE SEQUENCE IF NOT EXISTS public.users_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL,
    name character varying(45) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(45) COLLATE pg_catalog."default" NOT NULL,
    date_of_birth date NOT NULL,
    email_address character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    phone_number character varying(45) COLLATE pg_catalog."default" NOT NULL,
    address_id integer,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_address_key UNIQUE (email_address),
    CONSTRAINT users_address_id_fkey FOREIGN KEY (address_id)
        REFERENCES public.user_addresses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.orders_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.orders
(
    id integer NOT NULL,
    user_id integer NOT NULL,
    payment_method character varying COLLATE pg_catalog."default" NOT NULL,
    payment_status character varying COLLATE pg_catalog."default" NOT NULL,
    delivery_method character varying COLLATE pg_catalog."default" NOT NULL,
    order_status character varying COLLATE pg_catalog."default" NOT NULL,
    date date NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT orders_delivery_method_fkey FOREIGN KEY (delivery_method)
        REFERENCES public.delivery_methods (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT orders_order_status_fkey FOREIGN KEY (order_status)
        REFERENCES public.order_statuses (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT orders_payment_method_fkey FOREIGN KEY (payment_method)
        REFERENCES public.payment_methods (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT orders_payment_status_fkey FOREIGN KEY (payment_status)
        REFERENCES public.payment_statuses (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT orders_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.order_books_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.order_books
(
    book_id integer NOT NULL,
    amount integer NOT NULL,
    id integer NOT NULL,
    order_id integer NOT NULL,
    CONSTRAINT order_books_pkey PRIMARY KEY (id),
    CONSTRAINT fk9ai8wib7jbokb4njnkwb8cy5i FOREIGN KEY (order_id)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT order_books_book_id_fkey FOREIGN KEY (book_id)
        REFERENCES public.books (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
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

CREATE SEQUENCE IF NOT EXISTS public.user_roles_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

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