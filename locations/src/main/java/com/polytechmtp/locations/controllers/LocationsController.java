package com.polytechmtp.locations.controllers;


import com.polytechmtp.locations.models.Location;
import com.polytechmtp.locations.repositories.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    @Autowired
    private LocationRepository locationRepository ;

    @GetMapping
    public List<Location> list(){
        return locationRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Location get(@PathVariable Long id) {
        System.out.println("LOCATIONNNNNNNNNNNN "+ id);
        if (locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Location with ID "+id+" not found");
        }
        return locationRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody final Location location) {
        return locationRepository.saveAndFlush(location);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE )
    public void delete ( @PathVariable Long id){
        if (locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Location with ID "+id+" not found");
        }
        // TODO: Toujours verifier s’il faut les enregistrements enfants
        locationRepository.deleteById(id);
    }

    @PutMapping
    @RequestMapping(value="{id}", method=RequestMethod.PUT)
    public Location update (@PathVariable Long id , @RequestBody Location location) {

        // TODO: Ajouter ici une validation si tous les champs ont ete passes

        if (locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Location with ID "+id+" not found");
        }
        // TODO: Sinon, retourner une erreur 400 (Bad Payload)
        Location existingLocation = locationRepository.getOne(id);
        BeanUtils.copyProperties(location,existingLocation ,"location_id");
        return locationRepository.saveAndFlush(existingLocation);
    }
}
