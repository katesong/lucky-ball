package com.timesaver.luckyball;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.val;

class BallNumberGeneratorTest {

    @Test
    void test_4_digit() {

        val totalNumbers = BallNumberGenerator.getFromTenNumbers(4, 9999);

        Assertions.assertEquals(10000, totalNumbers.size());


        val totalCombination = CombinationService.filterDuplicate(totalNumbers);

        System.out.println("non duplicate count: " + totalCombination.size());
    }

}
