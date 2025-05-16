package kyu4;

public class TimeFormatter {
    public static String formatDuration(int seconds) {
        int years = seconds / (365 * 24 * (60 * 60));
        int days = (seconds % (365 * 24 * (60 * 60))) / (24 * (60 * 60));
        int hours = (seconds % (24 * (60 * 60))) / (60 * 60);
        int minutes = (seconds % (60 * 60)) / 60;
        int second = seconds % 60;

        StringBuilder result = new StringBuilder();
        int count = 0;

        if (seconds == 0) {
            return "now";
        } else {
            if (second > 0) {
                String resultSeconds = second == 1 ? second + " second" : second + " seconds";
                result.append(resultSeconds);
                count++;
            }

            if (minutes > 0) {
                String resultMinutes = minutes == 1 ? minutes + " minute" : minutes + " minutes";
                if (count == 1) {
                    resultMinutes = resultMinutes + " and ";
                }
                result.insert(0, resultMinutes);
                count++;
            }

            if (hours > 0) {
                String resultHours = hours == 1 ? hours + " hour" : hours + " hours";
                if (!result.isEmpty()) {
                    if (count == 1) {
                        resultHours = resultHours + " and ";
                    } else {
                        resultHours = resultHours + ", ";
                    }
                }
                result.insert(0, resultHours);
                count++;
            }

            if (days > 0) {
                String resultDays = days == 1 ? days + " day" : days + " days";
                if (count == 1) {
                    resultDays = resultDays + " and ";
                } else {
                    resultDays = resultDays + ", ";
                }
                result.insert(0, resultDays);
                count++;
            }

            if (years > 0) {
                String resultYears = years == 1 ? years + " year" : years + " years";
                if (count == 1) {
                    resultYears = resultYears + " and ";
                } else {
                    resultYears = resultYears + ", ";
                }
                result.insert(0, resultYears);
            }

        }
        return String.valueOf(result);
    }

}

