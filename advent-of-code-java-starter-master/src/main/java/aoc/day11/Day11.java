package aoc.day11;

import aoc.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 implements Day {

    private long flashes;
    private List<List<Integer>> parsedInput;

    @Override
    public String part1(List<String> input) {
        flashes = 0;
        parsedInput = input.stream()
                .map(line -> Arrays.stream(line
                        .split(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        for (int step = 1; step <= 100; step ++) {
            parsedInput = parsedInput.stream()
                    .map(line -> line.stream()
                            .map(energy -> energy + 1)
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
            for (int i = 0; i < parsedInput.size(); i++) {
                List<Integer> line = parsedInput.get(i);
                for (int j = 0; j < line.size(); j++) {
                    if(line.get(j) >= 9) {
                        flash(j, i, true);
                    }
                }
            }
        }

        return String.valueOf(flashes);
    }

    private void flash(int x, int y, boolean isInitialCheck) {
        if(x < 0 || x >= 10 || y < 0 || y >= 10) return;
        int thisNumber = parsedInput.get(y).get(x);
        if(thisNumber == 0) return;
        if (!isInitialCheck) thisNumber ++;
        if (thisNumber <= 9) {
            parsedInput.get(y).set(x, thisNumber);
            return;
        }
        parsedInput.get(y).set(x, 0);
        flashes++;
        flash(x-1, y-1, false);
        flash(x, y-1, false);
        flash(x+1, y-1, false);
        flash(x-1, y, false);
        flash(x+1, y, false);
        flash(x-1, y+1, false);
        flash(x, y+1, false);
        flash(x+1, y+1, false);
    }

    @Override
    public String part2(List<String> input) {
        flashes = 0;
        parsedInput = input.stream()
                .map(line -> Arrays.stream(line
                        .split(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        boolean allHaveFlashed = false;
        int step = 0;
        while (!allHaveFlashed) {
            step++;
            parsedInput = parsedInput.stream()
                    .map(line -> line.stream()
                            .map(energy -> energy + 1)
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
            for (int i = 0; i < parsedInput.size(); i++) {
                List<Integer> line = parsedInput.get(i);
                for (int j = 0; j < line.size(); j++) {
                    if(line.get(j) >= 9) {
                        flash(j, i, true);
                    }
                }
            }
            int linesOfAllZeros = 0;
            for (List<Integer> integers : parsedInput) {
                if (integers.stream().allMatch(n -> n == 0)) linesOfAllZeros++;
            }
            if (linesOfAllZeros == parsedInput.size()) allHaveFlashed = true;
        }

        return String.valueOf(step);
    }

}
