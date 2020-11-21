package com.polytechmtp.kafka;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducer producer;

    public KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public void writeMessageToTopic(@RequestParam("userId") String userId,
                                    @RequestParam("latitude") String latitude,
                                    @RequestParam("longitude") String longitude,
                                    @RequestParam("date") String date){

        String message = userId + "," + latitude + "," + longitude + "," + date;
        this.producer.writeMessage(message);

    }

}