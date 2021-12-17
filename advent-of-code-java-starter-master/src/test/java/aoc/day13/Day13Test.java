package aoc.day13;

import aoc.day12.Day12;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day13Test {

    @Test
    public void testPart1(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("6,10");
        input.add("0,14");
        input.add("9,10");
        input.add("0,3");
        input.add("10,4");
        input.add("4,11");
        input.add("6,0");
        input.add("6,12");
        input.add("4,1");
        input.add("0,13");
        input.add("10,12");
        input.add("3,4");
        input.add("3,0");
        input.add("8,4");
        input.add("1,10");
        input.add("2,14");
        input.add("8,10");
        input.add("9,0");
        input.add("");
        input.add("fold along y=7");
        input.add("fold along x=5");


        // When
        String result = new Day13().part1(input);

        // Then
        assertEquals(String.valueOf(17), result);
    }

}
