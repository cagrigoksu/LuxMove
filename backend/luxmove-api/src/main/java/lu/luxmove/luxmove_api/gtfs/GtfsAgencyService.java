package lu.luxmove.luxmove_api.gtfs;

import org.springframework.stereotype.Service;

@Service
public class GtfsAgencyService {

    private static final String SOURCE = "GTFS";

    private final AgencyRepository agencyRepository;

    public GtfsAgencyService(
            AgencyRepository agencyRepository
    ) {
        this.agencyRepository = agencyRepository;
    }

    public Agency saveAgency(GtfsAgency gtfsAgency) {

        Agency agency = agencyRepository
                .findBySourceAndSourceId(
                        SOURCE,
                        gtfsAgency.agencyId()
                )
                .orElseGet(() -> new Agency(
                        SOURCE,
                        gtfsAgency.agencyId(),
                        gtfsAgency.agencyName(),
                        gtfsAgency.agencyUrl(),
                        gtfsAgency.agencyTimezone(),
                        gtfsAgency.agencyLanguage(),
                        gtfsAgency.agencyPhone()
                ));

        return agencyRepository.save(agency);
    }
}
