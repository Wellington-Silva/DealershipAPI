CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE dealership(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    shortAddress TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL
);