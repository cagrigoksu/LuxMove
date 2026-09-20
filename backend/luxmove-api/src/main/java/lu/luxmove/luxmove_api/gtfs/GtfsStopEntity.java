package lu.luxmove.luxmove_api.gtfs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lu.luxmove.luxmove_api.location.Location;

@Entity
@Table(
        name = "gtfs_stops",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_gtfs_stops_source_source_id",
                        columnNames = {"source", "source_id"}
                )
        }
)
public class GtfsStopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String source;

    @Column(name = "source_id", nullable = false)
    private String sourceId;

    @OneToOne
    @JoinColumn(
            name = "location_id",
            nullable = false
    )
    private Location location;

    @Column(nullable = false)
    private String name;

    @Column(name = "location_type", nullable = false)
    private Integer locationType;

    @Column(name = "parent_source_id")
    private String parentSourceId;

    @Column(name = "wheelchair_boarding")
    private Integer wheelchairBoarding;

    @Column(name = "platform_code")
    private String platformCode;

    protected GtfsStopEntity() {
    }

    public GtfsStopEntity(
            String source,
            String sourceId,
            Location location,
            String name,
            Integer locationType
    ) {
        this.source = source;
        this.sourceId = sourceId;
        this.location = location;
        this.name = name;
        this.locationType = locationType;
    }

    public Long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getSourceId() {
        return sourceId;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public Integer getLocationType() {
        return locationType;
    }
}