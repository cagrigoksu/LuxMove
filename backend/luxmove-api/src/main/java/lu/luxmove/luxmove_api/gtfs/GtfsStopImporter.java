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
public class GtfsStopImporter {

    public List<GtfsStop> readStops(Path file) throws IOException {

        try (Reader reader = Files.newBufferedReader(file);
             CSVParser parser = CSVFormat.DEFAULT.builder()
                     .setHeader()
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)) {

            List<GtfsStop> stops = new ArrayList<>();

            for (CSVRecord record : parser) {
                GtfsStop stop = new GtfsStop(
                        record.get("stop_id"),
                        record.get("stop_name"),
                        Double.parseDouble(record.get("stop_lat")),
                        Double.parseDouble(record.get("stop_lon")),
                        Integer.parseInt(record.get("location_type"))
                );

                stops.add(stop);
            }

            return stops;
        }
    }
}