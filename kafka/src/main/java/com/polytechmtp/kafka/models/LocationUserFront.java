package com.polytechmtp.kafka.models;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocationUserFront {

    private String userId;
    private String latitude;
    private String longitude;
    private Timestamp date;

    public LocationUserFront(String userId, String latitude, String longitude) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LocationUserFront{" +
                "userId='" + userId + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", date=" + date +
                '}';
    }
}
