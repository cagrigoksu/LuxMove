package lu.luxmove.luxmove_api.location;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/locations")
public class LocationController {
    
    @GetMapping
    public List<Location> getLocation(){
        return List.of(
                new Location(
                        "Luxembourg City",
                        49.6116,
                        6.1300
                ),
                new Location(
                        "Luxembourg Gare",
                        49.6008,
                        6.1347
                )

        );
    }

}
