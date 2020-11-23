package com.polytechmtp.kafka.kafka;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Test {

    public static void main(String[] args) throws IOException {
        String message = "1e7fccc5-b244-439f-bb66-b9eb9ddabde1,43,56,Mon Nov 23 06:58:14 UTC 2020";

        Path pathToFile = Paths.get("logs/position.txt");
        FileWriter myWriter = new FileWriter("position.txt", true);
        BufferedWriter writer = new BufferedWriter(myWriter);
        writer.append(message);
        writer.append("\n");
        writer.flush();
        writer.close();



        System.out.println(pathToFile.toFile().getAbsolutePath());
        Files.write(pathToFile.toAbsolutePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        BufferedWriter bf = new BufferedWriter(new FileWriter(Paths.get("logs/position.txt").toString()));
        System.out.println(bf.toString());

    }
}
