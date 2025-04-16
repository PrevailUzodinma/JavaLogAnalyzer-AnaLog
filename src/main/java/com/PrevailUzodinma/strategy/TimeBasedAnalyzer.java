package com.PrevailUzodinma.strategy;

import com.PrevailUzodinma.model.LogEntry;
import com.PrevailUzodinma.singleton.LogConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeBasedAnalyzer implements LogAnalyzer {

    private final DateTimeFormatter formatter;
    private final String label;
    private final LocalDateTime targetDate;

    public TimeBasedAnalyzer(String label, LocalDateTime targetDate) {
        String pattern = LogConfig.getInstance().getTimestampPattern();
        this.formatter = DateTimeFormatter.ofPattern(pattern);
        this.label = label;
        this.targetDate = targetDate;
    }

    @Override
    public List<LogEntry> analyze(List<LogEntry> entries) {
        if (entries.isEmpty()) {
            System.out.println("Oops! Sorry, there are no logs in this file to analyze.");
            return Collections.emptyList();
        }

        System.out.println("\nLogs grouped by " + label + ":");

        Map<String, Integer> groupedLogs = new HashMap<>();
        Map<String, List<LogEntry>> logsByTime = new HashMap<>();
        boolean foundLogs = false;
        List<LogEntry> filtered = new ArrayList<>();

        for (LogEntry entry : entries) {
            if (label.equals("day")) {
                if (entry.getTimestamp().toLocalDate().isEqual(targetDate.toLocalDate())) {
                    String timeKey = entry.getTimestamp().format(formatter);
                    groupedLogs.put(timeKey, groupedLogs.getOrDefault(timeKey, 0) + 1);
                    logsByTime.computeIfAbsent(timeKey, k -> new ArrayList<>()).add(entry);
                    filtered.add(entry);
                    foundLogs = true;
                }
            } else if (label.equals("hour")) {
                if (entry.getTimestamp().getYear() == targetDate.getYear() &&
                        entry.getTimestamp().getMonth() == targetDate.getMonth() &&
                        entry.getTimestamp().getDayOfMonth() == targetDate.getDayOfMonth() &&
                        entry.getTimestamp().getHour() == targetDate.getHour()) {
                    String timeKey = entry.getTimestamp().format(formatter);
                    groupedLogs.put(timeKey, groupedLogs.getOrDefault(timeKey, 0) + 1);
                    logsByTime.computeIfAbsent(timeKey, k -> new ArrayList<>()).add(entry);
                    filtered.add(entry);
                    foundLogs = true;
                }
            }
        }

        if (!foundLogs) {
            System.out.println("No logs on this " + label + ": " + targetDate);
        } else {
            int totalLogs = groupedLogs.values().stream().mapToInt(Integer::intValue).sum();
            System.out.println("Total logs for " + label + ": " + totalLogs);

            for (Map.Entry<String, Integer> entry : groupedLogs.entrySet()) {
                System.out.println("\n" + entry.getKey() + ": " + entry.getValue() + " logs");

                System.out.println("\nLogs for " + entry.getKey() + ":");
                for (LogEntry log : logsByTime.get(entry.getKey())) {
                    System.out.println(log);
                }
            }
        }

        return filtered;
    }
}
