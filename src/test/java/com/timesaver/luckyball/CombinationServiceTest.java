package com.timesaver.luckyball;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CombinationServiceTest {

    @Test
    public void test_filterDuplicate() {
        val testCase = List.of("000", "010", "001", "100", "002");
        val result = CombinationService.filterDuplicate(testCase);

        System.out.println(result);

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("000", result.get(0));
    }

    @Test
    public void test_filterDuplicate_withSeparator() {
        val testCase = List.of("0,0,0", "0,1,0", "0,0,1", "1,0,0", "0,0,2");
        val result = CombinationService.filterDuplicateWithSeparator(testCase, ",");

        System.out.println(result);

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("0,0,0", result.get(0));
    }

}
