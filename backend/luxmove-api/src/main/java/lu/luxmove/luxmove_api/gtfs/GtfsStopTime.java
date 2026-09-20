package lu.luxmove.luxmove_api.gtfs;

public record GtfsStopTime(
        String tripId,
        String stopId,
        int stopSequence,
        String arrivalTime,
        String departureTime,
        Integer pickupType,
        Integer dropOffType,
        String stopHeadsign
) {
}