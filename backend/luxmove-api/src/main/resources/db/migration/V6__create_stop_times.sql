CREATE TABLE stop_times (
    id BIGSERIAL PRIMARY KEY,

    trip_id BIGINT NOT NULL,

    gtfs_stop_id BIGINT NOT NULL,

    stop_sequence INTEGER NOT NULL,

    arrival_time VARCHAR(20),

    departure_time VARCHAR(20),

    pickup_type INTEGER,

    drop_off_type INTEGER,

    stop_headsign VARCHAR(255),

    CONSTRAINT uq_stop_times_trip_sequence
        UNIQUE (trip_id, stop_sequence),

    CONSTRAINT fk_stop_times_trip
        FOREIGN KEY (trip_id)
        REFERENCES trips (id),

    CONSTRAINT fk_stop_times_gtfs_stop
        FOREIGN KEY (gtfs_stop_id)
        REFERENCES gtfs_stops (id)
);

CREATE INDEX idx_stop_times_trip_id
    ON stop_times (trip_id);

CREATE INDEX idx_stop_times_gtfs_stop_id
    ON stop_times (gtfs_stop_id);