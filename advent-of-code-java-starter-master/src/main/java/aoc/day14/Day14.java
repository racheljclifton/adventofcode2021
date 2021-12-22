package aoc.day14;

import aoc.Day;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14 implements Day {

    private Map<String, String> pairInsertionRules;
    private Map<String, Long> numberOfElements;
    private Map<String, Map<Integer, Map<String, Long>>> cachedSteps;

    @Override
    public String part1(List<String> input) {
        List<String> polymerTemplate = Arrays.asList(input.get(0).split(""));
        pairInsertionRules = input.subList(2, input.size()).stream()
                .map(line -> line.split(" -> "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(item -> item[0], item -> item[1]),
                        Collections::<String, String>unmodifiableMap));
        numberOfElements = new HashMap<>();
        for (String element : polymerTemplate) {
            numberOfElements.put(element, numberOfElements.containsKey(element) ? numberOfElements.get(element) + 1 : 1);
        }
        List<String> newPolymer = polymerTemplate.stream().collect(Collectors.toList());
        for (int i = 1; i <= 10; i++) {
            for (int j = polymerTemplate.size() - 2; j >= 0; j--) {
                String newElement = pairInsertionRules.get(polymerTemplate.get(j).concat(polymerTemplate.get(j + 1)));
                newPolymer.add(j + 1, newElement);
                numberOfElements.put(newElement, numberOfElements.containsKey(newElement) ? numberOfElements.get(newElement) + 1 : 1);
            }
            polymerTemplate = newPolymer.stream().collect(Collectors.toList());
        }
        return String.valueOf(Collections.max(numberOfElements.values()) - Collections.min(numberOfElements.values()));
    }

    @Override
    public String part2(List<String> input) {
        cachedSteps = new HashMap<>();
        List<String> polymerTemplate = Arrays.asList(input.get(0).split(""));
        pairInsertionRules = input.subList(2, input.size()).stream()
                .map(line -> line.split(" -> "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(item -> item[0], item -> item[1]),
                        Collections::<String, String>unmodifiableMap));
        numberOfElements = new HashMap<>();
        for (int i = 0; i < polymerTemplate.size() - 1; i += 1) {
            String polymerPair = polymerTemplate.get(i).concat(polymerTemplate.get(i + 1));
            processPolymerPair(polymerPair, 0);
            Map<String, Long> totals = cachedSteps.get(polymerPair).get(0);
            for (Map.Entry<String, Long> total : totals.entrySet()) {
                String element = total.getKey();
                Long number = total.getValue();
                numberOfElements.put(element, numberOfElements.containsKey(element) ? numberOfElements.get(element) + number : number);
            }
        }

        return String.valueOf(Collections.max(numberOfElements.values()) - Collections.min(numberOfElements.values()));
    }

    private void processPolymerPair(String polymerPair, int step) {
        if(cachedSteps.containsKey(polymerPair) && cachedSteps.get(polymerPair).containsKey(step)) {
            return;
        }

        if(step == 40) {
            Map<String, Long> totals = new HashMap<>();
            if (polymerPair.charAt(0) == polymerPair.charAt(1)) {
                totals.put(polymerPair.charAt(0) + "", 2L);
            } else {
                totals.put(polymerPair.charAt(0) + "", 1L);
                totals.put(polymerPair.charAt(1) + "", 1L);
            }
            if (!cachedSteps.containsKey(polymerPair)) cachedSteps.put(polymerPair, new HashMap<>());
            cachedSteps.get(polymerPair).put(step, totals);
        } else {
            String newElement = pairInsertionRules.get(polymerPair);
            String pairOne = polymerPair.charAt(0) + newElement;
            String pairTwo = newElement + polymerPair.charAt(1);
            processPolymerPair(pairOne, step + 1);
            processPolymerPair(pairTwo, step + 1);

            Map<String, Long> firstTotals = cachedSteps.get(pairOne).get(step + 1);
            Map<String, Long> secondTotals = cachedSteps.get(pairTwo).get(step + 1);
            Map<String, Long> totals = new HashMap<>();
            for (Map.Entry<String, Long> total : firstTotals.entrySet()) {
                String element = total.getKey();
                Long number = total.getValue();
                totals.put(element, totals.containsKey(element) ? totals.get(element) + number : number);
            }
            for (Map.Entry<String, Long> total : secondTotals.entrySet()) {
                String element = total.getKey();
                Long number = total.getValue();
                totals.put(element, totals.containsKey(element) ? totals.get(element) + number : number);
            }
            totals.put(newElement, totals.get(newElement) - 1);
            if (!cachedSteps.containsKey(polymerPair)) cachedSteps.put(polymerPair, new HashMap<>());
            cachedSteps.get(polymerPair).put(step, totals);
        }

    }


}
