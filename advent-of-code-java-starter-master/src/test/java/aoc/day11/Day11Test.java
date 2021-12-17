package aoc.day11;

import aoc.day01.Day01;
import aoc.day10.Day10;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day11Test {

    @Test
    public void testPart1(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("5483143223");
        input.add("2745854711");
        input.add("5264556173");
        input.add("6141336146");
        input.add("6357385478");
        input.add("4167524645");
        input.add("2176841721");
        input.add("6882881134");
        input.add("4846848554");
        input.add("5283751526");

        // When
        String result = new Day11().part1(input);

        // Then
        assertEquals(String.valueOf(1656), result);
    }

    @Test
    public void testPart2(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("5483143223");
        input.add("2745854711");
        input.add("5264556173");
        input.add("6141336146");
        input.add("6357385478");
        input.add("4167524645");
        input.add("2176841721");
        input.add("6882881134");
        input.add("4846848554");
        input.add("5283751526");

        // When
        String result = new Day11().part2(input);

        // Then
        assertEquals(String.valueOf(195), result);
    }
}
