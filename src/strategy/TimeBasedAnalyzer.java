package strategy;

import model.LogEntry;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedAnalyzer implements LogAnalyzer {

    private final DateTimeFormatter formatter;
    private final String label;

    public TimeBasedAnalyzer(String pattern, String label) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
        this.label = label;
    }

    public void analyze(List<LogEntry> entries) {
        System.out.println("\n🕒 Logs grouped by " + label + ":");

        Map<String, Integer> groupedLogs = new HashMap<>();

        for (LogEntry entry : entries) {
            String timeKey = entry.getTimestamp().format(formatter);
            groupedLogs.put(timeKey, groupedLogs.getOrDefault(timeKey, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : groupedLogs.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " logs");
        }
    }
}
