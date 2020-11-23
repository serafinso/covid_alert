package com.polytechmtp.keycloak.controllers;

import com.polytechmtp.keycloak.models.KeycloakUser;
import com.polytechmtp.keycloak.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<KeycloakUser> list(){
        return userRepository.findAll();
    }

    @GetMapping
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public KeycloakUser get (@PathVariable String id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with ID "+id+" not found");
        }
        return userRepository.getOne(id);
    }

    @PutMapping
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void setState (@PathVariable String id, @RequestParam String state) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with ID "+id+" not found");
        }
        userRepository.updateState(state, id);

        if (state.equals("Positive")) {

        }
    }

}
