--liquibase formatted sql

--changeset jane:1
ALTER TABLE users
ADD COLUMN image VARCHAR(64);
