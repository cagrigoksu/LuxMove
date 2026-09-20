package lu.luxmove.luxmove_api.gtfs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "trips",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_trips_source_source_id",
                        columnNames = {"source", "source_id"}
                )
        }
)
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String source;

    @Column(name = "source_id", nullable = false)
    private String sourceId;

    @ManyToOne
    @JoinColumn(
            name = "route_id",
            nullable = false
    )
    private Route route;

    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Column(name = "headsign")
    private String headsign;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "direction_id")
    private Integer directionId;

    @Column(name = "block_id")
    private String blockId;

    @Column(name = "shape_id")
    private String shapeId;

    @Column(name = "wheelchair_accessible")
    private Integer wheelchairAccessible;

    @Column(name = "bikes_allowed")
    private Integer bikesAllowed;

    protected Trip() {
    }

    public Trip(
            String source,
            String sourceId,
            Route route,
            String serviceId,
            String headsign,
            String shortName,
            Integer directionId,
            String blockId,
            String shapeId,
            Integer wheelchairAccessible,
            Integer bikesAllowed
    ) {
        this.source = source;
        this.sourceId = sourceId;
        this.route = route;
        this.serviceId = serviceId;
        this.headsign = headsign;
        this.shortName = shortName;
        this.directionId = directionId;
        this.blockId = blockId;
        this.shapeId = shapeId;
        this.wheelchairAccessible = wheelchairAccessible;
        this.bikesAllowed = bikesAllowed;
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

    public Route getRoute() {
        return route;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getHeadsign() {
        return headsign;
    }

    public String getShortName() {
        return shortName;
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public String getBlockId() {
        return blockId;
    }

    public String getShapeId() {
        return shapeId;
    }

    public Integer getWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public Integer getBikesAllowed() {
        return bikesAllowed;
    }
}