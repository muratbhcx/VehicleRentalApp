CREATE TABLE category
(
    id   SERIAL PRIMARY KEY,
    name varchar(100) NOT NULL
);

CREATE TABLE vehicle
(
    id                  SERIAL PRIMARY KEY,
    brand               varchar(100)   NOT NULL,
    model               varchar(100)   NOT NULL,
    year                INT            NOT NULL,
    hourly_rental_price varchar(100)   NOT NULL,
    price               NUMERIC(15, 2) NOT NULL,
    category_id         INT REFERENCES category (id)
);

CREATE TABLE customer
(
    id       SERIAL PRIMARY KEY,
    name     varchar(100) NOT NULL,
    age      int          NOT NULL,
    email    varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    role     varchar(100)
);

CREATE TABLE rent
(
    id            SERIAL PRIMARY KEY,
    customer_id   INT REFERENCES customer (id),
    vehicle_brand VARCHAR(100),
    vehicle_model VARCHAR(100),
    total_amount  NUMERIC(15, 2)
);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100)        NOT NULL,
    active   BOOLEAN DEFAULT TRUE
);
