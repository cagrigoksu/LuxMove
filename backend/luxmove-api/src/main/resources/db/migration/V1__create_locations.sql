CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE locations (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(255) NOT NULL,

    type VARCHAR(50) NOT NULL,

    location GEOGRAPHY(Point, 4326) NOT NULL
);

CREATE INDEX idx_locations_location
    ON locations
    USING GIST (location);