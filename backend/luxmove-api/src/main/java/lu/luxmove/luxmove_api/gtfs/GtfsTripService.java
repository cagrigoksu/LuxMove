package lu.luxmove.luxmove_api.gtfs;

import org.springframework.stereotype.Service;

@Service
public class GtfsTripService {

    private static final String SOURCE = "GTFS";

    private final RouteRepository routeRepository;
    private final TripRepository tripRepository;

    public GtfsTripService(
            RouteRepository routeRepository,
            TripRepository tripRepository
    ) {
        this.routeRepository = routeRepository;
        this.tripRepository = tripRepository;
    }

    public Trip saveTrip(GtfsTrip gtfsTrip) {

        Route route = routeRepository
                .findBySourceAndSourceId(
                        SOURCE,
                        gtfsTrip.routeId()
                )
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Route not found: "
                                        + gtfsTrip.routeId()
                        )
                );

        Trip trip = tripRepository
                .findBySourceAndSourceId(
                        SOURCE,
                        gtfsTrip.tripId()
                )
                .orElseGet(() -> new Trip(
                        SOURCE,
                        gtfsTrip.tripId(),
                        route,
                        gtfsTrip.serviceId(),
                        gtfsTrip.headsign(),
                        gtfsTrip.shortName(),
                        gtfsTrip.directionId(),
                        gtfsTrip.blockId(),
                        gtfsTrip.shapeId(),
                        gtfsTrip.wheelchairAccessible(),
                        gtfsTrip.bikesAllowed()
                ));

        return tripRepository.save(trip);
    }
}