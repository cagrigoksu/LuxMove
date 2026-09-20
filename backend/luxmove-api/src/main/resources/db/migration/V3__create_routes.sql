CREATE TABLE routes (
    id BIGSERIAL PRIMARY KEY,

    source VARCHAR(50) NOT NULL,

    source_id VARCHAR(255) NOT NULL,

    agency_id BIGINT NOT NULL,

    short_name VARCHAR(100),

    long_name VARCHAR(255),

    route_type INTEGER NOT NULL,

    route_color VARCHAR(10),

    route_text_color VARCHAR(10),

    description TEXT,

    CONSTRAINT uq_routes_source_source_id
        UNIQUE (source, source_id),

    CONSTRAINT fk_routes_agency
        FOREIGN KEY (agency_id)
        REFERENCES agencies (id)
);