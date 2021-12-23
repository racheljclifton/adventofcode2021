package aoc.day22;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day22 implements Day {

    @Override
    public String part1(List<String> input) {
        List<List<List<Integer>>> grid = new ArrayList<>();
        for (int x = 0; x <= 100; x++) {
            grid.add(new ArrayList<>());
            for (int y = 0; y <= 100; y++) {
                grid.get(x).add(new ArrayList<>());
                for (int z = 0; z <= 100; z++) {
                    grid.get(x).get(y).add(0);
                }
            }
        }
        for (String line : input) {
            String[] splitLine = line.split(" ");
            int turnedOn = splitLine[0].equals("on") ? 1 : 0;
            String[] splitAxes = splitLine[1].split(",");
            List<List<Integer>> axes = Arrays.stream(splitAxes)
                    .map(axis -> Arrays.stream(axis
                            .replace("x=", "")
                            .replace("y=", "")
                            .replace("z=", "")
                            .split("\\.\\."))
                            .map(endpoint -> Integer.parseInt(endpoint) + 50)
                            .collect(Collectors.toList())).collect(Collectors.toList());
            boolean outsideOfRange = false;
            for (List<Integer> axis : axes) {
                if (axis.get(0) > 101 || axis.get(1) < 0) {
                    outsideOfRange = true;
                    break;
                } else {
                    if (axis.get(0) < 0) {
                        axis.set(0, 0);
                    }
                    if (axis.get(1) > 101) {
                        axis.set(1, 101);
                    }
                }
            }
            if (outsideOfRange) break;

            for (int x = axes.get(0).get(0); x <= axes.get(0).get(1); x++) {
                for (int y = axes.get(1).get(0); y <= axes.get(1).get(1); y++) {
                    for (int z = axes.get(2).get(0); z <= axes.get(2).get(1); z++) {
                        grid.get(x).get(y).set(z, turnedOn);
                    }
                }
            }

        }
        return String.valueOf(grid.stream()
                .map(x -> x.stream()
                        .map(y -> y.stream()
                                .reduce(0, Integer::sum))
                        .reduce(0, Integer::sum))
                .reduce(0, Integer::sum));
    }

    @Override
    public String part2(List<String> input) {
        List<List<List<Integer>>> cuboidsTurnedOn = new ArrayList<>();

        for (String line : input) {
            String[] splitLine = line.split(" ");
            boolean turnedOn = splitLine[0].equals("on");
            String[] splitAxes = splitLine[1].split(",");
            List<List<Integer>> axes = Arrays.stream(splitAxes)
                    .map(axis -> Arrays.stream(axis
                            .replace("x=", "")
                            .replace("y=", "")
                            .replace("z=", "")
                            .split("\\.\\."))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())).collect(Collectors.toList());
            if (cuboidsTurnedOn.isEmpty() && turnedOn) {
                cuboidsTurnedOn.add(axes);
            } else {
                List<Integer> xAxis = axes.get(0);
                List<Integer> yAxis = axes.get(1);
                List<Integer> zAxis = axes.get(2);
                List<List<List<Integer>>> allOverlaps = new ArrayList<>();
                List<List<List<Integer>>> cubesToFractureAndReAdd = new ArrayList<>();
                Iterator<List<List<Integer>>> cuboidIterator = cuboidsTurnedOn.iterator();
                while (cuboidIterator.hasNext()) {
                    List<List<Integer>> cube = cuboidIterator.next();
                    if (newCubeCompletelyCovers(cube, axes)) {
                        cuboidIterator.remove();
                        continue;
                    }
                    List<Integer> xOverlap = getOverlap(cube.get(0), xAxis);
                    List<Integer> yOverlap = getOverlap(cube.get(1), yAxis);
                    List<Integer> zOverlap = getOverlap(cube.get(2), zAxis);
                    if (xOverlap != null && yOverlap != null && zOverlap != null) {
                        List<List<Integer>> overlap = new ArrayList<>();
                        overlap.add(xOverlap);
                        overlap.add(yOverlap);
                        overlap.add(zOverlap);
                        allOverlaps.add(overlap);
                        cubesToFractureAndReAdd.add(cube);
                        cuboidIterator.remove();
                    }
                }

                //too many cubes are being turned off during a turn off overlap
                if (turnedOn) {
                    cuboidsTurnedOn.add(axes);
                }
                for (int i = 0; i < cubesToFractureAndReAdd.size(); i++) {
                    List<List<Integer>> cubeToFracture = cubesToFractureAndReAdd.get(i);
                    List<List<Integer>> overlapCube = allOverlaps.get(i);
                    if (cubeToFracture.get(0).get(0) < overlapCube.get(0).get(0)) {
                        List<List<Integer>> newCube = new ArrayList<>();
                        List<Integer> xRange = new ArrayList<>();
                        xRange.add(cubeToFracture.get(0).get(0));
                        xRange.add(overlapCube.get(0).get(0) - 1);
                        newCube.add(xRange);
                        newCube.add(cubeToFracture.get(1).stream().collect(Collectors.toList()));
                        newCube.add(cubeToFracture.get(2).stream().collect(Collectors.toList()));
                        cuboidsTurnedOn.add(newCube);
                        cubeToFracture.get(0).set(0, overlapCube.get(0).get(0));
                    }
                    if (cubeToFracture.get(0).get(1) > overlapCube.get(0).get(1)) {
                        List<List<Integer>> newCube = new ArrayList<>();
                        List<Integer> xRange = new ArrayList<>();
                        xRange.add(overlapCube.get(0).get(1) + 1);
                        xRange.add(cubeToFracture.get(0).get(1));
                        newCube.add(xRange);
                        newCube.add(cubeToFracture.get(1).stream().collect(Collectors.toList()));
                        newCube.add(cubeToFracture.get(2).stream().collect(Collectors.toList()));
                        cuboidsTurnedOn.add(newCube);
                        cubeToFracture.get(0).set(1, overlapCube.get(0).get(1));
                    }
                    if (cubeToFracture.get(1).get(0) < overlapCube.get(1).get(0)) {
                        List<List<Integer>> newCube = new ArrayList<>();
                        newCube.add(cubeToFracture.get(0).stream().collect(Collectors.toList()));
                        List<Integer> yRange = new ArrayList<>();
                        yRange.add(cubeToFracture.get(1).get(0));
                        yRange.add(overlapCube.get(1).get(0) - 1);
                        newCube.add(yRange);
                        newCube.add(cubeToFracture.get(2).stream().collect(Collectors.toList()));
                        cuboidsTurnedOn.add(newCube);
                        cubeToFracture.get(1).set(0, overlapCube.get(1).get(0));
                    }
                    if (cubeToFracture.get(1).get(1) > overlapCube.get(1).get(1)) {
                        List<List<Integer>> newCube = new ArrayList<>();
                        newCube.add(cubeToFracture.get(0).stream().collect(Collectors.toList()));
                        List<Integer> yRange = new ArrayList<>();
                        yRange.add(overlapCube.get(1).get(1) + 1);
                        yRange.add(cubeToFracture.get(1).get(1));
                        newCube.add(yRange);
                        newCube.add(cubeToFracture.get(2).stream().collect(Collectors.toList()));
                        cuboidsTurnedOn.add(newCube);
                        cubeToFracture.get(1).set(1, overlapCube.get(1).get(1));
                    }
                    if (cubeToFracture.get(2).get(0) < overlapCube.get(2).get(0)) {
                        List<List<Integer>> newCube = new ArrayList<>();
                        newCube.add(cubeToFracture.get(0).stream().collect(Collectors.toList()));
                        newCube.add(cubeToFracture.get(1).stream().collect(Collectors.toList()));
                        List<Integer> zRange = new ArrayList<>();
                        zRange.add(cubeToFracture.get(2).get(0));
                        zRange.add(overlapCube.get(2).get(0) - 1);
                        newCube.add(zRange);
                        cuboidsTurnedOn.add(newCube);
                        cubeToFracture.get(2).set(0, overlapCube.get(2).get(0));
                    }
                    if (cubeToFracture.get(2).get(1) > overlapCube.get(2).get(1)) {
                        List<List<Integer>> newCube = new ArrayList<>();
                        newCube.add(cubeToFracture.get(0).stream().collect(Collectors.toList()));
                        newCube.add(cubeToFracture.get(1).stream().collect(Collectors.toList()));
                        List<Integer> zRange = new ArrayList<>();
                        zRange.add(overlapCube.get(2).get(1) + 1);
                        zRange.add(cubeToFracture.get(2).get(1));
                        newCube.add(zRange);
                        cuboidsTurnedOn.add(newCube);
                        cubeToFracture.get(2).set(1, overlapCube.get(2).get(1));
                    }
                }
            }
        }

        return String.valueOf(cuboidsTurnedOn.stream()
                .map(cube -> cube.stream()
                        .map(axis -> (long) axis.get(1) - axis.get(0) + 1)
                        .reduce(1L, (subproduct, measurement) -> subproduct * measurement))
                .reduce(0L, Long::sum));
}

    private boolean newCubeCompletelyCovers(List<List<Integer>> cube1, List<List<Integer>> cube2) {
        return cube2.get(0).get(0) <= cube1.get(0).get(0) && cube2.get(0).get(1) >= cube1.get(0).get(1)
                && cube2.get(1).get(0) <= cube1.get(1).get(0) && cube2.get(1).get(1) >= cube1.get(1).get(1)
                && cube2.get(2).get(0) <= cube1.get(2).get(0) && cube2.get(2).get(1) >= cube1.get(2).get(1);
    }

    private List<Integer> getOverlap(List<Integer> axis1, List<Integer> axis2) {
        List<Integer> leftAxis;
        List<Integer> rightAxis;
        if (axis1.get(0) <= axis2.get(0)) {
            leftAxis = axis1;
            rightAxis = axis2;
        } else {
            leftAxis = axis2;
            rightAxis = axis1;
        }
        if (leftAxis.get(1) < rightAxis.get(0)) return null;
        List<Integer> overlap = new ArrayList<>();
        overlap.add(rightAxis.get(0));
        overlap.add(rightAxis.get(1) < leftAxis.get(1) ? rightAxis.get(1) : leftAxis.get(1));
        return overlap;

    }


}
