INSERT INTO public.roles (name)
VALUES
    ('ADMIN'),
    ('EMPLOYEE'),
    ('CLIENT');

INSERT INTO public.countries (name, is_active)
VALUES
    ('Spain', true),
    ('Germany', true);

INSERT INTO public.cities (name, is_active, country_name)
VALUES
    ('Granada', true, 'Spain'),
    ('Münster', true, 'Germany');

INSERT INTO public.postal_codes (code, is_active, city_name)
VALUES
    ('18014', true, 'Granada'),
    ('48143', true, 'Münster');