CREATE TABLE trips (
    id BIGSERIAL PRIMARY KEY,

    source VARCHAR(50) NOT NULL,

    source_id VARCHAR(255) NOT NULL,

    route_id BIGINT NOT NULL,

    service_id VARCHAR(255) NOT NULL,

    headsign VARCHAR(255),

    short_name VARCHAR(100),

    direction_id INTEGER,

    block_id VARCHAR(255),

    shape_id VARCHAR(255),

    wheelchair_accessible INTEGER,

    bikes_allowed INTEGER,

    CONSTRAINT uq_trips_source_source_id
        UNIQUE (source, source_id),

    CONSTRAINT fk_trips_route
        FOREIGN KEY (route_id)
        REFERENCES routes (id)
);