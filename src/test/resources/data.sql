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

INSERT INTO public.delivery_methods (name, is_active)
VALUES
    ('EXPRESS', true),
    ('STANDARD', true);

INSERT INTO public.order_statuses (name, is_active)
VALUES
    ('Pending payment', true),
    ('Pending shipment', true),
    ('Shipped', true),
    ('Delivered', true);

INSERT INTO public.payment_methods (name, is_active)
VALUES
    ('Cash', true),
    ('By card', true);

INSERT INTO public.payment_statuses (name, is_active)
VALUES
    ('Pending', true),
    ('Paid', true);

INSERT INTO public.book_genres (name)
VALUES
    ('Thriller'),
    ('Essay'),
    ('Fantasy');

INSERT INTO public.book_parameter_formats (name)
VALUES
    ('E-book'),
    ('Paperback'),
    ('Hardcover');

INSERT INTO public.user_addresses (
	id, postal_code, street, number, is_active
)
VALUES (
    1, '18014', 'Street', 1, true
);

INSERT INTO public.users (
	id, name, surname, date_of_birth, email_address, password, is_active, phone_number, address_id
)
VALUES (
    1, 'Admin', 'Admin', '01-01-2000', 'admin@email.com', '$2a$10$UXwOIA7tOQES3gO2shUsDO.AISCnWLy.aaxUzQd1te2Uki.0qPZpG', true, 12345678910, 1
);

INSERT INTO public.user_roles (
	user_id, role_id, assigned_date, id
)
VALUES (
    1, 'ADMIN', '01-01-2000', 1
);