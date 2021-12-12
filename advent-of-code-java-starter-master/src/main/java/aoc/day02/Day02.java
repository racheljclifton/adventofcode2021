package aoc.day02;

import aoc.Day;

import java.util.List;

public class Day02 implements Day {

    @Override
    public String part1(List<String> input) {
        int forward = 0;
        int depth = 0;

        for (String instruction : input) {
            String[] parsedInstruction = instruction.split(" ");
            String direction = parsedInstruction[0];
            int amount = Integer.parseInt(parsedInstruction[1]);
            if (direction.equals("forward")) {
                forward += amount;
            } else if (direction.equals("up")) {
                depth -= amount;
            } else if (direction.equals("down")) {
                depth += amount;
            }
        }
        return String.valueOf(forward * depth);
    }

    @Override
    public String part2(List<String> input) {
        int forward = 0;
        int depth = 0;
        int aim = 0;

        for (String instruction : input) {
            String[] parsedInstruction = instruction.split(" ");
            String direction = parsedInstruction[0];
            int amount = Integer.parseInt(parsedInstruction[1]);
            if (direction.equals("forward")) {
                forward += amount;
                depth += aim * amount;
            } else if (direction.equals("up")) {
                aim -= amount;
            } else if (direction.equals("down")) {
                aim += amount;
            }
        }
        return String.valueOf(forward * depth);
    }

}
