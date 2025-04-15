package strategy;

import model.LogEntry;
import singleton.LogConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedAnalyzer implements LogAnalyzer {

    private final DateTimeFormatter formatter;
    private final String label;
    private final LocalDateTime targetDate;

    public TimeBasedAnalyzer(String label, LocalDateTime targetDate) {
        // Use the timestamp pattern from LogConfig singleton
        String pattern = LogConfig.getInstance().getTimestampPattern();
        this.formatter = DateTimeFormatter.ofPattern(pattern);
        this.label = label;
        this.targetDate = targetDate;  // Store the target date for filtering logs
    }

    @Override
    public void analyze(List<LogEntry> entries) {
        if (entries.isEmpty()) {
            System.out.println("Oops! Sorry, there are no logs in this file to analyze.");
            return;  // Exit the method if no logs are found
        }

        System.out.println("\n🕒 Logs grouped by " + label + ":");

        Map<String, Integer> groupedLogs = new HashMap<>();
        Map<String, List<LogEntry>> logsByTime = new HashMap<>(); // Store the logs for each timeKey
        boolean foundLogs = false;

        // Iterate through the log entries and group them by the target date or hour
        for (LogEntry entry : entries) {
            if (label.equals("day")) {
                // For "day" analysis, compare only the date part
                if (entry.getTimestamp().toLocalDate().isEqual(targetDate.toLocalDate())) {
                    String timeKey = entry.getTimestamp().format(formatter);
                    groupedLogs.put(timeKey, groupedLogs.getOrDefault(timeKey, 0) + 1);

                    // Storing logs by timeKey for later display
                    logsByTime.computeIfAbsent(timeKey, k -> new ArrayList<>()).add(entry);
                    foundLogs = true;
                }
            } else if (label.equals("hour")) {
                // For "hour" analysis, compare both the date and hour
                if (entry.getTimestamp().getYear() == targetDate.getYear() &&
                        entry.getTimestamp().getMonth() == targetDate.getMonth() &&
                        entry.getTimestamp().getDayOfMonth() == targetDate.getDayOfMonth() &&
                        entry.getTimestamp().getHour() == targetDate.getHour()) {
                    String timeKey = entry.getTimestamp().format(formatter);
                    groupedLogs.put(timeKey, groupedLogs.getOrDefault(timeKey, 0) + 1);

                    // Storing logs by timeKey for later display
                    logsByTime.computeIfAbsent(timeKey, k -> new ArrayList<>()).add(entry);
                    foundLogs = true;
                }
            }
        }

        // If no logs for the target date or hour, print "No logs on this day"
        if (!foundLogs) {
            System.out.println("🔍 No logs on this " + label + ": " + targetDate);
        } else {
            // Print the total log count for the entire day or hour first
            int totalLogs = groupedLogs.values().stream().mapToInt(Integer::intValue).sum();
            System.out.println("Total logs for " + label + ": " + totalLogs);

            // Print the logs grouped by day or hour
            for (Map.Entry<String, Integer> entry : groupedLogs.entrySet()) {
                System.out.println("\n" + entry.getKey() + ": " + entry.getValue() + " logs");

                // Display the actual logs for each timeKey
                System.out.println("\nLogs for " + entry.getKey() + ":");
                for (LogEntry log : logsByTime.get(entry.getKey())) {
                    System.out.println(log);
                }
            }
        }
    }
}
