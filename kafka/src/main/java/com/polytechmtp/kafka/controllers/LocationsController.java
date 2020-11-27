package com.polytechmtp.kafka.controllers;

import com.polytechmtp.kafka.models.KeycloakUser;
import com.polytechmtp.kafka.models.Location;
import com.polytechmtp.kafka.repositories.LocationRepository;
import com.polytechmtp.kafka.repositories.UserLocationRepository;
import com.polytechmtp.kafka.repositories.UserRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    public String[] getMessage() throws IOException {
        BufferedReader reader;
        String[] ret = new String[0];
        File file = new File("positions-logs/my_topic-0/00000000000000000000.log");
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Canonical Path: " + file.getCanonicalPath());
        System.out.println("Path: " + file.getPath());
        try {
            reader = new BufferedReader(new FileReader(
                    "/usr/src/positions-logs/my_topic-0/00000000000000000000.log"));
            String line = reader.readLine();
            while (line != null) {
                ret = ArrayUtils.addAll(ret,StringUtils.substringsBetween(line, "[", "]"));
                line = reader.readLine();
            }
            reader.close();
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLocationRepository userLocationRepository;

    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public List<Location> list() throws IOException {
        return locationRepository.findAll();
    }

    @GetMapping
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String[] listLocation() throws IOException {
        String[] position = getMessage();
        return position;
    }

    @PostMapping("/{user_id}")
    public Location create(@PathVariable (value = "user_id") String user_id, @RequestBody Location location) {
        return userRepository.findById(user_id).map(user -> {
            location.setKeycloakUser(user);
            return locationRepository.save(location);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User with ID "+user_id+" not found"));
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
