package com.timesaver.luckyball;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.val;

public class AnyPlaceTest {

    @Test
    public void test_5_digits_twoPlace_sameNumber() {
        val numbers = BallNumberGenerator.getFromTenNumbers(5, 99999);
        val testCase = "00";
        val result = findNumbersContain(testCase, numbers);

        System.out.println(result);

        System.out.println("total number = " + numbers.size());
        System.out.println("result = " + result.size());

        Assertions.assertEquals(8146, result.size());
    }

    @Test
    public void test_5_digits_twoPlace_differentNumber() {
        val numbers = BallNumberGenerator.getFromTenNumbers(5, 99999);
        val testCase = "01";
        val result = findNumbersContain(testCase, numbers);

        System.out.println(result);

        System.out.println("total number = " + numbers.size());
        System.out.println("result = " + result.size());

        Assertions.assertEquals(14670, result.size());
    }

    @Test
    public void test_4_digits_twoPlace_sameNumber() {
        val numbers = BallNumberGenerator.getFromTenNumbers(4, 9999);
        val testCase = "00";
        val result = findNumbersContain(testCase, numbers);

        System.out.println(result);

        System.out.println("total number = " + numbers.size());
        System.out.println("result = " + result.size());

        Assertions.assertEquals(523, result.size());
    }

    @Test
    public void test_4_digits_twoPlace_differentNumber() {
        val numbers = BallNumberGenerator.getFromTenNumbers(4, 9999);
        val testCase = "01";
        val result = findNumbersContain(testCase, numbers);

        System.out.println(result);

        System.out.println("total number = " + numbers.size());
        System.out.println("result = " + result.size());

        Assertions.assertEquals(974, result.size());
    }

    @Test
    public void test_3_digits_twoPlace_sameNumber() {
        val numbers = BallNumberGenerator.getFromTenNumbers(3, 999);
        val testCase = "00";
        val result = findNumbersContain(testCase, numbers);

        System.out.println(result);

        System.out.println("total number = " + numbers.size());
        System.out.println("result = " + result.size());

        Assertions.assertEquals(28, result.size());
    }

    @Test
    public void test_3_digits_twoPlace_differentNumber() {
        val numbers = BallNumberGenerator.getFromTenNumbers(3, 999);
        val testCase = "01";
        val result = findNumbersContain(testCase, numbers);

        System.out.println(result);

        System.out.println("total number = " + numbers.size());
        System.out.println("result = " + result.size());

        Assertions.assertEquals(54, result.size());
    }

    @Test
    public void test_2_digits_onePlace() {
        val numbers = BallNumberGenerator.getFromTenNumbers(2, 99);
        val testCase = "0";
        val result = findNumbersContain(testCase, numbers);

        System.out.println(result);

        System.out.println("total number = " + numbers.size());
        System.out.println("result = " + result.size());

        Assertions.assertEquals(19, result.size());
    }

    private List<String> findNumbersContain(String target, List<String> allNumbers) {
        val targetMap = Stream.of(target.split("")).collect(Collectors.groupingBy(t -> t, Collectors.counting()));
        return allNumbers.stream()
                         .filter(numbers -> {
                             val numberMap = Stream.of(numbers.split("")).collect(Collectors.groupingBy(t -> t, Collectors.counting()));

                             val isMatch = targetMap.entrySet().stream().allMatch(entry -> {
                                 val numberCount = numberMap.get(entry.getKey());
                                 return numberCount != null && numberCount >= entry.getValue();
                             });

                             System.out.println("number[" + numbers + "] target [" + target + "] is match ? " + isMatch);
                             return isMatch;
                         })
                         .collect(Collectors.toList());

    }

    @Test
    public void test_total_bet_num() {
        val result = twoPlaceCombination("0123456789", "0123456789");
        Assertions.assertEquals(55, result.size());
    }

    private Set<String> twoPlaceCombination(String first, String second) {
        return Stream.of(first.split(""))
                .flatMap(f -> Stream.of(second.split(""))
                                    .map(s -> List.of(f, s).stream().sorted().collect(Collectors.joining())))
                .collect(Collectors.toSet());
    }

    public static Set<String> permutation(String number) {
        return permutate(number).collect(Collectors.toSet());
    }

    private static Stream<String> permutate(CharSequence str) {
        if(str.length() <= 1) {
            return Stream.of(str.toString());
        }
        return IntStream.range(0, str.length())
                        .boxed()
                        .flatMap(index -> permutate(new StringBuilder(str).deleteCharAt(index))
                                .map(s -> str.charAt(index) + s));
    }

}
