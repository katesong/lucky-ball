package com.timesaver.luckyball;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CombinationService {

    public static List<String> filterDuplicate(List<String> numbers) {
        return numbers.stream()
                      .map(num -> Stream.of(num.split("")).sorted().collect(Collectors.joining()))
                      .distinct()
                      .collect(Collectors.toList());
    }
}
