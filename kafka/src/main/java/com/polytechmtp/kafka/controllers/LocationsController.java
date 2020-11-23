package com.polytechmtp.kafka.controllers;

import com.polytechmtp.kafka.models.Location;
import com.polytechmtp.kafka.repositories.LocationRepository;
import com.polytechmtp.kafka.repositories.UserLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationsController {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserLocationRepository userLocationRepository;

    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public List<Location> list(){
        return locationRepository.findAll();
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
    public Location update (@PathVariable Long id ) {
        // TODO: Ajouter ici une validation si tous les champs ont ete passes
        // TODO: Sinon, retourner une erreur 400 (Bad Payload)
        Location existingUser = locationRepository.getOne(id);
        return locationRepository.saveAndFlush(existingUser);
    }
}
