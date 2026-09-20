package lu.luxmove.luxmove_api.gtfs;

import org.springframework.stereotype.Service;

@Service
public class GtfsStopTimeService {

    private static final String SOURCE = "GTFS";

    private final TripRepository tripRepository;
    private final GtfsStopRepository gtfsStopRepository;
    private final StopTimeRepository stopTimeRepository;

    public GtfsStopTimeService(
            TripRepository tripRepository,
            GtfsStopRepository gtfsStopRepository,
            StopTimeRepository stopTimeRepository
    ) {
        this.tripRepository = tripRepository;
        this.gtfsStopRepository = gtfsStopRepository;
        this.stopTimeRepository = stopTimeRepository;
    }

    public StopTime saveStopTime(
            GtfsStopTime gtfsStopTime
    ) {

        Trip trip = tripRepository
                .findBySourceAndSourceId(
                        SOURCE,
                        gtfsStopTime.tripId()
                )
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Trip not found: "
                                        + gtfsStopTime.tripId()
                        )
                );

        GtfsStopEntity gtfsStop = gtfsStopRepository
                .findBySourceAndSourceId(
                        SOURCE,
                        gtfsStopTime.stopId()
                )
                .orElseThrow(() ->
                        new IllegalStateException(
                                "GTFS stop not found: "
                                        + gtfsStopTime.stopId()
                        )
                );

        StopTime stopTime = new StopTime(
                trip,
                gtfsStop,
                gtfsStopTime.stopSequence(),
                gtfsStopTime.arrivalTime(),
                gtfsStopTime.departureTime(),
                gtfsStopTime.pickupType(),
                gtfsStopTime.dropOffType(),
                gtfsStopTime.stopHeadsign()
        );

        return stopTimeRepository.save(stopTime);
    }
}