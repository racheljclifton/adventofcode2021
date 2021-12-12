package aoc.day05;

import aoc.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day05 implements Day {

    @Override
    public String part1(List<String> input) {
        List<List<Integer>> filteredInput = input.stream()
                .map(line -> Arrays.stream(String.join(",", line.split(" -> "))
                        .split(","))
                        .map(Integer::parseInt).collect(Collectors.toList()))
                .filter(coordinates -> coordinates.get(0).equals(coordinates.get(2)) || coordinates.get(1).equals(coordinates.get(3)))
                .collect(Collectors.toList());
        Map<String, Integer> ventLocations = new HashMap<>();
        for(List<Integer> line : filteredInput) {
            int x1 = line.get(0) <= line.get(2) ? line.get(0) : line.get(2);
            int x2 = x1 == line.get(0) ? line.get(2) : line.get(0);
            int y1 = line.get(1) <= line.get(3) ? line.get(1) : line.get(3);
            int y2 = y1 == line.get(1) ? line.get(3) : line.get(1);
            if(x1 == x2) {
                createVerticalLine(ventLocations, x1, y1, y2);
            } else if (y1 == y2) {
                createHorizontalLine(ventLocations, y1, x1, x2);
            }
        }
        return String.valueOf(ventLocations.values().stream().reduce(0, (subtotal, number) -> number > 1 ? subtotal+1 : subtotal));
    }

    private void createVerticalLine(Map<String, Integer> ventLocations, int x, int y1, int y2) {
        for (int y = y1; y <= y2; y++) {
            String coordinate = x + "," + y;
            ventLocations.put(coordinate, ventLocations.get(coordinate)==null ? 1 : ventLocations.get(coordinate) + 1);
        }
    }

    private void createHorizontalLine(Map<String, Integer> ventLocations, int y, int x1, int x2) {
        for (int x = x1; x <= x2; x++) {
            String coordinate = x + "," + y;
            ventLocations.put(coordinate, ventLocations.get(coordinate)==null ? 1 : ventLocations.get(coordinate) + 1);
        }
    }

    @Override
    public String part2(List<String> input) {
        List<List<Integer>> formattedInput = input.stream()
                .map(line -> Arrays.stream(String.join(",", line.split(" -> "))
                        .split(","))
                        .map(Integer::parseInt).collect(Collectors.toList()))
                .collect(Collectors.toList());
        Map<String, Integer> ventLocations = new HashMap<>();
        for(List<Integer> line : formattedInput) {
            int x1 = line.get(0);
            int y1 = line.get(1);
            int x2 = line.get(2);
            int y2 = line.get(3);
            if(x1 == x2 ) {
                if(y1 < y2) {
                    createVerticalLine(ventLocations, x1, y1, y2);
                } else {
                    createVerticalLine(ventLocations, x1, y2, y1);
                }
            } else if (y1 == y2) {
                if (x1 < x2) {
                    createHorizontalLine(ventLocations, y1, x1, x2);
                } else {
                    createHorizontalLine(ventLocations, y1, x2, x1);
                }
            } else {
                createDiagonalLine(ventLocations, x1, y1, x2, y2);
            }

        }
        return String.valueOf(ventLocations.values().stream().reduce(0, (subtotal, number) -> number > 1 ? subtotal+1 : subtotal));
    }

    private void createDiagonalLine(Map<String, Integer> ventLocations, int x1, int y1, int x2, int y2) {
        int firstX = Math.min(x1, x2);
        int secondX = Math.max(x1, x2);
        int firstY = firstX == x1 ? y1 : y2;
        int secondY = secondX == x2 ? y2 : y1;
        int y = firstY;
        for (int x = firstX; x <= secondX; x++) {
            String coordinate = x + "," + y;
            ventLocations.put(coordinate, ventLocations.get(coordinate) == null ? 1 : ventLocations.get(coordinate) + 1);
            if (firstY < secondY) {
                y++;
            } else {
                y--;
            }
        }
    }

}
