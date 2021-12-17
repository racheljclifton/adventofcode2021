package aoc.day12;

import aoc.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12 implements Day {

    Map<String, Set<String>> caveMap;
    List<List<String>> paths;

    @Override
    public String part1(List<String> input) {
        caveMap = makeMap(input);
        paths = new ArrayList<>();
        findPath("start", new ArrayList<>());
        return String.valueOf(paths.size());
    }

    private void findPath(String cave, List<String> pathSoFar) {
        if(cave.equals(cave.toLowerCase()) && pathSoFar.contains(cave)) return;
        List<String> thisPath = pathSoFar.stream().collect(Collectors.toList());
        thisPath.add(cave);
        if(cave.equals("end")) {
            paths.add(thisPath);
            return;
        }
        for (String nextCave : caveMap.get(cave)) {
            findPath(nextCave, thisPath);
        }
    }

    private Map<String, Set<String>> makeMap(List<String> input) {
        Map<String, Set<String>> caveMap = new HashMap<>();
        for(String line : input) {
            String[] caves = line.split("-");
            Set<String> neighbors;
            if(caveMap.containsKey(caves[0])) {
                neighbors = caveMap.get(caves[0]);
            } else {
                neighbors = new HashSet<>();
            }
            neighbors.add(caves[1]);
            caveMap.put(caves[0], neighbors);
            if(caveMap.containsKey(caves[1])) {
                neighbors = caveMap.get(caves[1]);
            } else {
                neighbors = new HashSet<>();
            }
            neighbors.add(caves[0]);
            caveMap.put(caves[1], neighbors);
        }
        return caveMap;
    }

    @Override
    public String part2(List<String> input) {
        caveMap = makeMap(input);
        paths = new ArrayList<>();
        findNewPath("start", new Path());
        return String.valueOf(paths.size());
    }

    private void findNewPath(String cave, Path pathSoFar) {
        Path thisPath = new Path(pathSoFar);
        if(cave.equals(cave.toLowerCase()) && thisPath.thePath.contains(cave)) {
            if (cave.equals("start")) return;
            if (thisPath.littleCaveVisitedTwice) return;
            thisPath.littleCaveVisitedTwice = true;
        }
        thisPath.thePath.add(cave);
        if(cave.equals("end")) {
            paths.add(thisPath.thePath);
            return;
        }
        for (String nextCave : caveMap.get(cave)) {
            findNewPath(nextCave, thisPath);
        }
    }

    public class Path {
        boolean littleCaveVisitedTwice;
        List<String> thePath;

        public Path() {
            littleCaveVisitedTwice = false;
            thePath = new ArrayList<>();
        }

        public Path(Path otherPath) {
            this.littleCaveVisitedTwice = otherPath.littleCaveVisitedTwice;
            this.thePath = otherPath.thePath.stream().collect(Collectors.toList());
        }
    }

}
