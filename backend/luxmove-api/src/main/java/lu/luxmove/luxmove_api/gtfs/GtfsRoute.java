package lu.luxmove.luxmove_api.gtfs;

public record GtfsRoute(
        String routeId,
        String agencyId,
        String shortName,
        String longName,
        int routeType,
        String routeColor,
        String routeTextColor,
        String description
) {
}