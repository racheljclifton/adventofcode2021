package aoc.day01;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day01Test {

    @Test
    public void testPart1(){
        // Given
        List<String> input = Collections.singletonList("test");

        // When
        String result = new Day01().part1(input);

        // Then
        assertEquals(input.get(0), result);
    }

    @Test
    public void testPart2(){
        // Given
        List<String> input = Collections.singletonList("test");

        // When
        String result = new Day01().part2(input);

        // Then
        assertEquals(input.get(0), result);
    }
}
