package com.polytechmtp.keycloak.controllers;

import com.polytechmtp.keycloak.models.KeycloakUser;
import com.polytechmtp.keycloak.repositories.UserRepository;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/users")
public class UserController {

    String kafkaUrl = "http://localhost:8083/locations";

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
    public void setState (@PathVariable String id, @RequestParam String state) throws Exception {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");
        }
        userRepository.updateState(state, id);
    }

}
