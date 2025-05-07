package com.PrevailUzodinma.parser;

import com.PrevailUzodinma.model.LogEntry;
import com.PrevailUzodinma.singleton.LogConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleLogParser implements LogParser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogEntry parse(String logLine) {
        // Check for null or empty log line
        if (logLine == null || logLine.trim().isEmpty()) {
            System.out.println("\nInvalid log line: Empty or null.");
            return null;
        }

        // Remove square brackets from timestamp if they exist
        logLine = logLine.replaceAll("[\\[\\]]", "");

        // Split the log into timestamp and the rest of the string
        String[] parts = logLine.split(" ", 3); // I'm expecting 3 parts: timestamp, level, message
        if (parts.length < 3) {
            System.out.println("\nInvalid log line: Expected timestamp, level, and message.");
            return null;
        }

        // Parse the timestamp
        LocalDateTime timestamp;
        try {
            timestamp = LocalDateTime.parse(parts[0] + " " + parts[1], formatter);
        } catch (Exception e) {
            System.out.println("\nInvalid timestamp format: " + parts[0] + " " + parts[1]);
            return null;
        }

        // Extract level and message from the rest of the log line
        String[] levelAndMessage = parts[2].split(" ", 2);
        String level = levelAndMessage[0].trim();

        // If the level is not one of the valid levels, use the default level from the config
        if (!isValidLevel(level)) {
            level = LogConfig.getInstance().getDefaultLogLevel(); // Default to INFO if invalid
        }

        // Get the message (if any)
        String message = levelAndMessage.length > 1 ? levelAndMessage[1] : "";

        // Return the LogEntry
        return new LogEntry(timestamp, level, message);
    }

    // Helper method to check if a level is valid (e.g., INFO, ERROR, WARN)
    private boolean isValidLevel(String level) {
        // Define the valid levels
        String[] validLevels = {"INFO", "ERROR", "WARN", "DEBUG"};
        for (String validLevel : validLevels) {
            if (validLevel.equalsIgnoreCase(level)) {
                return true;
            }
        }
        return false;
    }
}
