package com.timesaver.luckyball;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class BallNumberGenerator {

    public static List<String> getFromTenNumbers(int ballDigit, int maxNum) {
        return IntStream.range(0, maxNum + 1)
                        .mapToObj(num -> {
                            String numStr = String.valueOf(num);
                            return StringUtils.leftPad(numStr, ballDigit, "0");
                        })
                        .collect(Collectors.toList());
    }

}
