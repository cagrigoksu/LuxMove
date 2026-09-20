package lu.luxmove.luxmove_api.location;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllBySource(String source);
}