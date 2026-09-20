ALTER TABLE locations
ADD COLUMN source VARCHAR(50),
ADD COLUMN source_id VARCHAR(255);

UPDATE locations
SET source = 'MANUAL'
WHERE source IS NULL;

UPDATE locations
SET source_id = CONCAT('manual-', id)
WHERE source_id IS NULL;

ALTER TABLE locations
ALTER COLUMN source SET NOT NULL,
ALTER COLUMN source_id SET NOT NULL;

CREATE UNIQUE INDEX uq_locations_source_source_id
    ON locations (source, source_id);