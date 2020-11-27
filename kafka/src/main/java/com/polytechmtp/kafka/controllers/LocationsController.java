package com.polytechmtp.kafka.controllers;

import com.polytechmtp.kafka.models.Location;
import com.polytechmtp.kafka.repositories.LocationRepository;
import com.polytechmtp.kafka.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.List;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import com.polytechmtp.kafka.services.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public List<Location> list() throws IOException {
        return locationRepository.findAll();
    }

    @GetMapping
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String[] listLocation() throws IOException {
        return LocationService.getMessage();
    }


    @PostMapping
    @RequestMapping(value = "/{user_id}", method = RequestMethod.POST)
    public Location create(@PathVariable (value = "user_id") String user_id, @RequestBody Location location) {
        return userRepository.findById(user_id).map(user -> {
            location.setKeycloakUser(user);
            return locationRepository.saveAndFlush(location);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + user_id + " not found"));
    }

    @GetMapping
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public void setLocationsByUserPositive(@PathVariable String id) throws IOException, ParseException {
        ArrayList<String> newContactUsers = new ArrayList<>();
        for (String location: LocationService.getMessage()) {
            System.out.println(location);
            String[] infos = location.split(",");
            if(infos[0].equals(id)) {
                Location loc = new Location(
                        Double.parseDouble(infos[1]),
                        Double.parseDouble(infos[2]),
                        Timestamp.valueOf(infos[3])
                );
                userRepository.findById(infos[0]).map(user -> {
                    loc.setKeycloakUser(user);
                    return locationRepository.saveAndFlush(loc);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + infos[0] + " not found"));
            }
        }
        for(Location locPos: locationRepository.get(id)) {
            for (String location: LocationService.getMessage()) {
                String[] infos = location.split(",");
                if(!infos[0].equals(id)) {
                    if(LocationService.distance(locPos.getLatitude(), Double.parseDouble(infos[1]),
                            locPos.getLongitude(), Double.parseDouble(infos[2])) < 12) {
                        if((int) LocationService.getDateDiff(locPos.getLocation_date(), Timestamp.valueOf(infos[3]),TimeUnit.MINUTES) <5 ) {
                            if(userRepository.getOne(infos[0]).getState().equals("OK")) {
                                userRepository.updateState("Contact", infos[0]);
                                if(!newContactUsers.contains(infos[0])) {
                                    newContactUsers.add(infos[0]);
                                }
                            }
                        }
                    }
                }
            }
        }
        for(String user: newContactUsers) {
            System.out.println(user);
            locationService.sendEmail(user);
        }
    }

    @GetMapping
    @RequestMapping(value = "{idLocation}", method = RequestMethod.GET)
    public Location get(@PathVariable Long idLocation) {
        return locationRepository.getOne(idLocation);
    }



    @DeleteMapping("/{location_id}/{user_id}")
    public ResponseEntity < ? > deleteCourse(@PathVariable(value = "user_id") String user_id,
                                             @PathVariable(value = "location_id") Long location_id) throws ResponseStatusException {
        return locationRepository.findById(location_id).map(location -> {
            locationRepository.delete(location);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found Location with id " + location_id + " and user " + user_id));
    }


}
