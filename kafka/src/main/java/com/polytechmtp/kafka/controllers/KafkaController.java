package com.polytechmtp.kafka.controllers;

import com.polytechmtp.kafka.kafka.KafkaProducer;
import com.polytechmtp.kafka.models.LocationUserFront;
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
    public void writeMessageToTopic(@RequestBody LocationUserFront locationUserFront){

        String message = "[" + locationUserFront.getUserId() + "," + locationUserFront.getLatitude() + "," + locationUserFront.getLongitude() + "," + locationUserFront.getDate() + "]";
        System.out.println(message);
        this.producer.writeMessage(message);

    }

}