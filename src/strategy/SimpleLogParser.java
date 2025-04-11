package strategy;

import model.LogEntry;
import reader.LogReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Set up SimpleLogParser to implement LogParser interface
public class SimpleLogParser implements LogParser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogEntry parse(String logLine){
        // for the datetime to be parsed correctly I removed the square brackets from the timestamp if they exist
        logLine = logLine.replaceAll("[\\[\\]]", "");

        // I split it into parts, to extract the relevant field
        String[] parts = logLine.split(" ", 3); // 3 to represent timestamp, level, message

        // extract each field into variable time, level and message
        LocalDateTime timestamp = LocalDateTime.parse(parts[0] + " " + parts[1], formatter);
        String[] levelAndMessage = parts[2].split(" ", 2);
        String level = levelAndMessage[0];

        String message = levelAndMessage.length > 1 ? levelAndMessage[1] : "";

        // pass them as arguments into LogEntry, and then add them to the list of LogEntries
        return new LogEntry(timestamp, level, message);
    }
}
