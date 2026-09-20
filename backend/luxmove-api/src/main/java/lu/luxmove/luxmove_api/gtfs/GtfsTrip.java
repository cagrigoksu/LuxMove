package lu.luxmove.luxmove_api.gtfs;

public record GtfsTrip(
        String tripId,
        String routeId,
        String serviceId,
        String headsign,
        String shortName,
        Integer directionId,
        String blockId,
        String shapeId,
        Integer wheelchairAccessible,
        Integer bikesAllowed
) {
}
