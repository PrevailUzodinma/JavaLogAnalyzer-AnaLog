package strategy;

import model.LogEntry;
import reader.LogReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Set up SimpleLogParser to implement LogParser interface
public class SimpleLogParser implements LogParser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogEntry parse(String logLine){
        String[] parts = logLine.split(" ", 3); // timestamp, level, message
        LocalDateTime timestamp = LocalDateTime.parse(parts[0] + " " + parts[1], formatter);
        String[] levelAndMessage = parts[2].split(" ", 2);
        String level = levelAndMessage[0];

        String message = levelAndMessage.length > 1 ? levelAndMessage[1] : "";

        return new LogEntry(timestamp, level, message);
    }
}
