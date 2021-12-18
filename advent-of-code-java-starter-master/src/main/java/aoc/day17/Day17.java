package aoc.day17;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day17 implements Day {

    @Override
    public String part1(List<String> input) {
        String[] splitInput = input.get(0).split(" ");
        List<Integer> xRange = Arrays.stream(splitInput[2]
                .replace("x=", "")
                .replace(",", "")
                .split("\\.\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> yRange = Arrays.stream(splitInput[3]
                .replace("y=", "")
                .split("\\.\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int leftX = xRange.get(0);
        int rightX = xRange.get(1);
        int bottomY = yRange.get(0);
        int topY = yRange.get(1);
        List<Position> possibleXValues = new ArrayList<>();
        for (int probePosition = rightX; probePosition > 0; probePosition--) {
            int xVelocity = probePosition;
            int currentLocation = 0;
            int steps = 0;
            Position position = null;
            while(xVelocity > 0) {
                currentLocation += xVelocity;
                if(currentLocation >= leftX && currentLocation <= rightX) {
                    if (position == null) {
                        position = new Position(probePosition, steps);
                        possibleXValues.add(position);
                    } else {
                        position.addNumberOfSteps(steps);
                    }
                }
                if(currentLocation >= rightX) break;
                xVelocity--;
                steps++;
            }
        }
        Set<Integer> possibleSteps = possibleXValues.stream()
                .map(position -> position.numberOfStepsToGetInRange)
                .flatMap(Collection::stream).collect(Collectors.toSet());
        int highest = 0;
        boolean solutionFound = false;
        int probePosition = Math.abs(bottomY) - 1;
        while (!solutionFound) {
            int steps = 0;
            int yVelocity = probePosition;
            int highestOnTrajectory = 0;
            int currentLocation = 0;
            while(currentLocation >= bottomY) {
                currentLocation += yVelocity;
                if(currentLocation > highestOnTrajectory) highestOnTrajectory = currentLocation;
                if(currentLocation <= topY && currentLocation >= bottomY && steps >= Collections.min(possibleSteps)) {
                    solutionFound = true;
                    highest = highestOnTrajectory;
                    break;
                }
                yVelocity--;
                steps++;
            }
            probePosition--;
        }
        return String.valueOf(highest);
    }

    public class Position {
        int probePosition;
        List<Integer> numberOfStepsToGetInRange;
        boolean zeroInsideOfZone;
        public Position (int probePosition, int numberOfStepsToGetInRange) {
            this.probePosition = probePosition;
            this.numberOfStepsToGetInRange = new ArrayList<>();
            this.numberOfStepsToGetInRange.add(numberOfStepsToGetInRange);
            zeroInsideOfZone = false;
        }
        public void addNumberOfSteps(int numberOfStepsToGetIntoRange) {
            this.numberOfStepsToGetInRange.add(numberOfStepsToGetIntoRange);

        }
    }

    @Override
    public String part2(List<String> input) {
        String[] splitInput = input.get(0).split(" ");
        List<Integer> xRange = Arrays.stream(splitInput[2]
                .replace("x=", "")
                .replace(",", "")
                .split("\\.\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> yRange = Arrays.stream(splitInput[3]
                .replace("y=", "")
                .split("\\.\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int leftX = xRange.get(0);
        int rightX = xRange.get(1);
        int bottomY = yRange.get(0);
        int topY = yRange.get(1);
        List<Position> possibleXValues = new ArrayList<>();
        for (int probePosition = rightX; probePosition > 0; probePosition--) {
            int xVelocity = probePosition;
            int currentLocation = 0;
            int steps = 0;
            Position position = null;
            while(xVelocity >= 0) {
                currentLocation += xVelocity;
                if(currentLocation >= leftX && currentLocation <= rightX) {
                    if (position == null) {
                        position = new Position(probePosition, steps);
                        possibleXValues.add(position);
                    } else {
                        position.addNumberOfSteps(steps);
                    }
                    if (xVelocity == 0) position.zeroInsideOfZone = true;
                }
                if(currentLocation > rightX) break;
                xVelocity--;
                steps++;
            }
        }
        Set<Integer> possibleSteps = possibleXValues.stream()
                .map(position -> position.numberOfStepsToGetInRange)
                .flatMap(Collection::stream).collect(Collectors.toSet());
        Set<String> possibleTrajectories = new HashSet<>();
        for (int probePosition = Math.abs(bottomY) -1; probePosition >= bottomY; probePosition--) {
            int steps = 0;
            int yVelocity = probePosition;
            int currentLocation = 0;
            while(currentLocation >= bottomY) {
                currentLocation += yVelocity;
                if(currentLocation <= topY && currentLocation >= bottomY && steps >= Collections.min(possibleSteps)) {
                    //figure out all the matching x values and add to possible trajectories
                    for(Position position : possibleXValues) {
                        if (position.numberOfStepsToGetInRange.contains(steps)
                                || (position.zeroInsideOfZone && steps >= Collections.min(position.numberOfStepsToGetInRange))) {
                            possibleTrajectories.add(position.probePosition + "," + probePosition);
                        }
                    }
                }
                yVelocity--;
                steps++;
            }
        }
        return String.valueOf(possibleTrajectories.size());
    }


}
