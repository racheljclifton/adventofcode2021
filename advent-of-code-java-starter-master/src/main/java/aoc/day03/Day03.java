package aoc.day03;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day03 implements Day {

    @Override
    public String part1(List<String> input) {
        int binaryLength = input.get(0).length();
        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        for (int i = 0; i < binaryLength ; i++) {
            String mostCommon = findMostCommon(input, i);
            gammaRate.append(mostCommon);
            if (mostCommon.equals("1")) {
                epsilonRate.append("0");
            } else {
                epsilonRate.append("1");
            }
        }
        int gammaRateInt = Integer.parseInt(String.valueOf(gammaRate), 2);
        int epsilonRateInt = Integer.parseInt(String.valueOf(epsilonRate), 2);

        return String.valueOf(gammaRateInt * epsilonRateInt);
    }

    @Override
    public String part2(List<String> input) {
        int binaryLength = input.get(0).length();
        List<String> oxygenGeneratorRating = input;
        for (int i = 0; i < binaryLength; i ++) {
            String mostCommon = findMostCommon(oxygenGeneratorRating, i);
            int finalI = i;
            oxygenGeneratorRating = oxygenGeneratorRating
                    .stream()
                    .filter(binary -> mostCommon.equals(String.valueOf(binary.charAt(finalI))))
                    .collect(Collectors.toList());
            if (oxygenGeneratorRating.size() == 1) {
                break;
            }
        }
        List<String> co2ScrubberRating = input;
        for (int i = 0; i < binaryLength; i ++) {
            String leastCommon = findLeastCommon(co2ScrubberRating, i);
            int finalI = i;
            co2ScrubberRating = co2ScrubberRating
                    .stream()
                    .filter(binary -> leastCommon.equals(String.valueOf(binary.charAt(finalI))))
                    .collect(Collectors.toList());
            if (co2ScrubberRating.size() == 1) {
                break;
            }
        }

        return String.valueOf(Integer.parseInt(oxygenGeneratorRating.get(0), 2) * Integer.parseInt(co2ScrubberRating.get(0), 2));
    }

    private String findMostCommon(List<String> input, int index)
    {
        int total = input.stream().map(binary -> Character.getNumericValue(binary.charAt(index))).reduce(0, (subtotal, num) -> subtotal += num);
        return total >= input.size()/2.0 ? "1" : "0";
    }

    private String findLeastCommon(List<String> input, int index)
    {
        int total = input.stream().map(binary -> Character.getNumericValue(binary.charAt(index))).reduce(0, (subtotal, num) -> subtotal += num);
        return total >= input.size()/2.0 ? "0" : "1";
    }

}
