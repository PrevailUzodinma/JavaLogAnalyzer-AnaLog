package strategy;

import model.LogEntry;
import reader.LogReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Set up SimpleLogParser to implement LogParser interface
public class SimpleLogParser implements LogParser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogEntry parse(String logLine){

    }
}
