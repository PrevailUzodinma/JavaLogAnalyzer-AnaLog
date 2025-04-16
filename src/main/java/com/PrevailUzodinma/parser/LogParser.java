package com.PrevailUzodinma.parser;

import com.PrevailUzodinma.model.LogEntry;

public interface LogParser {
    LogEntry parse(String logLine);
}
