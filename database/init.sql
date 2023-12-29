CREATE DATABASE temp
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA IF NOT EXISTS shoes
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS shoes.stocks
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    name character varying(256) COLLATE pg_catalog."default",
    size character varying(8) COLLATE pg_catalog."default",
    qty integer,
    CONSTRAINT stocks_pkey PRIMARY KEY (id),
    CONSTRAINT stocks_name_size_key UNIQUE (name, size)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS shoes.stocks
    OWNER to postgres;
