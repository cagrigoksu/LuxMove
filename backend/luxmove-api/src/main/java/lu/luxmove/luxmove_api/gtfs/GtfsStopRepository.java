package lu.luxmove.luxmove_api.gtfs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GtfsStopRepository
        extends JpaRepository<GtfsStopEntity, Long> {

    Optional<GtfsStopEntity> findBySourceAndSourceId(
            String source,
            String sourceId
    );
}