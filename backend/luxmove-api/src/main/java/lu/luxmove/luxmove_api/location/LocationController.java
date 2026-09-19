package lu.luxmove.luxmove_api.location;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {

        private final LocationRepository locationRepository;
        
        public LocationController(LocationRepository locationRepository)
        {
              this.locationRepository = locationRepository;  
        }

        @GetMapping
        public List<LocationResponse> getLocation(){
                return locationRepository.findAll()
                        .stream()
                        .map(location -> new LocationResponse(
                                location.getName(),
                                location.getLocation().getY(),
                                location.getLocation().getX(),
                                location.getType()
                        )).toList();
        }

}
