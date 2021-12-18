package aoc.day17;

import aoc.day14.Day14;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day17Test {

    @Test
    public void testPart1(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("target area: x=20..30, y=-10..-5");

        // When
        String result = new Day17().part1(input);

        // Then
        assertEquals("45", result);
    }

    @Test
    public void testPart2(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("target area: x=20..30, y=-10..-5");

        // When
        String result = new Day17().part2(input);

        // Then
        assertEquals("112", result);
    }

}
