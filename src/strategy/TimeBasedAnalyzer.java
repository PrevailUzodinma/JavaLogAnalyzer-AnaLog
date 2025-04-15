package strategy;

import model.LogEntry;
import singleton.LogConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedAnalyzer implements LogAnalyzer {

    private final DateTimeFormatter formatter;
    private final String label;
    private final LocalDateTime targetDateTime;  // Store both date and hour

    public TimeBasedAnalyzer(String label, LocalDateTime targetDateTime) {
        // Use the timestamp pattern from LogConfig singleton
        String pattern = LogConfig.getInstance().getTimestampPattern();
        this.formatter = DateTimeFormatter.ofPattern(pattern);
        this.label = label;
        this.targetDateTime = targetDateTime;  // Store the target date and time
    }

    @Override
    public void analyze(List<LogEntry> entries) {
        System.out.println("\n🕒 Logs grouped by " + label + ":");

        Map<String, Integer> groupedLogs = new HashMap<>();

        for (LogEntry entry : entries) {
            // Compare the log entry's timestamp to the target date and hour
            if (entry.getTimestamp().getYear() == targetDateTime.getYear() &&
                    entry.getTimestamp().getMonth() == targetDateTime.getMonth() &&
                    entry.getTimestamp().getDayOfMonth() == targetDateTime.getDayOfMonth() &&
                    entry.getTimestamp().getHour() == targetDateTime.getHour()) {

                // Format the timestamp and group by the specific hour
                String timeKey = entry.getTimestamp().format(formatter);
                groupedLogs.put(timeKey, groupedLogs.getOrDefault(timeKey, 0) + 1);
            }
        }

        if (groupedLogs.isEmpty()) {
            System.out.println("No logs on this " + label + ": " + targetDateTime.format(formatter));
        } else {
            for (Map.Entry<String, Integer> entry : groupedLogs.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " logs");
            }
        }
    }
}
