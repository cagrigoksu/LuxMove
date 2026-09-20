package lu.luxmove.luxmove_api.gtfs;

import lu.luxmove.luxmove_api.location.Location;
import lu.luxmove.luxmove_api.location.LocationType;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Component;

@Component
public class GtfsStopMapper {

    private final GeometryFactory geometryFactory =
            new GeometryFactory(new PrecisionModel(), 4326);

    public Location map(GtfsStop stop) {

        Point point = geometryFactory.createPoint(
                new Coordinate(
                        stop.longitude(),
                        stop.latitude()
                )
        );

        return new Location(
                stop.stopName(),
                point,
                mapLocationType(stop.locationType())
        );
    }

    private LocationType mapLocationType(int locationType) {

        return switch (locationType) {
            case 0 -> LocationType.BUS_STOP;
            case 1 -> LocationType.MOBILITY_HUB;
            case 2, 3, 4 -> LocationType.MOBILITY_HUB;
            default -> LocationType.MOBILITY_HUB;
        };
    }
}