package com.polytechmtp.kafka.models;

import javax.persistence.*;
import java.util.Date;

@Entity(name= "user_locations")
@Access(AccessType.FIELD)
public class UserLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_id;
    private String user_id;


    public UserLocation(long location_id, String user_id) {
        this.location_id = location_id;
        this.user_id = user_id;
    }

    public UserLocation(){

    }

    public void setLocation_id(long location_id) {
        this.location_id = location_id;
    }

    public long getLocation_id() {
        return location_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}

