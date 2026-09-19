package lu.luxmove.luxmove_api.location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(
            name = "location",
            nullable = false,
            columnDefinition = "geography(Point, 4326)"
    )
    private Point location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LocationType type;

    protected Location() {
    }

    public Location(String name, Point location) {
        this.name = name;
        this.location = location;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Point getLocation() {
        return location;
    }

    public LocationType getType(){
        return type;
    }
}