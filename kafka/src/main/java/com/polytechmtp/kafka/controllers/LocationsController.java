package com.polytechmtp.kafka.controllers;

import com.polytechmtp.kafka.models.Location;
import com.polytechmtp.kafka.repositories.LocationRepository;
import com.polytechmtp.kafka.repositories.UserLocationRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    public String[] getMessage() throws IOException {
        BufferedReader reader;
        String[] ret = new String[0];
        File file = new File("kafka-logs/my_topic-0/00000000000000000000.log");
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Canonical Path: " + file.getCanonicalPath());
        System.out.println("Path: " + file.getPath());
        try {
            reader = new BufferedReader(new FileReader(
                    "/usr/src/kafka-logs/my_topic-0/00000000000000000000.log"));
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

    @GetMapping
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Location get(@PathVariable Long id) {
        return locationRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody final Location user) {
        return locationRepository.saveAndFlush(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete ( @PathVariable Long id){
        // TODO: Toujours verifier s’il faut les enregistrements enfants
        locationRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public Location update (@PathVariable Long id) {
        // TODO: Ajouter ici une validation si tous les champs ont ete passes
        // TODO: Sinon, retourner une erreur 400 (Bad Payload)
        Location existingUser = locationRepository.getOne(id);
        return locationRepository.saveAndFlush(existingUser);
    }
}
