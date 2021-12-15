package aoc.day09;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day09 implements Day {

    @Override
    public String part1(List<String> input) {
        List<List<Integer>> transformedInput = input.stream()
                .map(line -> Arrays.stream(line
                        .split(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        int riskLevel = 0;
        for (int i = 0; i < transformedInput.size(); i++) {
            List<Integer> line = transformedInput.get(i);
            for (int j = 0; j < line.size(); j++) {
                int thisNumber = line.get(j);
                if(i != 0 && thisNumber >= transformedInput.get(i-1).get(j)) continue;
                if(i != transformedInput.size()-1 && thisNumber >= transformedInput.get(i+1).get(j)) continue;
                if(j != 0 && thisNumber >= line.get(j-1)) continue;
                if(j != line.size()-1 && thisNumber >= line.get(j+1)) continue;
                riskLevel += thisNumber + 1;
            }
        }
        return String.valueOf(riskLevel);
    }

    @Override
    public String part2(List<String> input) {
        List<List<Location>> transformedInput = input.stream()
                .map(line -> Arrays.stream(line
                        .split(""))
                        .map(Integer::parseInt)
                        .map(Location::new)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        List<int[]> lowPoints = new ArrayList<>();
        for (int i = 0; i < transformedInput.size(); i++) {
            List<Location> line = transformedInput.get(i);
            for (int j = 0; j < line.size(); j++) {
                int thisNumber = line.get(j).getVal();
                if(i != 0 && thisNumber >= transformedInput.get(i-1).get(j).getVal()) continue;
                if(i != transformedInput.size()-1 && thisNumber >= transformedInput.get(i+1).get(j).getVal()) continue;
                if(j != 0 && thisNumber >= line.get(j-1).getVal()) continue;
                if(j != line.size()-1 && thisNumber >= line.get(j+1).getVal()) continue;
                lowPoints.add(new int[]{j, i});
            }
        }
        int height = transformedInput.size();
        int width = transformedInput.get(0).size();
        List<Integer> basinSizes = new ArrayList<>();
        for (int[] lowPoint : lowPoints) {
            basinSizes.add(floodFill(transformedInput, lowPoint[0], lowPoint[1], 0, height, width));
        }
        List<Integer> sortedBasins =  basinSizes.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return String.valueOf(sortedBasins.get(0) * sortedBasins.get(1) * sortedBasins.get(2));
    }

    private int floodFill(List<List<Location>> input, int x, int y, int basinSize, int height, int width) {
        if (x < 0 || x >= width || y < 0 || y >= height) return basinSize;
        if (input.get(y).get(x).getVal() == 9 || input.get(y).get(x).isVisited()) return basinSize;
        input.get(y).get(x).setVisited(true);
        int newBasinSize = basinSize + 1;
        newBasinSize = floodFill(input, x+1, y,  newBasinSize, height, width);
        newBasinSize = floodFill(input, x-1, y,  newBasinSize, height, width);
        newBasinSize = floodFill(input, x, y+1, newBasinSize, height, width);
        newBasinSize = floodFill(input, x, y-1, newBasinSize, height, width);
        return newBasinSize;
    }

    public class Location {
        private boolean visited;
        private final int val;

        public Location(int val) {
            this.visited = false;
            this.val = val;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public int getVal() {
            return val;
        }
    }

}
