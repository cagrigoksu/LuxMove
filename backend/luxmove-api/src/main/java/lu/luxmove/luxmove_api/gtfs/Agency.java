package lu.luxmove.luxmove_api.gtfs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "agencies",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_agencies_source_source_id",
                        columnNames = {"source", "source_id"}
                )
        }
)
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String source;

    @Column(name = "source_id", nullable = false)
    private String sourceId;

    @Column(nullable = false)
    private String name;

    @Column(name = "url")
    private String url;

    @Column(nullable = false)
    private String timezone;

    @Column(name = "language")
    private String language;

    @Column(name = "phone")
    private String phone;

    protected Agency() {
    }

    public Agency(
            String source,
            String sourceId,
            String name,
            String url,
            String timezone,
            String language,
            String phone
    ) {
        this.source = source;
        this.sourceId = sourceId;
        this.name = name;
        this.url = url;
        this.timezone = timezone;
        this.language = language;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getLanguage() {
        return language;
    }

    public String getPhone() {
        return phone;
    }
}