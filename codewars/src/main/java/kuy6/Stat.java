package kuy6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stat {
    public static String stat(String strg) {
        if (!strg.isEmpty()) {
            List<Integer> resultsCompetition = new ArrayList<>();

            String[] split = strg.split(",");

            for (String string : split) {
                resultsCompetition.add(convertingStrToSeconds(string));
            }
            int range = calculateRange(resultsCompetition);
            int average = calculateAverage(resultsCompetition);
            int median = calculateMedian(resultsCompetition);
            return "Range: " + convertingSecondsToStr(range) +
                   " Average: " + convertingSecondsToStr(average) +
                   " Median: " + convertingSecondsToStr(median);
        } else {
            return "";
        }
    }

    private static String convertingSecondsToStr(int seconds) {
        int hours = (seconds % (24 * (60 * 60))) / (60 * 60);
        int minutes = (seconds % (60 * 60)) / 60;
        int second = seconds % 60;
        return (hours < 10 ? "0" + hours : hours) + "|" +
               (minutes < 10 ? "0" + minutes : minutes) + "|" +
               (second < 10 ? "0" + second : second);
    }

    private static int calculateMedian(List<Integer> resultsCompetition) {
        Collections.sort(resultsCompetition);
        int index = resultsCompetition.size() / 2;
        if (resultsCompetition.size() % 2 != 0) {
            return resultsCompetition.get(index);
        } else {
            return (resultsCompetition.get(index) + resultsCompetition.get(index - 1)) / 2;
        }
    }

    private static int calculateAverage(List<Integer> resultsCompetition) {
        int sumResults = resultsCompetition.stream().mapToInt(Integer::intValue).sum();
        return sumResults / resultsCompetition.size();
    }

    private static int calculateRange(List<Integer> resultsCompetition) {
        int max = Collections.max(resultsCompetition);
        int min = Collections.min(resultsCompetition);
        return max - min;
    }

    private static Integer convertingStrToSeconds(String str) {
        String[] split = str.split("\\|");
        int hours = Integer.parseInt(split[0].trim());
        int minutes = Integer.parseInt(split[1]);
        int seconds = Integer.parseInt(split[2]);
        return seconds + minutes * 60 + hours * 60 * 60;
    }
}
