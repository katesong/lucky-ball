package com.timesaver.luckyball;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.val;

class BallNumberGeneratorTest {


    @Test
    void test_getFromTenNumbers_4_digit() {

        val totalNumbers = BallNumberGenerator.getFromTenNumbers(4, 9999);

        Assertions.assertEquals(10000, totalNumbers.size());

        Assertions.assertEquals("0000", totalNumbers.get(0));
        Assertions.assertEquals("9999", totalNumbers.get(9999));

        System.out.println("all numbers: " + totalNumbers);
    }

    @Test
    void test_getFromTenNumbers_2_digit_without_zero() {

        val totalNumbers = BallNumberGenerator.getFromTenNumber(2, 1, 58);

        Assertions.assertEquals(58, totalNumbers.size());

        Assertions.assertEquals("01", totalNumbers.get(0));
        Assertions.assertEquals("58", totalNumbers.get(57));

        System.out.println("all numbers: " + totalNumbers);
    }

    /**
     *  四星 組選
     */
    @Test
    void test_4_digit_nonDuplicate() {

        val totalNumbers = BallNumberGenerator.getFromTenNumbers(4, 9999);

        Assertions.assertEquals(10000, totalNumbers.size());


        val totalCombination = CombinationService.filterDuplicate(totalNumbers);

        System.out.println("non duplicate count: " + totalCombination.size());
    }

    @Test
    void test_4_digit_withSeparator() {

        val totalNumbers = BallNumberGenerator.getFromTenNumbersWithSeparator(4, 9999, "_");

        Assertions.assertEquals(10000, totalNumbers.size());

        System.out.println("all numbers: " + totalNumbers);
    }

    @Test
    void test_3_digit_nonDuplicate() {

        val digits = 3;
        val maxNumber = 999;

        val totalNumbers = BallNumberGenerator.getFromTenNumbers(digits, maxNumber);
        val nonDuplicateTotalNumbers = CombinationService.filterDuplicate(totalNumbers);

        Assertions.assertEquals(220, nonDuplicateTotalNumbers.size());

        // 三個不重複數字 ex.012
        val nonDuplicate = getNumberCombinationType(nonDuplicateTotalNumbers, 3);
        Assertions.assertEquals(120, nonDuplicate.size());

        // 兩個重複數字 ex.001
        val twoDuplicates = getNumberCombinationType(nonDuplicateTotalNumbers, 2);
        Assertions.assertEquals(90, twoDuplicates.size());

        // 三個重複數字 ex.000
        val threeDuplicates = getNumberCombinationType(nonDuplicateTotalNumbers, 1);
        Assertions.assertEquals(10, threeDuplicates.size());
    }

    private List<String> getNumberCombinationType(List<String> numbers, int nonDuplicateDigits) {
        return numbers.stream()
                      .map(n -> n.split(""))
                      .filter(nArray -> {
                          val nSet = Stream.of(nArray).collect(Collectors.toSet());
                          return nSet.size() == nonDuplicateDigits;
                      })
                      .map(nArray -> String.join("", nArray))
                      .collect(Collectors.toList());
    }

    @Test
    void test_4_digit_RepeatNumberWithSeparator() {

        val totalNumbers = BallNumberGenerator.getNumberWithSeparatorRepeat3Times(2, 1, 58, "-");

        Assertions.assertEquals("01-01-01", totalNumbers.get(0));
        Assertions.assertEquals("58-58-58", totalNumbers.get(totalNumbers.size() - 1));
        Assertions.assertEquals(58 * 58 * 58, totalNumbers.size());

        System.out.println("all numbers: " + totalNumbers.size());
    }

    @Test
    void test_4_digit_RepeatNumberWithSeparator2() {

        val totalNumbers = BallNumberGenerator.getNumberWithSeparatorRepeat3Times(2, 0, 6, "-");

        Assertions.assertEquals("00-00-00", totalNumbers.get(0));
        Assertions.assertEquals("06-06-06", totalNumbers.get(totalNumbers.size() - 1));
        Assertions.assertEquals(7 * 7 * 7, totalNumbers.size());

        System.out.println("all numbers: " + totalNumbers);
    }

}
