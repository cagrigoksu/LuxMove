ALTER TABLE locations
ADD COLUMN source VARCHAR(50) NOT NULL,
ADD COLUMN source_id VARCHAR(255) NOT NULL;

CREATE UNIQUE INDEX uq_locations_source_source_id
    ON locations (source, source_id);