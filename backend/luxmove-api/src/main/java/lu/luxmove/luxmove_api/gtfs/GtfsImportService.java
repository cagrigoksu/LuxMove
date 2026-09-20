package lu.luxmove.luxmove_api.gtfs;

import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class GtfsImportService {

    private static final Path GTFS_DIRECTORY =
            Path.of("../../data/gtfs/current");

    private final GtfsAgencyImporter agencyImporter;
    private final GtfsAgencyService agencyService;

    private final GtfsRouteImporter routeImporter;
    private final GtfsRouteService routeService;

    private final GtfsStopImporter stopImporter;
    private final GtfsStopService stopService;

    private final GtfsTripImporter tripImporter;
    private final GtfsTripService tripService;  
    
    private final GtfsStopTimeImporter stopTimeImporter;
    private final GtfsStopTimeService stopTimeService;


    public GtfsImportService(
            GtfsAgencyImporter agencyImporter,
            GtfsAgencyService agencyService,
            GtfsRouteImporter routeImporter,
            GtfsRouteService routeService,
            GtfsStopImporter stopImporter,
            GtfsStopService stopService,
            GtfsTripImporter tripImporter,
            GtfsTripService tripService,
            GtfsStopTimeImporter stopTimeImporter,
            GtfsStopTimeService stopTimeService
    ) {
        this.agencyImporter = agencyImporter;
        this.agencyService = agencyService;
        this.routeImporter = routeImporter;
        this.routeService = routeService;
        this.stopImporter = stopImporter;
        this.stopService = stopService;
        this.tripImporter = tripImporter;
        this.tripService = tripService;
        this.stopTimeImporter = stopTimeImporter;
        this.stopTimeService = stopTimeService;
    }
    public ImportResult importGtfs() throws Exception {

        Path agencyFile =
                GTFS_DIRECTORY.resolve("agency.txt");

        List<GtfsAgency> agencies =
                agencyImporter.readAgencies(agencyFile);

        for (GtfsAgency agency : agencies) {
            agencyService.saveAgency(agency);
        }

        Path routeFile =
                GTFS_DIRECTORY.resolve("routes.txt");

        List<GtfsRoute> routes =
                routeImporter.readRoutes(routeFile);

        for (GtfsRoute route : routes) {
            routeService.saveRoute(route);
        }

        Path stopFile =
            GTFS_DIRECTORY.resolve("stops.txt");

        List<GtfsStop> stops =
                stopImporter.readStops(stopFile);

        for (GtfsStop stop : stops) {
            stopService.saveStop(stop);
        }

        Path tripFile =
                GTFS_DIRECTORY.resolve("trips.txt");

        List<GtfsTrip> trips =
                tripImporter.readTrips(tripFile);

        for (GtfsTrip trip : trips) {
            tripService.saveTrip(trip);
        }

        Path stopTimeFile =
                GTFS_DIRECTORY.resolve("stop_times.txt");

        List<GtfsStopTime> stopTimes =
                stopTimeImporter.readStopTimes(stopTimeFile);

        for (GtfsStopTime stopTime : stopTimes) {
            stopTimeService.saveStopTime(stopTime);
        }

        return new ImportResult(
                agencies.size(),
                routes.size(),
                stops.size(),
                trips.size(),
                stopTimes.size()
        );
    }

    public record ImportResult(
            int agencies,
            int routes,
            int stops,
            int trips,
            int stopTimes
    ) {
    }
}