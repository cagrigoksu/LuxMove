package lu.luxmove.luxmove_api.gtfs;

import lu.luxmove.luxmove_api.location.Location;
import lu.luxmove.luxmove_api.location.LocationRepository;
import lu.luxmove.luxmove_api.location.LocationType;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

@Service
public class GtfsStopService {

    private static final String SOURCE = "GTFS";

    private final LocationRepository locationRepository;
    private final GtfsStopRepository gtfsStopRepository;

    private final GeometryFactory geometryFactory =
            new GeometryFactory(
                    new PrecisionModel(),
                    4326
            );

    public GtfsStopService(
            LocationRepository locationRepository,
            GtfsStopRepository gtfsStopRepository
    ) {
        this.locationRepository = locationRepository;
        this.gtfsStopRepository = gtfsStopRepository;
    }

    public GtfsStopEntity saveStop(GtfsStop stop) {

        GtfsStopEntity existingStop =
                gtfsStopRepository
                        .findBySourceAndSourceId(
                                SOURCE,
                                stop.stopId()
                        )
                        .orElse(null);

        if (existingStop != null) {
            return existingStop;
        }

        Point point = geometryFactory.createPoint(
                new Coordinate(
                        stop.longitude(),
                        stop.latitude()
                )
        );

        Location location = locationRepository.save(
                new Location(
                        stop.stopName(),
                        point,
                        mapLocationType(stop.locationType())
                )
        );

        GtfsStopEntity entity = new GtfsStopEntity(
                SOURCE,
                stop.stopId(),
                location,
                stop.stopName(),
                stop.locationType()
        );

        return gtfsStopRepository.save(entity);
    }

    private LocationType mapLocationType(int locationType) {

        return switch (locationType) {
            case 0 -> LocationType.BUS_STOP;
            case 1, 2, 3, 4 -> LocationType.MOBILITY_HUB;
            default -> LocationType.MOBILITY_HUB;
        };
    }
}