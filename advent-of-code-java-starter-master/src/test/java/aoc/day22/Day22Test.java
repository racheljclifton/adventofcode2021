package aoc.day22;

import aoc.day17.Day17;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class Day22Test {

    @Test
    public void testPart1(){
        // Given
        List<String> input = new ArrayList<>();
        input.add("");

        // When
        String result = new Day22().part1(input);

        // Then
        assertEquals("", result);
    }

    @Test
    public void testPart2(){
        // Given
        List<String> input = loadInput("day22test2.txt");

        // When
        String result = new Day22().part2(input);

        // Then
        assertEquals("2758514936282235", result);
    }

    @Test
    public void testPart2again(){
        // Given
        List<String> input = loadInput("day22test1.txt");

        // When
        String result = new Day22().part2(input);

        // Then
        assertEquals("39", result);
    }

    private static List<String> loadInput(String fileName){
        try(BufferedReader r = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileName)))){
            return r.lines().collect(toList());
        } catch(IOException e){
            throw new UncheckedIOException(e);
        }
    }

}
