CREATE TABLE agencies (
    id BIGSERIAL PRIMARY KEY,

    source VARCHAR(50) NOT NULL,

    source_id VARCHAR(255) NOT NULL,

    name VARCHAR(255) NOT NULL,

    url VARCHAR(500),

    timezone VARCHAR(100) NOT NULL,

    language VARCHAR(20),

    phone VARCHAR(100),

    CONSTRAINT uq_agencies_source_source_id
        UNIQUE (source, source_id)
);