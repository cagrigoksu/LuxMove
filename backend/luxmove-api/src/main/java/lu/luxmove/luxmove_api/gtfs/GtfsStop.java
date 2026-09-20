package lu.luxmove.luxmove_api.gtfs;

public record GtfsStop(
        String stopId,
        String stopName,
        double latitude,
        double longitude,
        int locationType
) {
}