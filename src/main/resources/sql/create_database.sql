CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    last_name  VARCHAR(128) NOT NULL,
    login      VARCHAR(128) NOT NULL UNIQUE,
    password   VARCHAR(128) NOT NULL,
    role       VARCHAR(64)
);

DROP TABLE users;

CREATE TABLE clients
(
    id                 BIGSERIAL PRIMARY KEY,
    user_id            BIGINT REFERENCES users (id) UNIQUE NOT NULL,
    birth_date         DATE,
    driving_licence_no INTEGER                             NOT NULL UNIQUE,
    validity           DATE
);

DROP TABLE clients;

CREATE TABLE cars
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

DROP TABLE cars;

CREATE TABLE bookings
(
    id            BIGSERIAL PRIMARY KEY,
    client_id     BIGINT      NOT NULL REFERENCES clients (id),
    car_id        BIGINT      NOT NULL REFERENCES cars (id),
    rental_start  DATE        NOT NULL,
    rental_finish DATE        NOT NULL,
    status        VARCHAR(32) NOT NULL,
    payment_state VARCHAR(32),
    comment       VARCHAR(128)
);

DROP TABLE bookings;
