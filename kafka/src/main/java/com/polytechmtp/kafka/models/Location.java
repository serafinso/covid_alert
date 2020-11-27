package com.polytechmtp.kafka.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity(name= "locations")
@Access(AccessType.FIELD)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_id;
    private long latitude;
    private long longitude;
    private Date location_date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private KeycloakUser keycloakUser;

    public void setLocation_id(long location_id) {
        this.location_id = location_id;
    }

    public long getLocation_id() {
        return location_id;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public Date getLocation_date() {
        return location_date;
    }

    public void setLocation_date(Date location_date) {
        this.location_date = location_date;
    }

    public KeycloakUser getKeycloakUser() {
        return keycloakUser;
    }

    public void setKeycloakUser(KeycloakUser keycloakUser) {
        this.keycloakUser = keycloakUser;
    }
}
