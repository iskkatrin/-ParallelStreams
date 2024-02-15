-- liquibase formatted sql

-- changeset jrembo:1
CREATE TABLE users (
                       id SERIAL,
                       email TEXT
);

-- changeset sconnor:1
ALTER TABLE users ADD name TEXT;