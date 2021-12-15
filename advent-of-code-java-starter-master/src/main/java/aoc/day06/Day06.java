package aoc.day06;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 implements Day {

    @Override
    public String part1(List<String> input) {
        List<Integer> formattedInput = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        for (int i = 1; i <= 80; i++) {
            int numOfNewSpawn = formattedInput.stream().reduce(0, (subtotal, number) -> number == 0 ? subtotal + 1 : subtotal);
            formattedInput = formattedInput.stream().map(number -> number == 0 ? 6 : number - 1).collect(Collectors.toList());
            for (int j = 1; j <= numOfNewSpawn; j++) {
                formattedInput.add(8);
            }
        }
        return String.valueOf(formattedInput.size());
    }

    @Override
    public String part2(List<String> input) {
        List<List<Integer>> totalsAfterHalf = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            totalsAfterHalf.add(fishAfter128(i));
        }

        List<Integer> formattedInput = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

        List<Integer> startingTotals = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            startingTotals.add(formattedInput.stream().reduce(0, (subtotal, number) -> number == finalI ? subtotal + 1 : subtotal));
        }

        long totalFish = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j ++) {
                totalFish += (long) startingTotals.get(i) * totalsAfterHalf.get(i).get(j) * totalsAfterHalf.get(j).stream().reduce(0, Integer::sum);
            }
        }

        return String.valueOf(totalFish);
    }

    private List<Integer> fishAfter128(int startingNumber) {
        List<Integer> spawnAtHalfwayMark = new ArrayList<>();
        spawnAtHalfwayMark.add(startingNumber);
        for (int i = 1; i <= 128; i++) {
            int numOfNewSpawn = spawnAtHalfwayMark.stream().reduce(0, (subtotal, number) -> number == 0 ? subtotal + 1 : subtotal);
            spawnAtHalfwayMark = spawnAtHalfwayMark.stream().map(number -> number == 0 ? 6 : number - 1).collect(Collectors.toList());
            for (int j = 1; j <= numOfNewSpawn; j++) {
                spawnAtHalfwayMark.add(8);
            }
        }
        List<Integer> fishAfter128 = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            fishAfter128.add(spawnAtHalfwayMark.stream().reduce(0, (subtotal, number) -> number == finalI ? subtotal + 1 : subtotal));
        }
        return fishAfter128;
    }

}
