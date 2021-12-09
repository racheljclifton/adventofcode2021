package aoc.day01;

import aoc.Day;

import java.util.List;

public class Day01 implements Day {

    @Override
    public String part1(List<String> input) {
        int numberOfIncreases = 0;
        for (int i = 1; i < input.size(); i++) {
            if(Integer.parseInt(input.get(i-1)) < Integer.parseInt(input.get(i))) {
                numberOfIncreases++;
            }
        }
        return String.valueOf(numberOfIncreases);
    }

    @Override
    public String part2(List<String> input) {
        int numberOfIncreases = 0;
        int firstSum = Integer.parseInt(input.get(0)) + Integer.parseInt(input.get(1)) +Integer.parseInt(input.get(2));
        for (int i = 3; i < input.size(); i++) {
            int secondSum = Integer.parseInt(input.get(i-2)) + Integer.parseInt(input.get(i-1)) + Integer.parseInt(input.get(i));
            if(firstSum < secondSum) {
                numberOfIncreases++;
            }
            firstSum = secondSum;
        }
        return String.valueOf(numberOfIncreases);
    }

}
