package lu.luxmove.luxmove_api.location;

public record LocationResponse (
    String name,
    double latitude,
    double longitude,
    LocationType type
){}
