package com.polytechmtp.kafka.controllers;

import com.polytechmtp.kafka.kafka.KafkaProducer;
import com.polytechmtp.kafka.models.LocationUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class KafkaController {

    private final KafkaProducer producer;

    public KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public void writeMessageToTopic(@RequestBody LocationUser locationUser){

        String message = locationUser.getUserId() + "," + locationUser.getLatitude() + "," + locationUser.getLongitude() + "," + new Date();
        System.out.println(message);
        this.producer.writeMessage(message);

    }

}