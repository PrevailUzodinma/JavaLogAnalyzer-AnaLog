package com.PrevailUzodinma.strategy;

import com.PrevailUzodinma.model.LogEntry;
import java.util.List;

public interface LogAnalyzer {
    List<LogEntry> analyze(List<LogEntry> entries);
}
