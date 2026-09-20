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
public class GtfsAgencyImporter {

    public List<GtfsAgency> readAgencies(Path file) throws IOException {

        try (Reader reader = Files.newBufferedReader(file);
             CSVParser parser = CSVFormat.DEFAULT.builder()
                     .setHeader()
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)) {

            List<GtfsAgency> agencies = new ArrayList<>();

            for (CSVRecord record : parser) {

                GtfsAgency agency = new GtfsAgency(
                        record.get("agency_id"),
                        record.get("agency_name"),
                        record.get("agency_url"),
                        record.get("agency_timezone"),
                        record.get("agency_lang"),
                        record.get("agency_phone")
                );

                agencies.add(agency);
            }

            return agencies;
        }
    }
}