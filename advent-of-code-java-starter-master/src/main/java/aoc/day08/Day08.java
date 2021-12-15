package aoc.day08;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day08 implements Day {

    @Override
    public String part1(List<String> input) {
        return String.valueOf(input.stream()
                .map(line -> Arrays.stream(line
                        .split(" \\| ")[1]
                        .split(" "))
                        .map(String::length)
                        .reduce(0, (subtotal, length) -> length == 2 || length == 3 || length == 4 || length == 7 ? subtotal + 1 : subtotal))
                .reduce(0, Integer::sum));
    }

    @Override
    public String part2(List<String> input) {
        List<List<String[]>> outputList = input.stream()
                .map(line -> Arrays.stream(line.split(" \\| "))
                        .map(output -> output.split(" "))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        Map<String, String> mappingForNumbers = getMappingForNumbers();
        int sumOfOutputValues = 0;
        for (List<String[]> line : outputList) {
            Map<String, Integer> numberOfEachChar = getNumberOfEachChar(line);
            Map<String, String> mappingOfChars = getMappingOfChars(line, numberOfEachChar);
            String actualOutput = Arrays.stream(line.get(1))
                    .map(output -> {
                        if (output.length() == 2) return "1";
                        if (output.length() == 3) return "7";
                        if (output.length() == 4) return "4";
                        if (output.length() == 7) return "8";

                        String transformedOutput = Arrays.stream(output.split(""))
                                .map(mappingOfChars::get)
                                .sorted()
                                .reduce("", String::concat);
                        return mappingForNumbers.get(transformedOutput);
                    }).reduce("", String::concat);
            sumOfOutputValues += Integer.parseInt(actualOutput);
            }
        return String.valueOf(sumOfOutputValues);
    }

    private Map<String, String> getMappingForNumbers() {
        Map<String, String> mappingForNumbers = new HashMap<>();
        mappingForNumbers.put("abcefg", "0");
        mappingForNumbers.put("cf", "1");
        mappingForNumbers.put("acdeg", "2");
        mappingForNumbers.put("acdfg", "3");
        mappingForNumbers.put("bcdf", "4");
        mappingForNumbers.put("abdfg", "5");
        mappingForNumbers.put("abdefg", "6");
        mappingForNumbers.put("acf", "7");
        mappingForNumbers.put("abcdefg", "8");
        mappingForNumbers.put("abcdfg", "9");
        return mappingForNumbers;
    }

    private Map<String, Integer> getNumberOfEachChar(List<String[]> line) {
        Map<String, Integer> numberOfChars = new HashMap<>();
        numberOfChars.put("a", 0);
        numberOfChars.put("b", 0);
        numberOfChars.put("c", 0);
        numberOfChars.put("d", 0);
        numberOfChars.put("e", 0);
        numberOfChars.put("f", 0);
        numberOfChars.put("g", 0);
        String[] testOutput = line.get(0);
        for(String outputItem : testOutput) {
            for(String character : numberOfChars.keySet()) {
                if (outputItem.contains(character)) numberOfChars.put(character, numberOfChars.get(character) + 1);
            }
        }
        return numberOfChars;
    }

    private Map<String, String> getMappingOfChars(List<String[]> line, Map<String, Integer> numberOfEachChar) {
        Map<String, String> mappingOfChars = new HashMap<>();

        List<String> number1 = Arrays.asList(Arrays.stream(line.get(0)).filter(item -> item.length() == 2).collect(Collectors.toList()).get(0).split(""));
        List<String> number7 = Arrays.asList(Arrays.stream(line.get(0)).filter(item -> item.length() == 3).collect(Collectors.toList()).get(0).split(""));
        String characterForA = number7.stream().filter(character -> !number1.contains(character)).collect(Collectors.toList()).get(0);
        mappingOfChars.put(characterForA, "a");

        String number4 = Arrays.stream(line.get(0)).filter(item -> item.length() == 4).collect(Collectors.toList()).get(0);

        numberOfEachChar.forEach((key, val) -> {
            if(val == 9) mappingOfChars.put(key, "f");
            if(val == 6) mappingOfChars.put(key, "b");
            if(val == 4) mappingOfChars.put(key, "e");
            if(val == 8 && !mappingOfChars.containsKey(key)) mappingOfChars.put(key, "c");
            if(val == 7) {
                if(number4.contains(key)) {
                    mappingOfChars.put(key, "d");
                } else {
                    mappingOfChars.put(key, "g");
                }
            }
        });

        return mappingOfChars;
    }

}
