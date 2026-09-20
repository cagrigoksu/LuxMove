CREATE TABLE gtfs_stops (
    id BIGSERIAL PRIMARY KEY,

    source VARCHAR(50) NOT NULL,

    source_id VARCHAR(255) NOT NULL,

    location_id BIGINT NOT NULL,

    name VARCHAR(255) NOT NULL,

    location_type INTEGER NOT NULL,

    parent_source_id VARCHAR(255),

    wheelchair_boarding INTEGER,

    platform_code VARCHAR(100),

    CONSTRAINT uq_gtfs_stops_source_source_id
        UNIQUE (source, source_id),

    CONSTRAINT fk_gtfs_stops_location
        FOREIGN KEY (location_id)
        REFERENCES locations (id)
);

CREATE INDEX idx_gtfs_stops_location_id
    ON gtfs_stops (location_id);