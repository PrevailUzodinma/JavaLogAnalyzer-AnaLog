package strategy;

import model.LogEntry;
import java.time.LocalDateTime;
import java.util.List;

public class TimeRangeAnalyzer implements LogAnalyzer {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeRangeAnalyzer(LocalDateTime start, LocalDateTime end){
        this.start = start;
        this.end = end;
    }

    public void analyze(List<LogEntry> entries) {
        System.out.printf("\n📅 Logs between %s and %s:\n", start, end);

        boolean found = false; // use a boolean variable to track if we have found the logs that fall in this category
        for (LogEntry entry : entries) {
            LocalDateTime timestamp = entry.getTimestamp(); // Encapsulation implementation: getTimeStamp is a getter method
            if (!timestamp.isBefore(start) && !timestamp.isAfter(end)) {
                System.out.println(entry);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No logs found in the specified time range.");
        }

    }
}
