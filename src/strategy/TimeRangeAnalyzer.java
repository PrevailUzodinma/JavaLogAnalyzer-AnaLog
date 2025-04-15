package strategy;

import model.LogEntry;
import singleton.LogConfig; // Import LogConfig to get the timestamp pattern

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimeRangeAnalyzer implements LogAnalyzer {

    private final LocalDateTime start;
    private final LocalDateTime end;
    private final DateTimeFormatter formatter; // Add DateTimeFormatter for pattern

    public TimeRangeAnalyzer(LocalDateTime start, LocalDateTime end) {
        // Get timestamp pattern from LogConfig singleton
        String pattern = LogConfig.getInstance().getTimestampPattern();
        this.formatter = DateTimeFormatter.ofPattern(pattern); // Initialize the formatter with the pattern
        this.start = start;
        this.end = end;
    }

    public void analyze(List<LogEntry> entries) {
        System.out.printf("\n📅 Logs between %s and %s:\n", start.format(formatter), end.format(formatter));

        boolean found = false; // Track if logs fall in this time range
        for (LogEntry entry : entries) {
            LocalDateTime timestamp = entry.getTimestamp(); // Encapsulation implementation: getTimestamp is a getter method
            if (!timestamp.isBefore(start) && !timestamp.isAfter(end)) {
                System.out.println(entry); // Print the log entry
                found = true;
            }
        }

        if (!found) {
            System.out.println("No logs found in the specified time range.");
        }
    }
}
