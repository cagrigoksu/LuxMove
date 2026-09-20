package lu.luxmove.luxmove_api.gtfs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StopTimeRepository
        extends JpaRepository<StopTime, Long> {
}