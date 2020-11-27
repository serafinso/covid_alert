package com.polytechmtp.kafka.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name= "locations")
@Access(AccessType.FIELD)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_id;
    private double latitude;
    private double longitude;
    private Timestamp location_date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private KeycloakUser keycloakUser;

    public Location(long location_id, double latitude, double longitude, Timestamp location_date,
                    KeycloakUser keycloakUser) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location_date = location_date;
        this.keycloakUser = keycloakUser;
    }

    public Location(double latitude, double longitude, Timestamp location_date,
                    KeycloakUser keycloakUser) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.location_date = location_date;
        this.keycloakUser = keycloakUser;
    }

    public Location(){

    }

    public Location(double latitude, double longitude, Timestamp location_date) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.location_date = location_date;
    }

    public void setLocation_id(long location_id) {
        this.location_id = location_id;
    }

    public long getLocation_id() {
        return location_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Timestamp getLocation_date() {
        return location_date;
    }

    public void setLocation_date(Timestamp location_date) {
        this.location_date = location_date;
    }

    public KeycloakUser getKeycloakUser() {
        return keycloakUser;
    }

    public void setKeycloakUser(KeycloakUser keycloakUser) {
        this.keycloakUser = keycloakUser;
    }
}
