--liquibase formatted sql

--changeset jane:1
CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    last_name  VARCHAR(128) NOT NULL,
    login      VARCHAR(128) NOT NULL UNIQUE,
    password   VARCHAR(128) NOT NULL,
    role       VARCHAR(64)
);
--rollback DROP TABLE users;

--changeset jane:2
CREATE TABLE IF NOT EXISTS clients
(
    id                 BIGSERIAL PRIMARY KEY,
    user_id            BIGINT UNIQUE NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    birth_date         DATE,
    driving_licence_no INTEGER                             NOT NULL UNIQUE,
    validity           DATE
);
--rollback DROP TABLE clients;

--changeset jane:3
CREATE TABLE IF NOT EXISTS cars
(
    id            BIGSERIAL PRIMARY KEY,
    brand         VARCHAR(128) NOT NULL,
    model         VARCHAR(128) NOT NULL,
    color         VARCHAR(128),
    seat_amount   INTEGER      NOT NULL,
    price_per_day INTEGER,
    status        VARCHAR(32)  NOT NULL,
    image         VARCHAR(128)
);
--rollback DROP TABLE cars;

--changeset jane:4
CREATE TABLE IF NOT EXISTS bookings
(
    id            BIGSERIAL PRIMARY KEY,
    client_id     BIGINT      NOT NULL REFERENCES clients (id) ON DELETE CASCADE,
    car_id        BIGINT      NOT NULL REFERENCES cars (id) ON DELETE CASCADE,
    rental_start  DATE        NOT NULL,
    rental_finish DATE        NOT NULL,
    status        VARCHAR(32) NOT NULL,
    payment_state VARCHAR(32),
    comment       VARCHAR(128)
);
--rollback DROP TABLE bookings;
