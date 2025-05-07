package com.PrevailUzodinma.strategy;

import com.PrevailUzodinma.model.LogEntry;

import java.util.*;

public class LevelAnalyzer implements LogAnalyzerStrategy {

    @Override
    public List<LogEntry> analyze(List<LogEntry> entries) {
        if (entries.isEmpty()) {
            System.out.println("Oops! Sorry, there are no logs in this file to analyze.");
            return Collections.emptyList();
        }

        Map<String, Integer> levelCounts = new HashMap<>();
        Map<String, List<LogEntry>> logsByLevel = new HashMap<>();

        for (LogEntry entry : entries) {
            String level = entry.getLevel();
            levelCounts.put(level, levelCounts.getOrDefault(level, 0) + 1);
            logsByLevel.computeIfAbsent(level, k -> new ArrayList<>()).add(entry);
        }

        System.out.println("\nLog Level Counts:");
        System.out.println("===================");

        List<LogEntry> filtered = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : levelCounts.entrySet()) {
            String level = entry.getKey();
            int count = entry.getValue();
            List<LogEntry> logs = logsByLevel.get(level);

            System.out.println("\n" + level + ": " + count + " logs");
            System.out.println("Logs for level " + level + ":");
            for (LogEntry log : logs) {
                System.out.println(log);
                filtered.add(log);
            }
        }

        return filtered;
    }
}
