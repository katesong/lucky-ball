package com.timesaver.luckyball;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.val;

@Service
public class SimpleFileWriter {

    public static void writeToFile(String text) {
        try {
            val fileName = "C:\\workspace_my\\lucky-ball_" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ".txt";

            FileWriter writer = new FileWriter(fileName);

            writer.write(text);

            writer.close();

            System.out.println("Successfully wrote to file : " + fileName);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
