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
public class GtfsRouteImporter {

    public List<GtfsRoute> readRoutes(Path file) throws IOException {

        try (Reader reader = Files.newBufferedReader(file);
             CSVParser parser = CSVFormat.DEFAULT.builder()
                     .setHeader()
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)) {

            List<GtfsRoute> routes = new ArrayList<>();

            for (CSVRecord record : parser) {

                GtfsRoute route = new GtfsRoute(
                        record.get("route_id"),
                        record.get("agency_id"),
                        record.get("route_short_name"),
                        record.get("route_long_name"),
                        Integer.parseInt(record.get("route_type")),
                        record.get("route_color"),
                        record.get("route_text_color"),
                        record.get("route_desc")
                );

                routes.add(route);
            }

            return routes;
        }
    }
}