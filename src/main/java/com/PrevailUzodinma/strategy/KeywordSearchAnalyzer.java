package com.PrevailUzodinma.strategy;

import com.PrevailUzodinma.model.LogEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class KeywordSearchAnalyzer implements LogAnalyzerStrategy {
    private final String keyword;

    public KeywordSearchAnalyzer(String keyword) {
        this.keyword = keyword.toLowerCase(Locale.ROOT);
    }

    @Override
    public List<LogEntry> analyze(List<LogEntry> entries) {
        List<LogEntry> filtered = new ArrayList<>();

        for (LogEntry entry : entries) {
            String message = entry.getMessage().toLowerCase(Locale.ROOT);
            if (message.contains(keyword)) {
                filtered.add(entry);
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("No logs found with the keyword: " + keyword);
        } else {
            System.out.println("\nLogs containing keyword: \"" + keyword + "\"");
            System.out.println("==============================================\n");
            for (LogEntry entry : filtered) {
                System.out.println(entry);
            }
            System.out.println("\nTotal matches: " + filtered.size());
        }

        return filtered;
    }
}
