package lu.luxmove.luxmove_api.gtfs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripRepository
        extends JpaRepository<Trip, Long> {

    Optional<Trip> findBySourceAndSourceId(
            String source,
            String sourceId
    );
}