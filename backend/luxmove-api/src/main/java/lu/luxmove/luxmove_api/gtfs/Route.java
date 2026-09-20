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
        name = "routes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_routes_source_source_id",
                        columnNames = {"source", "source_id"}
                )
        }
)
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String source;

    @Column(name = "source_id", nullable = false)
    private String sourceId;

    @ManyToOne
    @JoinColumn(
            name = "agency_id",
            nullable = false
    )
    private Agency agency;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "long_name")
    private String longName;

    @Column(name = "route_type", nullable = false)
    private Integer routeType;

    @Column(name = "route_color")
    private String routeColor;

    @Column(name = "route_text_color")
    private String routeTextColor;

    @Column
    private String description;

    protected Route() {
    }

    public Route(
            String source,
            String sourceId,
            Agency agency,
            String shortName,
            String longName,
            Integer routeType,
            String routeColor,
            String routeTextColor,
            String description
    ) {
        this.source = source;
        this.sourceId = sourceId;
        this.agency = agency;
        this.shortName = shortName;
        this.longName = longName;
        this.routeType = routeType;
        this.routeColor = routeColor;
        this.routeTextColor = routeTextColor;
        this.description = description;
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

    public Agency getAgency() {
        return agency;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public Integer getRouteType() {
        return routeType;
    }

    public String getRouteColor() {
        return routeColor;
    }

    public String getRouteTextColor() {
        return routeTextColor;
    }

    public String getDescription() {
        return description;
    }

    public void update(
            Agency agency,
            String shortName,
            String longName,
            Integer routeType,
            String routeColor,
            String routeTextColor,
            String description
    ) {
        this.agency = agency;
        this.shortName = shortName;
        this.longName = longName;
        this.routeType = routeType;
        this.routeColor = routeColor;
        this.routeTextColor = routeTextColor;
        this.description = description;
    }

}