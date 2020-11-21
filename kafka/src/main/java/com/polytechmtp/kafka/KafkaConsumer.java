package com.polytechmtp.contact;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class KafkaConsumer  {

    @KafkaListener(topics="my_topic", groupId="my_group_id")
    public void getMessage(String message){

        System.out.println(message);
        try {
            Files.write(Paths.get("logs/kafka/position.txt"), (message + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println(e);
        }

    }



}