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
        name = "stop_times",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_stop_times_trip_sequence",
                        columnNames = {"trip_id", "stop_sequence"}
                )
        }
)
public class StopTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "trip_id",
            nullable = false
    )
    private Trip trip;

    @ManyToOne
    @JoinColumn(
            name = "gtfs_stop_id",
            nullable = false
    )
    private GtfsStopEntity gtfsStop;

    @Column(name = "stop_sequence", nullable = false)
    private Integer stopSequence;

    @Column(name = "arrival_time")
    private String arrivalTime;

    @Column(name = "departure_time")
    private String departureTime;

    @Column(name = "pickup_type")
    private Integer pickupType;

    @Column(name = "drop_off_type")
    private Integer dropOffType;

    @Column(name = "stop_headsign")
    private String stopHeadsign;

    protected StopTime() {
    }

    public StopTime(
            Trip trip,
            GtfsStopEntity gtfsStop,
            Integer stopSequence,
            String arrivalTime,
            String departureTime,
            Integer pickupType,
            Integer dropOffType,
            String stopHeadsign
    ) {
        this.trip = trip;
        this.gtfsStop = gtfsStop;
        this.stopSequence = stopSequence;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.pickupType = pickupType;
        this.dropOffType = dropOffType;
        this.stopHeadsign = stopHeadsign;
    }

    public Long getId() {
        return id;
    }

    public Trip getTrip() {
        return trip;
    }

    public GtfsStopEntity getGtfsStop() {
        return gtfsStop;
    }

    public Integer getStopSequence() {
        return stopSequence;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public Integer getPickupType() {
        return pickupType;
    }

    public Integer getDropOffType() {
        return dropOffType;
    }

    public String getStopHeadsign() {
        return stopHeadsign;
    }
}