package analyzer;

import model.LogEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountByHourLogAnalyzer implements LogAnalyzer {

    public void analyze(List<LogEntry> entries){
        Map<Integer, Integer> hourCounts = new HashMap<>();

        for (LogEntry entry : entries) {
            // ENCAPSULATION IMPLEMENTATION: using the getter getTimestamp()
            int hour = entry.getTimestamp().getHour();
            hourCounts.put(hour, hourCounts.getOrDefault(hour, 0) + 1);
        }

        // Console Outputs for the analyzer
        System.out.println("\nLogs Per Hour:");
        for (int hour = 0; hour < 24; hour++){
            int count = hourCounts.getOrDefault(hour, 0);
            System.out.printf("Hour %02d: %d entries%n", hour, count);
        }
    }
}
