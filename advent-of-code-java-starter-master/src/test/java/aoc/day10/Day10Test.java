package aoc.day10;

import aoc.day01.Day01;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day10Test {

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
        List<String> input = new ArrayList<>();
        input.add("[({(<(())[]>[[{[]{<()<>>");
        input.add("[(()[<>])]({[<{<<[]>>(");
        input.add("{([(<{}[<>[]}>{[]{[(<()>");
        input.add("(((({<>}<{<{<>}{[]{[]{}");
        input.add("[[<[([]))<([[{}[[()]]]");
        input.add("[{[{({}]{}}([{[{{{}}([]");
        input.add("{<[[]]>}<{[{[{[]{()[[[]");
        input.add("[<(<(<(<{}))><([]([]()");
        input.add("<{([([[(<>()){}]>(<<{{");
        input.add("<{([{{}}[<[[[<>{}]]]>[]]");

        // When
        String result = new Day10().part2(input);

        // Then
        assertEquals(String.valueOf(288957), result);
    }
}
