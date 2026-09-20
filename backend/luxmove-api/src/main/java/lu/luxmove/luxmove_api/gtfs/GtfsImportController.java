package lu.luxmove.luxmove_api.gtfs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imports")
public class GtfsImportController {

    private final GtfsImportService importService;

    public GtfsImportController(GtfsImportService importService) {
        this.importService = importService;
    }

    @PostMapping("/gtfs")
    public ResponseEntity<GtfsImportService.ImportResult> importGtfs()
            throws Exception {

        return ResponseEntity.ok(
                importService.importGtfs()
        );
    }
}