package strategy;

import model.LogEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountByLevelAnalyzer implements LogAnalyzer {

    public void analyze(List<LogEntry> entries) {
        Map<String, Integer> levelCounts = new HashMap<>();
        Map<String, List<LogEntry>> logsByLevel = new HashMap<>(); // A map to store logs by level

        if (entries.isEmpty()) {
            System.out.println("Oops! Sorry, there are no logs in this file to analyze.");
            return;  // Exit the method if no logs are found
        }

        for (LogEntry entry : entries) {
            // ENCAPSULATION IMPLEMENTATION: using the getter method to get the value of the private variable "level"
            String level = entry.getLevel();

            // Counting log entries by level
            levelCounts.put(level, levelCounts.getOrDefault(level, 0) + 1);

            // Storing logs by level
            logsByLevel.computeIfAbsent(level, k -> new ArrayList<>()).add(entry);
        }

        // Console Output for the analyzer
        System.out.println("\n🔍 Log Level Counts:");

        // For every entry in the hashmap, print the count of logs by level
        for (Map.Entry<String, Integer> entry : levelCounts.entrySet()) {
            System.out.println("\n" + entry.getKey() + ": " + entry.getValue() + " logs");

            // Print the logs for this level
            System.out.println("\nLogs for level " + entry.getKey() + ":");
            for (LogEntry log : logsByLevel.get(entry.getKey())) {
                System.out.println(log);
            }
        }
    }
}
