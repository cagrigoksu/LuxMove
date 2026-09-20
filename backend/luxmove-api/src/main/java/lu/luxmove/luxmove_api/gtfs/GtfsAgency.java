package lu.luxmove.luxmove_api.gtfs;

public record GtfsAgency(
    String agencyId,
    String agencyName,
    String agencyUrl,
    String agencyTimezone,
    String agencyLanguage,
    String agencyPhone
) {
    
}
