package aoc.day14;

import aoc.day12.Day12;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day14Test {

    @Test
    public void testPart1(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("NNCB");
        input.add("");
        input.add("CH -> B");
        input.add("HH -> N");
        input.add("CB -> H");
        input.add("NH -> C");
        input.add("HB -> C");
        input.add("HC -> B");
        input.add("HN -> C");
        input.add("NN -> C");
        input.add("BH -> H");
        input.add("NC -> B");
        input.add("NB -> B");
        input.add("BN -> B");
        input.add("BB -> N");
        input.add("BC -> B");
        input.add("CC -> N");
        input.add("CN -> C");

        // When
        String result = new Day14().part1(input);

        // Then
        assertEquals(String.valueOf(1588), result);
    }

    @Test
    public void testPart2(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("NNCB");
        input.add("");
        input.add("CH -> B");
        input.add("HH -> N");
        input.add("CB -> H");
        input.add("NH -> C");
        input.add("HB -> C");
        input.add("HC -> B");
        input.add("HN -> C");
        input.add("NN -> C");
        input.add("BH -> H");
        input.add("NC -> B");
        input.add("NB -> B");
        input.add("BN -> B");
        input.add("BB -> N");
        input.add("BC -> B");
        input.add("CC -> N");
        input.add("CN -> C");

        // When
        String result = new Day14().part2(input);

        // Then
        assertEquals(String.valueOf(2188189693529L), result);
    }

}
