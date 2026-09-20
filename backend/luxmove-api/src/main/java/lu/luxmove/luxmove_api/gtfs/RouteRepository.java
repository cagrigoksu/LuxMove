package lu.luxmove.luxmove_api.gtfs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<Route> findBySourceAndSourceId(
            String source,
            String sourceId
    );
}