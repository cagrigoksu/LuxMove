package lu.luxmove.luxmove_api.gtfs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class GtfsImportRunner implements CommandLineRunner {

    private final GtfsImportService importService;

    public GtfsImportRunner(GtfsImportService importService) {
        this.importService = importService;
    }

    @Override
    public void run(String... args) throws Exception {

        Path file = Path.of(
                "../../data/gtfs/current/stops.txt"
        );

        GtfsImportService.ImportResult result =
                importService.importStops(file);

        System.out.println();
        System.out.println("GTFS import completed");
        System.out.println("---------------------");
        System.out.println("Read:     " + result.read());
        System.out.println("Inserted: " + result.inserted());
        System.out.println("Updated:  " + result.updated());
        System.out.println();
    }
}