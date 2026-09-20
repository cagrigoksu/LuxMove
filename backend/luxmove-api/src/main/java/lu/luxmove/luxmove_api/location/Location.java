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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LocationType type;

    @Column(
            name = "location",
            nullable = false,
            columnDefinition = "geography(Point, 4326)"
    )
    private Point location;

    @Column(nullable = false)
    private String source;

    @Column(name = "source_id", nullable = false)
    private String sourceId;

    protected Location() {
    }

    public Location(
            String name,
            Point location,
            LocationType type,
            String source,
            String sourceId
    ) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.source = source;
        this.sourceId = sourceId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocationType getType() {
        return type;
    }

    public Point getLocation() {
        return location;
    }

    public String getSource() {
        return source;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void updateFromGtfs(
            String name,
            Point location,
            LocationType type
    ) {
        this.name = name;
        this.location = location;
        this.type = type;
    }
}