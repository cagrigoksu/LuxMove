package lu.luxmove.luxmove_api.gtfs;

import jakarta.transaction.Transactional;
import lu.luxmove.luxmove_api.location.Location;
import lu.luxmove.luxmove_api.location.LocationRepository;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GtfsImportService {

    private static final String SOURCE = "GTFS";

    private final GtfsStopImporter importer;
    private final GtfsStopMapper mapper;
    private final LocationRepository locationRepository;

    public GtfsImportService(
            GtfsStopImporter importer,
            GtfsStopMapper mapper,
            LocationRepository locationRepository
    ) {
        this.importer = importer;
        this.mapper = mapper;
        this.locationRepository = locationRepository;
    }

    @Transactional
    public ImportResult importStops(Path file) throws Exception {

        List<GtfsStop> stops = importer.readStops(file);

        List<Location> existingLocations =
                locationRepository.findAllBySource(SOURCE);

        Map<String, Location> existingBySourceId = new HashMap<>();

        for (Location location : existingLocations) {
            existingBySourceId.put(
                    location.getSourceId(),
                    location
            );
        }

        int inserted = 0;
        int updated = 0;

        for (GtfsStop stop : stops) {

            Location existing = existingBySourceId.get(stop.stopId());

            if (existing == null) {

                Location location = mapper.map(stop);

                existingBySourceId.put(
                        stop.stopId(),
                        location
                );

                locationRepository.save(location);

                inserted++;

            } else {

                Location updatedLocation = mapper.map(stop);

                existing.updateFromGtfs(
                        updatedLocation.getName(),
                        updatedLocation.getLocation(),
                        updatedLocation.getType()
                );

                updated++;
            }
        }

        return new ImportResult(
                stops.size(),
                inserted,
                updated
        );
    }

    public record ImportResult(
            int read,
            int inserted,
            int updated
    ) {
    }
}