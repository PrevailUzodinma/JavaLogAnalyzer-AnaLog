package strategy;

import model.LogEntry;

public interface LogParser {
    LogEntry parse(String logLine);
}
