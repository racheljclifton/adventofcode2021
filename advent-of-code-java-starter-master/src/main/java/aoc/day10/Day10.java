package aoc.day10;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day10 implements Day {

    private Map<String, String> matchingBraces;

    @Override
    public String part1(List<String> input) {
        List<String> errors = new ArrayList<>();
        buildBracesMap();
        List<String> fixedInput = input.stream().collect(Collectors.toList());
        for (String line : input) {
            Stack<String> braces = new Stack<>();
            for (int i = 0; i < line.length(); i++) {
                String brace = String.valueOf(line.charAt(i));
                if (!matchingBraces.containsKey(brace)) {
                    braces.push(brace);
                } else {
                    if (braces.isEmpty()) {
                        errors.add(brace);
                        fixedInput.remove(line);
                        break;
                    } else {
                        if (!braces.pop().equals(matchingBraces.get(brace))) {
                            errors.add(brace);
                            fixedInput.remove(line);
                            break;
                        }
                    }
                }
            }
        }

        return String.valueOf(errors.stream()
                .map(brace -> {
                    if (brace.equals(")")) return 3;
                    if (brace.equals("]")) return 57;
                    if (brace.equals("}")) return 1197;
                    if (brace.equals(">")) return 25137;
                    return 0;
                })
                .reduce(0, Integer::sum));
    }

    @Override
    public String part2(List<String> input) {
        List<Long> scores = new ArrayList<>();
        buildBracesMap();
        List<String> fixedInput = input.stream().collect(Collectors.toList());
        for (String line : input) {
            Stack<String> braces = new Stack<>();
            for (int i = 0; i < line.length(); i++) {
                String brace = String.valueOf(line.charAt(i));
                if (!matchingBraces.containsKey(brace)) {
                    braces.push(brace);
                } else {
                    if (braces.isEmpty()) {
                        fixedInput.remove(line);
                        braces = null;
                        break;
                    } else {
                        String lastBrace = braces.pop();
                        if (!lastBrace.equals(matchingBraces.get(brace))) {
                            fixedInput.remove(line);
                            braces = null;
                            break;
                        }
                    }
                }
            }
            if(braces != null) {
                long lineScore = 0;
                while (braces.size() > 0) {
                    lineScore = (lineScore * 5) + getBracesScore(braces.pop());
                }
                scores.add(lineScore);
            }
        }
        return String.valueOf(scores.stream().sorted().collect(Collectors.toList()).get(scores.size()/2));
    }

    private void buildBracesMap() {
        matchingBraces = new HashMap<>();
        matchingBraces.put(")", "(");
        matchingBraces.put("]", "[");
        matchingBraces.put("}", "{");
        matchingBraces.put(">", "<");
    }

    private long getBracesScore(String value) {
        if (value.equals("(")) return 1;
        if (value.equals("[")) return 2;
        if (value.equals("{")) return 3;
        if (value.equals("<")) return 4;
        return 0;
    }

}
