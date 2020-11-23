package com.polytechmtp.kafka.models;

import java.util.Date;

public class LocationUserFront {

    private String userId;
    private String latitude;
    private String longitude;
    private Date date;

    public LocationUserFront(String userId, String latitude, String longitude) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = new Date();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
