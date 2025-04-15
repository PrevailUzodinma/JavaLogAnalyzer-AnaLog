package strategy;

import model.LogEntry;
import java.util.List;

public interface LogAnalyzer {
    void analyze(List<LogEntry> entries);
}
