package com.timesaver.luckyball;

import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class BallNumberGenerator {

    /**
     *  generate numbers
     *  1. start from 0, stop at maxNum (included)
     *  2. will pad zero, if less than ball digits
     *  ex. 0000, 0001 ~ 9999
     */
    public static List<String> getFromTenNumbers(int minBallDigit, int maxNum) {
        return IntStream.range(0, maxNum + 1)
                        .mapToObj(num -> {
                            String numStr = String.valueOf(num);
                            return StringUtils.leftPad(numStr, minBallDigit, "0");
                        })
                        .collect(Collectors.toList());
    }

    /**
     *  generate numbers
     *  1. start from min, stop at maxNum (included)
     *  2. will pad zero, if less than ball digits
     *  ex. 0002 ~ 9999
     */
    public static List<String> getFromTenNumber(int minBallDigit, int min, int maxNum) {
        return IntStream.range(0, maxNum + 1)
                .mapToObj(num -> {
                    String numStr = String.valueOf(num);
                    return StringUtils.leftPad(numStr, minBallDigit, "0");
                })
                .filter(num -> new BigDecimal(num).compareTo(new BigDecimal(min)) >= 0)
                .collect(Collectors.toList());
    }

    /**
     *  generate numbers
     *  1. start from 0, stop at maxNum (included)
     *  2. will pad zero, if less than ball digits
     *  3. numbers will join by separator
     *  ex. 0_0_0_0, 0_0_0_1 ~ 9_9_9_9
     */
    public static List<String> getFromTenNumbersWithSeparator(int ballDigit, int maxNum, String separator) {
        return IntStream.range(0, maxNum + 1)
                        .mapToObj(num -> {
                            String numStr = String.valueOf(num);
                            String number = StringUtils.leftPad(numStr, ballDigit, "0");
                            return StringUtils.join(number.split(""), separator);
                        })
                        .collect(Collectors.toList());
    }

    /**
     *  generate numbers
     *  1. start from min, stop at maxNum (included)
     *  2. will pad zero, if less than ball digits
     *  3. numbers will join by separator
     *  ex. 0_0_0_0, 0_0_0_1 ~ 9_9_9_9
     */
    public static List<String> getNumberWithSeparatorRepeat3Times(int ballDigit, int minNum, int maxNum, String separator) {
        val format = generateStringFormat(ballDigit, separator, 3);
        return IntStream.rangeClosed(minNum, maxNum)
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(minNum, maxNum)
                        .boxed()
                        .flatMap(j -> IntStream.rangeClosed(minNum, maxNum)
                                .mapToObj(k -> String.format(format, i, j, k))))
                .collect(Collectors.toList());
    }

    private static String generateStringFormat(int digit, String separator, int repeatTimes) {
        return IntStream.rangeClosed(1, repeatTimes)
                .mapToObj(i -> "%0" + digit + "d")
                .collect(Collectors.joining(separator));
    }

}
