CREATE TABLE IF NOT EXISTS roles (
    name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS public.countries
(
    name character varying(45) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    CONSTRAINT countries_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.cities
(
    name character varying(45) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    country_name character varying(45) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cities_pkey PRIMARY KEY (name),
    CONSTRAINT cities_country_name_fkey FOREIGN KEY (country_name)
        REFERENCES public.countries (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.postal_codes
(
    code character varying(45) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    city_name character varying COLLATE pg_catalog."default",
    CONSTRAINT postal_codes_pkey PRIMARY KEY (code),
    CONSTRAINT postal_codes_city_name_fkey FOREIGN KEY (city_name)
        REFERENCES public.cities (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);


