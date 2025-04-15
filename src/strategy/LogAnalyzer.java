package strategy;

import model.LogEntry;
import java.util.List;

public interface LogAnalyzer {
    List<LogEntry> analyze(List<LogEntry> entries);
}
