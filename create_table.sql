CREATE TABLE vehicle
(
    id           SERIAL PRIMARY KEY,
    brand        varchar(100)   NOT NULL,
    model        varchar(100)   NOT NULL,
    year         INT            NOT NULL,
    color        varchar(100),
    price        NUMERIC(10, 2) NOT NULL,
    category_id  INT REFERENCES category (id),
    created_date date default current_date,
    updated_date date default current_date
);
CREATE TABLE category
(
    id           SERIAL PRIMARY KEY,
    name         varchar(100) NOT NULL,
    created_date date default current_date,
    updated_date date default current_date
);
CREATE TABLE customer
(
    id           SERIAL PRIMARY KEY,
    name         varchar(100) NOT NULL,
    email        varchar(100) NOT NULL,
    password     varchar(100) NOT NULL,
    created_date date default current_date,
    updated_date date default current_date
);
CREATE TABLE payment
(
    id             SERIAL PRIMARY KEY,
    rent_id        INT REFERENCES rent (id),
    payment_method varchar(100),
    amount         NUMERIC(10, 2) NOT NULL,
    created_date   date default current_date,
    updated_date   date default current_date
);
CREATE TABLE rent
(
    id           SERIAL PRIMARY KEY,
    customer_id  INT REFERENCES category (id),
    rent_date    timestamp,
    total_amount NUMERIC(10, 2),
    created_date date default current_date,
    updated_date date default current_date
);
CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100)        NOT NULL,
    active   BOOLEAN DEFAULT TRUE
);
CREATE TABLE cart
(
    id SERIAL PRIMARY KEY,
    customer_id INT,
    created_date timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cart_items
(
    id SERIAL PRIMARY KEY,
    cart_id INT NOT NULL REFERENCES cart(id),
    vehicle_id INT NOT NULL REFERENCES vehicle(id)
);

CREATE TABLE rent_item
(
    id SERIAL PRIMARY KEY,
    rent_id INT REFERENCES rent(id),
    vehicle_id INT REFERENCES vehicle(id),
    price NUMERIC(10,2) NOT NULL
);

--ALTER KOMUTLARI
ALTER TABLE vehicle ADD COLUMN created_by INT REFERENCES users(id);
ALTER TABLE vehicle ADD COLUMN updated_by INT REFERENCES users(id);

ALTER TABLE category ADD COLUMN created_by INT REFERENCES users(id);
ALTER TABLE category ADD COLUMN updated_by INT REFERENCES users(id);

ALTER TABLE cart ADD COLUMN total_amount INT;
