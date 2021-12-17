package aoc.day14;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14 implements Day {

    @Override
    public String part1(List<String> input) {
        List<String> polymerTemplate = Arrays.asList(input.get(0).split(""));
        Map<String, String> pairInsertionRules = input.subList(2, input.size()).stream()
                .map(line -> line.split(" -> "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(item -> item[0], item -> item[1]),
                        Collections::<String, String>unmodifiableMap));
        Map<String, Integer> numberOfElements = new HashMap<>();
        for(String element : polymerTemplate) {
            numberOfElements.put(element, numberOfElements.containsKey(element) ? numberOfElements.get(element) +1 : 1);
        }
        List<String> newPolymer = polymerTemplate.stream().collect(Collectors.toList());
        for (int i = 1; i <= 10; i++) {
            for (int j = polymerTemplate.size()-2; j >= 0; j--){
                String newElement = pairInsertionRules.get(polymerTemplate.get(j).concat(polymerTemplate.get(j+1)));
                newPolymer.add(j+1, newElement);
                numberOfElements.put(newElement, numberOfElements.containsKey(newElement) ? numberOfElements.get(newElement) +1 : 1);
            }
            polymerTemplate = newPolymer.stream().collect(Collectors.toList());
        }
        return String.valueOf(Collections.max(numberOfElements.values()) - Collections.min(numberOfElements.values()));
    }

    @Override
    public String part2(List<String> input) {
        List<String> polymerTemplate = Arrays.asList(input.get(0).split(""));
        Map<String, String> pairInsertionRules = input.subList(2, input.size()).stream()
                .map(line -> line.split(" -> "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(item -> item[0], item -> item[1]),
                        Collections::<String, String>unmodifiableMap));
        Map<String, Long> numberOfElements = new HashMap<>();
        for(String element : polymerTemplate) {
            numberOfElements.put(element, numberOfElements.containsKey(element) ? numberOfElements.get(element) +1 : 1);
        }

        for (int k = 0; k < polymerTemplate.size()-1; k ++) {
            List<String> startingPolymer = polymerTemplate.subList(k, k+2);
            List<String> newPolymer = startingPolymer.stream().collect(Collectors.toList());
            for (int i = 1; i <= 40; i++) {
                for (int j = startingPolymer.size()-2; j >= 0; j--){
                    String newElement = pairInsertionRules.get(startingPolymer.get(j).concat(startingPolymer.get(j+1)));
                    newPolymer.add(j+1, newElement);
                    numberOfElements.put(newElement, numberOfElements.containsKey(newElement) ? numberOfElements.get(newElement) +1 : 1);
                }
                startingPolymer = newPolymer.stream().collect(Collectors.toList());
            }
        }

        return String.valueOf(Collections.max(numberOfElements.values()) - Collections.min(numberOfElements.values()));
    }


}
