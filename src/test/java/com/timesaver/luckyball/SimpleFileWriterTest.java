package com.timesaver.luckyball;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import lombok.val;

public class SimpleFileWriterTest {

    @Test
    void test() {

        val totalNumbers = BallNumberGenerator.getFromTenNumbers(4, 9999);

        val textSample = "{\n" +
                   "    \"gameCode\": \"TCGVNC\",\n" +
                   "    \"numero\": \"12345\",\n" +
                   "    \"merchant\": \"dfstar\",\n" +
                   "    \"playId\": 2092,\n" +
                   "    \"betNumber\": \"0000\",\n" +
                   "    \"betAmount\": 1\n" +
                   "}";

        val targetText = totalNumbers.stream().map(number -> textSample.replace("0000", number)).collect(Collectors.joining(","));

        SimpleFileWriter.writeToFile(targetText);
    }
}
