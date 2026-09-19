INSERT INTO locations (name, location)
VALUES
(
    'Luxembourg City',
    ST_SetSRID(ST_MakePoint(6.1300, 49.6116), 4326)::geography
),
(
    'Luxembourg Gare',
    ST_SetSRID(ST_MakePoint(6.1347, 49.6008), 4326)::geography
);