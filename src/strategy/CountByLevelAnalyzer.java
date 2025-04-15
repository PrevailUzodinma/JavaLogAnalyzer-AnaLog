package strategy;

import model.LogEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountByLevelAnalyzer implements LogAnalyzer {

    public void analyze(List<LogEntry> entries) {
        Map<String, Integer> levelCounts = new HashMap<>();
        Map<String, List<LogEntry>> logsByLevel = new HashMap<>();

        if (entries.isEmpty()) {
            System.out.println("Oops! Sorry, there are no logs in this file to analyze.");
            return;
        }

        for (LogEntry entry : entries) {
            String level = entry.getLevel();
            levelCounts.put(level, levelCounts.getOrDefault(level, 0) + 1);
            logsByLevel.computeIfAbsent(level, k -> new ArrayList<>()).add(entry);
        }

        System.out.println("\n🔍 Log Level Counts:");

        for (Map.Entry<String, Integer> entry : levelCounts.entrySet()) {
            System.out.println("\n" + entry.getKey() + ": " + entry.getValue() + " logs");
            System.out.println("\nLogs for level " + entry.getKey() + ":");
            for (LogEntry log : logsByLevel.get(entry.getKey())) {
                System.out.println(log);
            }
        }

        // ✅ Update entries to reflect only what was analyzed (this may be redundant here
        // because we analyze all, but this ensures consistency)
        List<LogEntry> filtered = new ArrayList<>();
        for (List<LogEntry> logs : logsByLevel.values()) {
            filtered.addAll(logs);
        }
        entries.clear();
        entries.addAll(filtered);
    }
}
