package lu.luxmove.luxmove_api.gtfs;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class GtfsTripImporter {

    public List<GtfsTrip> readTrips(Path file) throws IOException {

        try (Reader reader = Files.newBufferedReader(file);
             CSVParser parser = CSVFormat.DEFAULT.builder()
                     .setHeader()
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)) {

            List<GtfsTrip> trips = new ArrayList<>();

            for (CSVRecord record : parser) {

                GtfsTrip trip = new GtfsTrip(
                        record.get("trip_id"),
                        record.get("route_id"),
                        record.get("service_id"),
                        record.get("trip_headsign"),
                        record.get("trip_short_name"),
                        parseInteger(record.get("direction_id")),
                        record.get("block_id"),
                        record.get("shape_id"),
                        parseInteger(record.get("wheelchair_accessible")),
                        parseInteger(record.get("bikes_allowed"))
                );

                trips.add(trip);
            }

            return trips;
        }
    }

    private Integer parseInteger(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return Integer.valueOf(value);
    }
}