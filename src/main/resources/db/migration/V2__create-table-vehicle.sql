CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE vehicle (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    model TEXT NOT NULL,
    plate TEXT NOT NULL,
    year INTEGER NOT NULL,
    dealership_id UUID NOT NULL,
    CONSTRAINT fk_vehicle_dealership
    FOREIGN KEY (dealership_id) REFERENCES dealership(id) ON DELETE CASCADE
);