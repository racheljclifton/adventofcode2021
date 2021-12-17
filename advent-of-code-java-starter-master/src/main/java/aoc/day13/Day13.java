package aoc.day13;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 implements Day {

    @Override
    public String part1(List<String> input) {
        int blankLineIndex = input.indexOf("");
        List<List<Integer>> coordinates = input.subList(0, blankLineIndex).stream()
                .map(line -> Arrays.stream(line.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        List<List<String>> transparentPaper = makePaperWithDots(coordinates);
        List<String[]> directions = input.subList(blankLineIndex + 1, input.size()).stream()
                .map(line -> line.split(" ")[2]
                        .split("="))
                .collect(Collectors.toList());
        List<List<String>> foldedPaper = foldPaper(transparentPaper, directions.get(0));
        return String.valueOf(foldedPaper.stream()
                .map(line -> line.stream()
                        .map(space -> space.equals("#") ? 1 : 0)
                        .reduce(0, Integer::sum))
                .reduce(0, Integer::sum));
    }

    private List<List<String>> foldPaper(List<List<String>> transparentPaper, String[] direction) {
        int foldAt = Integer.parseInt(String.valueOf(direction[1]));
        List<List<String>> foldedPaper;
        if(direction[0].equals("y")) {
            foldedPaper = transparentPaper.subList(0, foldAt + 1).stream().collect(Collectors.toList());
            for (int y = 1; y < transparentPaper.size() - foldAt; y++) {
                for (int x = 0; x < transparentPaper.get(0).size(); x ++) {
                    if (transparentPaper.get(foldAt + y).get(x).equals("#")) {
                        foldedPaper.get(foldAt - y).set(x, "#");
                    }
                }
            }
        } else {
            foldedPaper = transparentPaper.stream()
                    .map(line -> line
                            .subList(0, foldAt + 1))
                    .collect(Collectors.toList())
                    .stream().collect(Collectors.toList());
            for (int x = 1; x < transparentPaper.get(0).size() - foldAt; x++) {
                for (int y = 0; y < transparentPaper.size(); y++) {
                    if (transparentPaper.get(y).get(foldAt + x).equals("#")) {
                        foldedPaper.get(y).set(foldAt - x, "#");
                    }
                }
            }
        }
        return foldedPaper;
    }

    private List<List<String>> makePaperWithDots(List<List<Integer>> coordinates) {
        int largestCoordinate = Collections.max(coordinates.stream().map(Collections::max).collect(Collectors.toList()));
        List<List<String>> transparentPaper = new ArrayList<>();
        for (int i = 0; i <= largestCoordinate; i++) {
            transparentPaper.add(new ArrayList<>());
            for(int j = 0; j <= largestCoordinate; j++) {
                transparentPaper.get(i).add(j, ".");
            }
        }
        for (List<Integer> coordinate : coordinates) {
            transparentPaper.get(coordinate.get(1)).set(coordinate.get(0), "#");
        }
        return transparentPaper;
    }

    @Override
    public String part2(List<String> input) {
        int blankLineIndex = input.indexOf("");
        List<List<Integer>> coordinates = input.subList(0, blankLineIndex).stream()
                .map(line -> Arrays.stream(line.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        List<List<String>> transparentPaper = makePaperWithDots(coordinates);
        List<String[]> directions = input.subList(blankLineIndex + 1, input.size()).stream()
                .map(line -> line.split(" ")[2]
                        .split("="))
                .collect(Collectors.toList());
        List<List<String>> foldedPaper = transparentPaper;
        for(String[] direction : directions) {
            foldedPaper = foldPaper(foldedPaper, direction);
        }
        for(List<String> line : foldedPaper) {
            System.out.println(line);
        }
        return null;
    }


}
