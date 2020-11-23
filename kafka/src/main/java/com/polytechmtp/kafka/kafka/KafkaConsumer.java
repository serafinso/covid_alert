package com.polytechmtp.kafka.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class KafkaConsumer  {

    @KafkaListener(topics="my_topic", groupId="my_group_id")
    public void getMessage(String message) throws IOException {

        System.out.println(message);
        /*try {
            FileWriter myWriter = new FileWriter("position.txt", true);
            BufferedWriter writer = new BufferedWriter(myWriter);

            writer.append(message).append(";");
            writer.append('\n');

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }



}