package lu.luxmove.luxmove_api.gtfs;

import org.springframework.stereotype.Service;

@Service
public class GtfsRouteService {

    private static final String SOURCE = "GTFS";

    private final AgencyRepository agencyRepository;
    private final RouteRepository routeRepository;

    public GtfsRouteService(
            AgencyRepository agencyRepository,
            RouteRepository routeRepository
    ) {
        this.agencyRepository = agencyRepository;
        this.routeRepository = routeRepository;
    }

    public Route saveRoute(GtfsRoute gtfsRoute) {

        Agency agency = agencyRepository
                .findBySourceAndSourceId(
                        SOURCE,
                        gtfsRoute.agencyId()
                )
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Agency not found: "
                                        + gtfsRoute.agencyId()
                        )
                );

        Route route = routeRepository
                .findBySourceAndSourceId(
                        SOURCE,
                        gtfsRoute.routeId()
                )
                .orElseGet(() -> new Route(
                        SOURCE,
                        gtfsRoute.routeId(),
                        agency,
                        gtfsRoute.shortName(),
                        gtfsRoute.longName(),
                        gtfsRoute.routeType(),
                        gtfsRoute.routeColor(),
                        gtfsRoute.routeTextColor(),
                        gtfsRoute.description()
                ));

        route.update(
                agency,
                gtfsRoute.shortName(),
                gtfsRoute.longName(),
                gtfsRoute.routeType(),
                gtfsRoute.routeColor(),
                gtfsRoute.routeTextColor(),
                gtfsRoute.description()
        );

        return routeRepository.save(route);
    }    

}