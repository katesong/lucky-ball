package com.timesaver.luckyball;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CombinationService {

    /**
     *  000, 001, 010, 100, 002
     *  -> 000, 001, 002
     */
    public static List<String> filterDuplicate(List<String> numbers) {
        return numbers.stream()
                      .map(num -> Stream.of(num.split("")).sorted().collect(Collectors.joining()))
                      .distinct()
                      .collect(Collectors.toList());
    }

    /**
     *  0_0_0, 0_0_1, 0_1_0, 1_0_0, 0_0_2
     *  -> 0_0_0, 0_0_1, 0_0_2
     */
    public static List<String> filterDuplicateWithSeparator(List<String> numbers, String delimiter) {
        return numbers.stream()
                .map(num -> Stream.of(num.split(delimiter)).sorted().collect(Collectors.joining(delimiter)))
                .distinct()
                .collect(Collectors.toList());
    }
}
