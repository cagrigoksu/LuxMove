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
public class GtfsStopTimeImporter {

    public List<GtfsStopTime> readStopTimes(Path file) throws IOException {

        try (Reader reader = Files.newBufferedReader(file);
             CSVParser parser = CSVFormat.DEFAULT.builder()
                     .setHeader()
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)) {

            List<GtfsStopTime> stopTimes = new ArrayList<>();

            for (CSVRecord record : parser) {

                GtfsStopTime stopTime = new GtfsStopTime(
                        record.get("trip_id"),
                        record.get("stop_id"),
                        Integer.parseInt(record.get("stop_sequence")),
                        record.get("arrival_time"),
                        record.get("departure_time"),
                        parseInteger(record.get("pickup_type")),
                        parseInteger(record.get("drop_off_type")),
                        record.get("stop_headsign")
                );

                stopTimes.add(stopTime);
            }

            return stopTimes;
        }
    }

    private Integer parseInteger(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return Integer.valueOf(value);
    }
}