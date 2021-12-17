package aoc.day12;

import aoc.day11.Day11;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day12Test {

    @Test
    public void testPart1(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("dc-end");
        input.add("HN-start");
        input.add("start-kj");
        input.add("dc-start");
        input.add("dc-HN");
        input.add("LN-dc");
        input.add("HN-end");
        input.add("kj-sa");
        input.add("kj-HN");
        input.add("kj-dc");

        // When
        String result = new Day12().part1(input);

        // Then
        assertEquals(String.valueOf(19), result);
    }

    @Test
    public void testPart1Again(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("fs-end");
        input.add("he-DX");
        input.add("fs-he");
        input.add("start-DX");
        input.add("pj-DX");
        input.add("end-zg");
        input.add("zg-sl");
        input.add("zg-pj");
        input.add("pj-he");
        input.add("RW-he");
        input.add("fs-DX");
        input.add("pj-RW");
        input.add("zg-RW");
        input.add("start-pj");
        input.add("he-WI");
        input.add("zg-he");
        input.add("pj-fs");
        input.add("start-RW");

        // When
        String result = new Day12().part1(input);

        // Then
        assertEquals(String.valueOf(226), result);
    }

    @Test
    public void testPart2(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("start-A");
        input.add("start-b");
        input.add("A-c");
        input.add("A-b");
        input.add("b-d");
        input.add("A-end");
        input.add("b-end");

        // When
        String result = new Day12().part2(input);

        // Then
        assertEquals(String.valueOf(36), result);
    }

    @Test
    public void testPart2Again(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("dc-end");
        input.add("HN-start");
        input.add("start-kj");
        input.add("dc-start");
        input.add("dc-HN");
        input.add("LN-dc");
        input.add("HN-end");
        input.add("kj-sa");
        input.add("kj-HN");
        input.add("kj-dc");
        // When
        String result = new Day12().part2(input);

        // Then
        assertEquals(String.valueOf(103), result);
    }

    @Test
    public void testPart2AgainAgain(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("fs-end");
        input.add("he-DX");
        input.add("fs-he");
        input.add("start-DX");
        input.add("pj-DX");
        input.add("end-zg");
        input.add("zg-sl");
        input.add("zg-pj");
        input.add("pj-he");
        input.add("RW-he");
        input.add("fs-DX");
        input.add("pj-RW");
        input.add("zg-RW");
        input.add("start-pj");
        input.add("he-WI");
        input.add("zg-he");
        input.add("pj-fs");
        input.add("start-RW");
        // When
        String result = new Day12().part2(input);

        // Then
        assertEquals(String.valueOf(3509), result);
    }
}
