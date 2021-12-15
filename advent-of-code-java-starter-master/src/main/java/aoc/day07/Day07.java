package aoc.day07;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day07 implements Day {

    @Override
    public String part1(List<String> input) {
        List<Integer> formattedInput = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        Map<Integer, Integer> amountOfFuel = new HashMap<>();
        for(int i = Collections.min(formattedInput); i <= Collections.max(formattedInput); i++) {
            int finalI = i;
            int fuel = formattedInput.stream().reduce(0, (subtotal, location) -> subtotal + Math.abs(finalI - location));
            amountOfFuel.put(i, fuel);
        }
        return String.valueOf(Collections.min(amountOfFuel.values()));
    }

    @Override
    public String part2(List<String> input) {
        List<Integer> formattedInput = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        Map<Integer, Integer> amountOfFuel = new HashMap<>();
        for(int i = Collections.min(formattedInput); i <= Collections.max(formattedInput); i++) {
            int finalI = i;
            int fuel = formattedInput.stream().reduce(0, (subtotal, location) -> subtotal + getAmountOfFuel(Math.abs(finalI - location)));
            amountOfFuel.put(i, fuel);
        }
        return String.valueOf(Collections.min(amountOfFuel.values()));
    }

    private int getAmountOfFuel(int moves) {
        return (moves * (moves + 1))/2;
    }

}
